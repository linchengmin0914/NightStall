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
<LINK rel="Bookmark" href="/favicon.ico" >
<LINK rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="${ctxAdmin}/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="${ctxAdmin}/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link href="${ctxAdmin}/skin/default/skin.css" rel="stylesheet" type="text/css" id="skin" />
<link href="${ctxAdmin}/lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
<link href="${ctxAdmin}/css/style.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>厦门市湖里区叶有清隆便利店 - 后台管理平台</title>
</head>
<body>
<!-- 头部 S -->
<%@include file="include/header.jsp" %>
<!-- 头部 E -->
<!-- 导航 S -->
<%@include file="include/left_nav.jsp" %>
<!-- 导航 E -->
<div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
	<div id="Hui-tabNav" class="Hui-tabNav">
		<div class="Hui-tabNav-wp">
			<ul id="min_title_list" class="acrossTab cl">
				<li class="active"><span title="平台首页" data-href="welcome.html">平台首页</span><em></em></li>
			</ul>
		</div>
		<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
	</div>
	<div id="iframe_box" class="Hui-article">
		<div class="show_iframe">
			<div style="display:none" class="loading"></div>
			<iframe scrolling="yes" frameborder="0" src="${ctx}/admin/welcome.html"></iframe>
		</div>
	</div>
</section>
<script type="text/javascript" src="${ctxAdmin}/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="${ctxAdmin}/lib/layer/1.9.3/layer.js"></script> 
<script type="text/javascript" src="${ctxAdmin}/js/H-ui.js"></script> 
<script type="text/javascript" src="${ctxAdmin}/js/H-ui.admin.js"></script> 
<script type="text/javascript">
/*资讯-添加*/
function article_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*产品-添加*/
function product_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*用户-添加*/
function member_add(title,url,w,h){
	layer_show(title,url,w,h);
}
</script> 
<script>
(function() {
  
})();
</script>
</body>
</html>
