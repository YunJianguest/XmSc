	
	//返回顶部
	function blackUp(){
        if($("meta[name=toTop]").attr("content")=="true"){
            $("<div id='toTop'><img src='/jfyMobile/img/blackTop.png'></div>").appendTo('body');
            $("#toTop").css({
                width: '30px',
                height: '30px',
                bottom:'60px',
                borderRadius:'50%',
                right:'15px',
                position:'fixed',
                cursor:'pointer',
                zIndex:'999999'
            });
            $("#toTop img").css({
                width: '100%',
                height: '100%'
            });
            if($(this).scrollTop()==0){
                $("#toTop").hide();
            }
       		$(window).scroll(function(event) {
                /* Act on the event */
                if($(this).scrollTop()==0){
                    $("#toTop").hide();
                }
                if($(this).scrollTop()!=0){
                    $("#toTop").show();
                }
            });
            $("#toTop").click(function(event) {
                /* Act on the event */
                $("html,body").animate({
                    scrollTop:"0px"},
                    666
                )
            });
        }
    }
    blackUp();
    //分类页按钮点击显示隐藏导航
    function noneNav(){
    	$('.search-box-jfy').click(function(){
		$('.shortcut').toggle()
		console.log('...')
		})
    }
    
	
	//首页
	            // 轮播图
    function silder (){
       	var silder = new Swiper('.swiper-container-silder', {
			autoplay:5000,
			pagination: '.swiper-pagination',
            paginationClickable: true,
            loop: true,
            touchRatio: 1,
            visiblilityFullfit: true,
            speed:500
		})
    }
//  silder();
	
	function prodSilder(){
		var silder = new Swiper('.swiper-container-prod', {
			autoplay:5000,
			pagination: '.swiper-pagination',
            paginationClickable: true,
            loop: true,
            touchRatio: 1,
            visiblilityFullfit: true,
            speed:500
		})
	}
	// 快报滚动
//  function scroll (){
//   	var express = document.getElementById('express');
//		//console.log(express)
//		var iliHeight=24;//li每行的高度
//     	var time;
//     	var speed = 50;//设置速度
//     	var delay=1500;//间隔时间
//     	express.scrollTop =0;
//     	express.innerHTML += express.innerHTML;
//          	
//     	function startScoll (){
//     		time=setInterval(function scrollUp(){
//     			if ( express.scrollTop % iliHeight ==0){
//	            	clearInterval( time );
//	            	setTimeout(startScoll,delay);
//	            }
//	            if (express.scrollTop >= express.scrollHeight/2){
//	            express.scrollTop=0;
//	            }else{
//	            	express.scrollTop++;
//	            }
//	       	},speed)
////          console.log(time)
//	        	express.scrollTop++;
//	        }         	
//	    	setTimeout( startScoll,delay)
//  }
//  scroll();
	
function AutoScroll(obj) {
	$(obj).find("ul:first").animate({
	    marginTop: "-22px"
	},
	1000,
	function() {
	    $(this).css({
	        marginTop: "0px"
	    }).find("li:first").appendTo(this);
	});
}

	//分类页左侧导航栏点击切换事件
	function category(){
		$('.category-tab-list li a').click(function (event){
			 event.preventDefault();
            $(this).parent().addClass('opt').siblings('.opt').removeClass('opt');
            console.log(this)
//          var id=$(this).attr('href');
//          $(id).addClass('opt').siblings('.opt').removeClass('opt');
		})
	}
	
	category();
	function lateralNav(){
		$('.dropdown-nav ul li span').click(function(){
			$(this).parent().addClass('cus').siblings('.cus').removeClass('cus');
		})
	}
	lateralNav()

//  商品详情页点击 header-tabs li a 切换页面内容
function prodswitch(){
	$('.header-tabs li a').click(function(event){
		event.preventDefault();
		$(this).addClass('header-tabs-selected').parents('li').siblings('li').children().removeClass('header-tabs-selected');
//		console.log(this)
		var id=$(this).attr('href');
//		console.log(id)
        $(id).addClass('main').show().parents().siblings().children('.main').removeClass('main').hide();
        if($('#prod').hasClass('main')){
        	$('#prodDetails').show();
        	$('.commodity-main div.deta:first').addClass('on').siblings('.deta.on').removeClass('on');
        	$('.tab-list li a:first').addClass('on').parent().siblings().children('.on').removeClass('on')
        }
	})
}
prodswitch()

function prodMsg(){
	// 获取产品信息

//	$('.pro-color p a').click(function (event){
//		console.log(this)
//		event.preventDefault()
//		$(this).addClass('selected').siblings().removeClass('selected');
//		$('.spec-stock-text').text($(this).text());
//	})
	//获取尺码信息
	$('.pro-size p a').click(function (event){
		event.preventDefault()
		$(this).addClass('selected').siblings().removeClass('selected');
		$('.size').text($(this).text());
	})
			
	//加减获取值赋值给已选择
	$(".quantity").find("input").attr("readonly", true);
	$(".quantity-wrapper a").click(function() {
		var min_num = 1;
		var max_num=parseInt($('.spec-stock-stock-num').text());

		var num= $('.quantity').val();
		if ( $(this).hasClass('quantity-reduce')){
			if( num >= min_num){
				num --;
				if(num<min_num){
					$(this).siblings('input').val(num+1)
					$('.amount').text(num+1);
				}else{
					$(this).siblings('input').val(num)
					$('.amount').text(num);
				}
			}
		}
			
		if ( $(this).hasClass('quantity-increase')){
			if(num <= max_num){
		    	num ++;
				if(num>max_num){
					$(this).siblings('input').val(num-1)
				}else{
					$(this).siblings('input').val(num);
					$('.amount').text(num);				
			   }					
			}
		}

	});
}
prodMsg();

//选择商品关闭
function maskClose(){
	$('.r-close-btn').click(function (event){
		event.preventDefault()
//		console.log(this)
		$('.menu-mask,.spec-menu').fadeOut();
		
	})
}
maskClose();
//弹出商品选择栏
function popChoose(){
	$('#popChoose').click(function (){
		$('.menu-mask,.spec-menu').fadeIn();
	})
}
popChoose()
// 商品详情切换 
function commodtiy(){
	$('.tab-list li a').click(function(event){
		event.preventDefault();
		$(this).addClass('on').parent().siblings().children('.on').removeClass('on');
		var id=$(this).attr('href');
//		console.log(id)
        $(id).addClass('on').siblings('.deta.on').removeClass('on');
	})
}
commodtiy();

//评论切换
function comm(){
	$('.comment-nav .comment-tabs li').click(function (){
		$(this).addClass('tab-hover').siblings('.tab-hover').removeClass('tab-hover')
	})
}
comm();
// 价格提示
function orderPriceTip(){
	$('#moneyTip').click(function(){
		$('#moneyTip-pop').fadeIn();
	});
	$('.ship-btn').click(function(){
		$('#moneyTip-pop').fadeOut()
	})
}
orderPriceTip();

 $.extend($.fn,{
        fnTimeCountDown:function(d){
            this.each(function(){
                var $this = $(this);
                var o = {
                    hm: $this.find(".hm"),
                    sec: $this.find(".countDownTime-seconds"),
                    mini: $this.find(".countDownTime-min"),
                    hour: $this.find(".countDownTime-hour"),
                    day: $this.find(".countDownTime-day"),
//                  month:$this.find(".countDownTime-month"),
//                  year: $this.find(".countDownTime-year")
                };
                var f = {
                    haomiao: function(n){
                        if(n < 10)return "00" + n.toString();
                        if(n < 100)return "0" + n.toString();
                        return n.toString();
                    },
                    zero: function(n){
                        var _n = parseInt(n, 10);//解析字符串,返回整数
                        if(_n > 0){
                            if(_n <= 9){
                                _n = "0" + _n
                            }
                            return String(_n);
                        }else{
                            return "00";
                        }
                    },
                    dv: function(){
                        d = d || Date.UTC(2050, 0, 1); //如果未定义时间，则我们设定倒计时日期是2050年1月1日
                        var _d = $this.data("end") || d;
                        var now = new Date(),
                            endDate = new Date(_d);
                        //现在将来秒差值
                        //alert(future.getTimezoneOffset());
                        var dur = (endDate - now.getTime()) / 1000 , mss = endDate - now.getTime() ,pms = {
                          hm:"000",
                            sec: "00",
                            mini: "00",
                            hour: "00",
                            day: "00",
                            month: "00",
                            year: "0"
                        };
                        if(mss > 0){
                            pms.hm = f.haomiao(mss % 1000);
                            pms.sec = f.zero(dur % 60);
                            pms.mini = Math.floor((dur / 60)) > 0? f.zero(Math.floor((dur / 60)) % 60) : "00";
                            pms.hour = Math.floor((dur / 3600)) > 0? f.zero(Math.floor((dur / 3600)) % 24) : "00";
                            pms.day = Math.floor((dur / 86400)) > 0? f.zero(Math.floor((dur / 86400)) % 30) : "00";
                            //月份，以实际平均每月秒数计算
                            pms.month = Math.floor((dur / 2629744)) > 0? f.zero(Math.floor((dur / 2629744)) % 12) : "00";
                            //年份，按按回归年365天5时48分46秒算
                            pms.year = Math.floor((dur / 31556926)) > 0? Math.floor((dur / 31556926)) : "0";
                        }else{
                            pms.year=pms.month=pms.day=pms.hour=pms.mini=pms.sec="00";
                            pms.sec = "00";
                            //alert('结束了');
                            return;
                        }
                        return pms;
                    },
                    ui: function(){
                        if(o.hm){
                            o.hm.html(f.dv().hm);
                        }
                        if(o.sec){
                            o.sec.html(f.dv().sec);
                        }
                        if(o.mini){
                            o.mini.html(f.dv().mini);
                        }
                        if(o.hour){
                            o.hour.html(f.dv().hour);
                        }
                        if(o.day){
                            o.day.html(f.dv().day);
                        }
                        if(o.month){
                            o.month.html(f.dv().month);
                        }
                        if(o.year){
                            o.year.html(f.dv().year);
                        }
                        setTimeout(f.ui, 1);
                    }
                };
                f.ui();
            });
        }
    });
 function reducew(obj){
		//减
		var $this = $(obj);
		var num= parseInt($('.quantity').val());
		if(num <= 1){
			num = 1;
		}else{
			$this.siblings('input').val(num-1);
		};	
	};
			
	function plusw(obj){
		//加
		var num= parseInt($('.quantity').val());
		var $this = $(obj);
		$this.siblings('input').val(num+1)
	}

