<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/webcom/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
 
 <%@include file="/webcom/bracket.jsp"%>
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <link rel="shortcut icon" href="${ctx}/bracket/images/favicon.png" type="image/png">

  <title>用户登陆</title>
 
  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="js/html5shiv.js"></script>
  <script src="js/respond.min.js"></script>
  <![endif]-->
  <style type="text/css">
  		body.signin{
  			background: url('${ctx}/pc_gw/images/loginbanner.jpg') no-repeat;
  			background-size: 100% 100%;
  		}
  		.row::after,.row::before{
  			display: none;
  		}
  		.signinpanel form{
  			background: #75BCF3;
  			position: relative;
  			height: 271px;
  		}
  		.nomargin{
  			position: absolute;
  			top: -12px;
  			left: 30px;
  			color: #FFF;
  			background: url('${ctx}/pc_gw/images/logintit.png') no-repeat;
  			width: 381px;
  			height: 60px;
  			background-size: 100% 100%;
  			text-align: center;
  			font-size: 18px;
  			line-height: 60px;
  			overflow: hidden;
  			margin:  0 auto;
  			display: flex;
  			justify-content: center;
  			align-items: center;
  			margin-bottom: 20px;
  		}
  		.logos{
  			display: block;
  			float: left;
  			width: 80px;
  			height: 30px;
  			background: url('${ctx}/pc_gw/images/login.png') no-repeat;
  			background-size: 100% auto;
  		
  		}
  		.signup-footer{
  			border: none;
  			text-align: center;
  		}
  		#loginform a{
  			text-align: right;
  			float: right;
  			text-decoration: none;
  		}
  		video{
  			width: 100%;
  			height: 100%;
  			position: fixed;
  			top: 0;
  			right: 0;
  			bottom: 0;
  			left: 0;
  			z-index: -100;
  			display: block;
  			object-fit: fill;
  		}
  </style>
</head>

<body class="signin">
 	<video autoplay loop style="width: 100%;">
 		<source src="${ctx}/xmMobile/login.mp4" type="video/mp4"></source>
 	</video>
<section>
  
    <div class="signinpanel" >
       
        <div class="row" style="display: flex;justify-content: center;">
<!--            
            <div class="col-md-7">
                
                <div class="signin-info">
                    <div class="logopanel">
                        <h1><span></span> 资源管理系统 <span></span></h1>
                    </div>
                
                    <div class="mb20"></div>
                
                    <h5><strong></strong></h5>
                    <ul>
                        
                    </ul>
                    <div class="mb20"></div>
                   
                </div>
            
            </div>-->
            
            <div style="width: 448px;">
                
                <form method="post" id="loginform" name="loginForm"   action="j_spring_security_check">
                    <div class="nomargin"><i class="logos"></i>熊猫后台登陆系统</div>
                   
                
                    <input type="text" maxlength="11" id="j_username" onblur="checkName(this.value)" name="j_username" value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}" class="form-control uname" placeholder="账号" style="margin-top: 30px;"/>
                    <input type="password" id="j_password" name="j_password" class="form-control pword" placeholder="密码" />
                   
                    <button class="btn btn-block" style="background: #fff !important;margin-bottom: 30px;">登陆</button>
                    <a href="${ctx}/login!pcforgetpw.action">忘记密码</a><span style="float: right;margin: 0 10px;">|</span><a href="${ctx}/register.action">注册</a>
                </form>
            </div><!-- col-sm-5 -->
            
        </div><!-- row -->
        
        <div class="signup-footer">
            <div style="font-size: 18px;">
             	上合集团 &copy;
            </div>
            
        </div>
        
    </div><!-- signin -->
  
</section>

 
<script>
    jQuery(document).ready(function(){
        
        // Please do not use the code below
        // This is for demo purposes only
        var c = jQuery.cookie('change-skin');
        if (c && c == 'greyjoy') {
            jQuery('.btn-success').addClass('btn-orange').removeClass('btn-success');
        } else if(c && c == 'dodgerblue') {
            jQuery('.btn-success').addClass('btn-primary').removeClass('btn-success');
        } else if (c && c == 'katniss') {
            jQuery('.btn-success').addClass('btn-primary').removeClass('btn-success');
        }
    });
</script>

</body>
</html>
