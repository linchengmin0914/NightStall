<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../include/import.jsp" %>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@include file="../../include/style.jsp" %>
<title>系统参数管理</title>
</head>

<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
	 系统参数管理 
	 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i>
	 </a>
</nav>
<div class="pd-20">
	<div id="form-add" class="form form-horizontal">
		<div id="tab-system" class="HuiTab">
			<div class="tabBar cl">
				<span>系统参数设置</span>
			</div>
			
			<div class="tabCon">
				<div class="row cl">
					<label class="form-label col-2">${paramMap['peisong'].param}：</label>
					<div class="formControls col-8">
						<input type="text" class="input-text" value="${paramMap['peisong'].value}" placeholder="" id="peisong" name="peisong">
					</div>
					<div class="col-2">单位：元</div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-2">${paramMap['baoyou'].param}：</label>
					<div class="formControls col-8">
						<input type="text" class="input-text" value="${paramMap['baoyou'].value}" placeholder="" id="baoyou" name="baoyou">
					</div>
					<div class="col-2">单位：元</div>
				</div>
			
				<div class="row cl">
					<label class="form-label col-2">${paramMap['gdttime'].param}：</label>
					<div class="formControls col-8">
						<input type="text" class="input-text" value="${paramMap['gdttime'].value}" placeholder="" id="gdttime" name="gdttime">
					</div>
					<div class="col-2">单位：毫秒，默认2秒</div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-2">${paramMap['storePhone'].param}：</label>
					<div class="formControls col-8">
						<input type="text" class="input-text" value="${paramMap['storePhone'].value}" placeholder="" id="storePhone" name="storePhone">
					</div>
					<div class="col-2"></div>
				</div>
				
				<div class="row cl">
					<div class="col-10 col-offset-2">
						<input class="btn btn-primary radius" onclick="save2();" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
					</div>
				</div>
			</div>
		</div>
		
	</form>
</div>

<script type="text/javascript">
var tofserv;

$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	$.Huitab("#tab-system .tabBar span","#tab-system .tabCon","current","click","0");
	
	//tofserv = UE.getEditor('tofserv',{savePath:['upload']});
});


function save1() {
	var data = {
		"tofserv.code":"tofserv",
		"tofserv.value":tofserv.body.innerHTML,
		
		"yhxy.code":"yhxy",
		"yhxy.value":yhxy.body.innerHTML,
		
		"etofserv.code":"etofserv",
		"etofserv.value":etofserv.body.innerHTML,
		
		"eyhxy.code":"eyhxy",
		"eyhxy.value":eyhxy.body.innerHTML
	}
	save(data);
}

function save2() {
	var data = {
		"baoyou.code":"baoyou",
		"baoyou.value":$("#baoyou").val(),
		
		"peisong.code":"peisong",
		"peisong.value":$("#peisong").val(),
			
		"gdttime.code":"gdttime",
		"gdttime.value":$("#gdttime").val(),
		
		"storePhone.code":"storePhone",
		"storePhone.value":$("#storePhone").val()
	}
	save(data);
}

function save(data) {
	$.ajax({
        type: "POST",
        url:"${ctx}/admin/y23/system/param/update/done.html",
        data:data,
        dataType: "json",
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
            if(data.resCode == 1) {
				layer.msg(data.resDes, {icon: 6,time:1000});
			} else {
				layer.msg(data.resDes,{icon: 5,time:1000});
			}
        }
    });
}

</script>
</body>
</html>
