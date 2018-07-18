package com.lsp.pub.service;

 
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.lsp.integral.entity.InteProstore;
import com.lsp.pub.dao.BaseDao;
import com.lsp.pub.db.MongoSequence;
import com.lsp.pub.entity.PubConstants;
import com.lsp.pub.util.BaseDecimal;
import com.lsp.pub.util.DateUtil;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.SysConfig;
import com.lsp.pub.util.UniObject;
import com.lsp.shop.entiy.OrderForm;
import com.lsp.shop.entiy.ShopMb;
import com.lsp.suc.entity.IntegralInfo;
import com.lsp.suc.entity.IntegralRecord;
import com.lsp.suc.entity.Ranking;
import com.lsp.website.service.WwzService;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;
 
/**
 * 定时任务
 * @author lsp
 *
 */
public class TimerService {
	/** LOG4J对象 */
	private static Logger logger = LoggerFactory.getLogger(TimerService.class);

	private static TimerService timerService;
	@Autowired
	private BaseDao baseDao; 
	private MongoSequence mongoSequence;	
	@Autowired
	private WwzService  wwzservice; 
	 
	@Autowired
	public void setMongoSequence(MongoSequence mongoSequence) {
		this.mongoSequence = mongoSequence;
	}
    /**
	 * 服务类实例化
	 * 
	 * @return CzrzService
	 */
	public synchronized static TimerService getInstance() {
		if (timerService == null) {
			timerService = new TimerService();
		}
		return timerService;
	} 
	/**
	 * 每日返还
	 * @throws Exception 
	 */
	public synchronized void updProstore() throws Exception{
		System.out.println("进入这个方法1");
		HashMap<String, Object>sortMap=new HashMap<String, Object>();
		HashMap<String, Object>whereMap=new HashMap<String, Object>();
		sortMap.put("createdate", -1);
		BasicDBObject dateCondition = new BasicDBObject();
		dateCondition.append("$gte",new Date());
		whereMap.put("enddate", dateCondition);
		whereMap.put("state", 0);
		List<DBObject>list=baseDao.getList(PubConstants.INTEGRAL_PROSTORE,whereMap, sortMap); 
		for (DBObject dbObject : list) {
			if(dbObject.get("money")!=null){
				String price = "0";
				System.out.println("money"+dbObject.get("money"));
				System.out.println("time"+dbObject.get("time"));
				
				if(dbObject.get("money")!=null&&dbObject.get("time")!=null&&Integer.parseInt(dbObject.get("time").toString())>0) { 
            		price = BaseDecimal.division(dbObject.get("money").toString(),dbObject.get("time").toString(),6);
            	}else {
            		price = BaseDecimal.division("0","1",6);
            	}
				double kjlx=0;
				HashMap<String, Object>where1Map=new HashMap<>();
	    		whereMap.put("custid",SysConfig.getProperty("custid"));
	    		whereMap.put("fromUserid", dbObject.get("fromUserid").toString());
	    		IntegralRecord ir = null;
				DBObject db = baseDao.getMessage(PubConstants.SUC_INTEGRALRECORD, where1Map);
				if(db!=null){
					ir=(IntegralRecord) UniObject.DBObjectToObject(db, IntegralRecord.class);
					kjlx=ir.getKjlx(); 
					
				}
				System.out.println("--------------///////////--"+price);
				if(kjlx>0){
					if(kjlx==1){
						price=BaseDecimal.division(price,wwzservice.getPPBSprice()+"",20);
					}else if(kjlx==2){
						price=BaseDecimal.division(price,wwzservice.getBTCSprice()+"",20);
						
					}else if(kjlx==3){
						price=BaseDecimal.division(price,wwzservice.getETHSprice()+"",20);
						
					} 
					if(dbObject.get("fromUserid")!=null&&dbObject.get("type")!=null){
						if(dbObject.get("type").toString().equals("ps_account")||dbObject.get("type").toString().equals("ps_recovery")){
							//挖矿到矿机账号
							System.out.println("----------------"+price);
							wwzservice.addyfjf(price, dbObject.get("fromUserid").toString(), dbObject.get("type").toString(), SysConfig.getProperty("custid"),1,dbObject.get("_id").toString(), null);
							
						}else{
							wwzservice.addyfjf(price, dbObject.get("fromUserid").toString(), dbObject.get("type").toString(), SysConfig.getProperty("custid"),1,dbObject.get("_id").toString(), null);
						}
						
						
						
						/*//返回积分到直推用户
						DBObject user=wwzservice.getCustUser(dbObject.get("fromUserid").toString());
					    if(user.get("parentid")!=null) {
					    	wwzservice.addjf(Double.parseDouble(price)*0.1+"", user.get("parentid").toString(),user.get("custid").toString(), dbObject.get("type").toString(),1,1,0);
					    }*/
						
					}
				}
			
			}
		}
	}
	
	/**
	 * 更新返还账户
	 */
	public synchronized void delProstore() throws Exception{
		System.out.println("进入这个方法2");
		HashMap<String, Object>sortMap=new HashMap<String, Object>();
		HashMap<String, Object>whereMap=new HashMap<String, Object>();
		sortMap.put("createdate", -1);
		BasicDBObject dateCondition = new BasicDBObject();
		dateCondition.append("$lt",DateUtil.getTimesnight());
		whereMap.put("createdate", dateCondition);
		whereMap.put("state", 0);
		List<DBObject>list=baseDao.getList(PubConstants.INTEGRAL_PROSTORE,whereMap, sortMap);
		System.out.println("--list-->"+list);
		for (DBObject dbObject : list) {
			InteProstore prostore = (InteProstore)UniObject.DBObjectToObject(dbObject,InteProstore.class);
			prostore.setState(1);//1-已返完
			baseDao.insert(PubConstants.INTEGRAL_PROSTORE, prostore);
			if(prostore.getType().equals("ps_account")){//如果预存类型为开户积分，所以状态为冻结，
				if(dbObject.get("fromUserid")!=null){
					wwzservice.changeFreezeJf(null, dbObject.get("fromUserid").toString());
				}
			}
			
		}
	}
}