<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="include/import.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="${ctxAdmin}/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${ctxAdmin}/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="${ctxAdmin}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctxAdmin}/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>后台登录 - 厦门市湖里区叶有清隆便利店</title>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header" style="background: none;"></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form class="form form-horizontal" action="${ctx}/admin/index.html" method="post">
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-8">
          <input id="nickname" name="nickname" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-8">
          <input id="passwd" name="passwd" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row">
        <div class="formControls col-8 col-offset-3">
          <input name="" type="button" onclick="login()" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
       <div class="row">
        <div class="formControls col-8 col-offset-3">
          <%--<label for="online">
            <input type="checkbox" name="online" id="online" value="">
            使我保持登录状态</label>
        	--%>
        	<label for="online" style="color: red;">为避免可能上的兼容性问题，麻烦使用Chrome浏览器进行操作，<a target="_blank" href="http://lds1.whgjjy.cn/bdggllq.html?wordId=605596570705&bd_vid=11945603133339600091">马上下载</a></label>
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">Copyright 厦门市湖里区叶有清隆便利店</div>
<script type="text/javascript" src="${ctxAdmin}/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${ctxAdmin}/js/H-ui.js"></script> 
<script>
$(function(){
	
});

function login() {
	if($.trim($('#nickname').val()) == '') {
		Alert("请输入账号");
		return;
	}
	
	if($.trim($('#passwd').val()) == '') {
		Alert("请输入密码");
		return;
	}
	var data = {
		'nickname':$('#nickname').val(),
		'passwd':$('#passwd').val()
	}
	$.ajax({
        type: "POST",
        url:"${ctx}/admin/login/done.html",
        data:data,
        dataType: "json",
        error: function(request) {
            Alert("Connection error");
        },
        success: function(data) {
            if(data.resCode == 1) {
				window.location.href = "${ctx}/admin/index.html";
			} else {
				Alert(data.resDes);
			}
        }
    });
}
</script>
</body>
</html>
