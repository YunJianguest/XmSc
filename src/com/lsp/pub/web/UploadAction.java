package com.lsp.pub.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Results;

import com.lsp.pub.upload.HttpURLConnectionUtils;
import com.lsp.pub.upload.JsonUtil;
import com.lsp.pub.util.EncodeUtils;
import com.lsp.pub.util.PayCommonUtil;
import com.lsp.pub.util.Struts2Utils;
import com.lsp.pub.util.SysConfig;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 文件上传
 * @author lsp 
 *   
 */
@Namespace("/pub")
@Results({ @org.apache.struts2.convention.annotation.Result(name = "reload", location = "upload.action", type = "redirect") })
public class UploadAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6784469775589971579L;
	private List<File> image; // 上传的文件
	private List<String> imageFileName; // 文件名称
	private List<String> imageContentType; // 文件类型
	private String savePath;

	@Override
	public String execute() throws Exception { 
		// 取得需要上传的文件数组
		List<File> files = getImage();
		
		
		if (files != null && files.size() > 0) {
		
			for (int i = 0; i < files.size(); i++) {
				
				FileInputStream fis = new FileInputStream(files.get(i));
				byte[] buffer = new byte[fis.available()]; 
				fis.read(buffer); 
				fis.close();
			  
				JsonUtil.Add(new String[]{"file","FileName","ContentType"},new Object[]{ EncodeUtils.base64Encode(buffer),imageFileName.get(i),imageContentType.get(i)});
				 
			}
			
			HttpURLConnectionUtils.GetHttpURLConnection(SysConfig.getProperty("imgurl"));
			savePath=HttpURLConnectionUtils.SendFiles(JsonUtil.JSONARRAY.toString(),HttpURLConnectionUtils.CONN);
		 
			Struts2Utils.getResponse().getWriter().print(savePath);
			 
		}
		
		//
		return SUCCESS;
	}

	public List<File> getImage() {
		return image;
	}

	public void setImage(List<File> image) {
		this.image = image;
	}

	public List<String> getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(List<String> imageFileName) {
		this.imageFileName = imageFileName;
	}

	public List<String> getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(List<String> imageContentType) {
		this.imageContentType = imageContentType;
	}
	/**
	 * 交易测试 笑我一世沉沦: 传入参数的格式 {"data":
	 * 
	 * {"eth":"0xed188d6257ab4adbaf266cd5be3b563e11696882","num":1,"username":
	 * 
	 * "admin","key":"82bd7f241cc6d151de1c0aa26713c60e","orderid":"A1245649846
	 * 
	 * 57846"}} 笑我一世沉沦: key是eth+num+username+orderid+密钥
	 * 
	 * @throws IOException
	 */
	public void testjr() throws IOException {

		HashMap<String, Object>map=new HashMap<>();
		InputStream input = Struts2Utils.getRequest

		().getInputStream();
		BufferedReader br = new BufferedReader(new

		InputStreamReader(input, "utf-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			line = new String(line.getBytes(), "utf-8");
			sb.append(line);
		}
		JSONObject jsonObject =null;
		String sign=null;
		sb.toString(); 
		if(sb.length()>0) {
			jsonObject = JSONObject.fromObject

					(URLDecoder.decode(sb.toString(), "utf-8"));
			SortedMap<Object, Object> parameters = new

					TreeMap<Object, Object>();
					parameters.put("eth", jsonObject.get("eth"));
					parameters.put("num", jsonObject.get("num"));
					parameters.put("username", jsonObject.get("username"));
					parameters.put("orderid", jsonObject.get("orderid"));
					// 验证签名
					sign= PayCommonUtil.createKey("UTF-8", jsonObject.getString("eth")+jsonObject.getString("num")+jsonObject.getString("username")+jsonObject.getString("orderid"), "uskdpro6623");
					System.out.println(sign);
					System.out.println(jsonObject.get("key"));
		}else {
			map.put("state", "10001");
			map.put("msg", "数据错误");
			String json = JSONArray.fromObject(map).toString();
			Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);
			return;
		}
		 
		if (sign!=null&&sign.equals(jsonObject.get("key"))) {
			map.put("state", "10005");
			map.put("msg", "成功！");
		} else {
			map.put("state", "10002");
			map.put("msg", "key错误");
		
		}
		String json = JSONArray.fromObject(map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);

	}
	public void testjr1() throws IOException {

		HashMap<String, Object>map=new HashMap<>(); 
		String sign=null; 
		String eth=Struts2Utils.getParameter("eth");
		String num=Struts2Utils.getParameter("num");
		String username=Struts2Utils.getParameter("username");
		String orderid=Struts2Utils.getParameter("orderid");
		String key=Struts2Utils.getParameter("key");
		sign= PayCommonUtil.createKey("UTF-8", eth+num+username+orderid, "uskdpro6623");
		if (sign!=null&&sign.equals(key)) {
			map.put("state", "10005");
			map.put("msg", "成功！");
		} else {
			map.put("state", "10002");
			map.put("msg", "key错误");
		
		}
		String json = JSONArray.fromObject(map).toString();
		Struts2Utils.renderJson(json.substring(1, json.length() - 1), new String[0]);

	}

   
}
