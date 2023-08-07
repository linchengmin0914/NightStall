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
<title>达达参数管理</title>
</head>

<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
	 达达参数管理 
	 <a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i>
	 </a>
</nav>
<div class="pd-20">
	<div id="form-add" class="form form-horizontal">
		<div id="tab-system" class="HuiTab">
			<div class="tabBar cl">
				<span>达达参数设置</span>
			</div>
			
			<div class="tabCon">
				<div class="row cl">
					<label class="form-label col-2">${paramMap['useType'].param}：</label>
					<div class="formControls col-8">
						<select class="select" id="useType" name="useType" style="padding: 5px;">
						    <option value="1" <c:if test="${paramMap['useType'].value eq '1'}">selected="selected"</c:if>>正式</option>
						    <option value="0" <c:if test="${paramMap['useType'].value eq '0'}">selected="selected"</c:if>>测试</option> 
		                </select>
					</div>
					<div class="col-2">默认使用测试</div>
				</div>
			
				<div class="row cl">
					<label class="form-label col-2">${paramMap['ddtesturl'].param}：</label>
					<div class="formControls col-8">
						<input type="text" class="input-text" value="${paramMap['ddtesturl'].value}" placeholder="" id="ddtesturl" name="ddtesturl">
					</div>
					<div class="col-2"></div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-2">${paramMap['ddzsurl'].param}：</label>
					<div class="formControls col-8">
						<input type="text" class="input-text" value="${paramMap['ddzsurl'].value}" placeholder="" id="ddzsurl" name="ddzsurl">
					</div>
					<div class="col-2"></div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-2">${paramMap['ddappkey'].param}：</label>
					<div class="formControls col-8">
						<input type="text" class="input-text" value="${paramMap['ddappkey'].value}" placeholder="" id="ddappkey" name="ddappkey">
					</div>
					<div class="col-2"></div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-2">${paramMap['ddappsercret'].param}：</label>
					<div class="formControls col-8">
						<input type="text" class="input-text" value="${paramMap['ddappsercret'].value}" placeholder="" id="ddappsercret" name="ddappsercret">
					</div>
					<div class="col-2"></div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-2">${paramMap['testSourceId'].param}：</label>
					<div class="formControls col-8">
						<input type="text" class="input-text" value="${paramMap['testSourceId'].value}" placeholder="" id="testSourceId" name="testSourceId">
					</div>
					<div class="col-2"></div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-2">${paramMap['sourceId'].param}：</label>
					<div class="formControls col-8">
						<input type="text" class="input-text" value="${paramMap['sourceId'].value}" placeholder="" id="sourceId" name="sourceId">
					</div>
					<div class="col-2"></div>
				</div>
				
				<div class="row cl">
					<div class="col-10 col-offset-2">
						<input class="btn btn-primary radius" onclick="save1();" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
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
	
});


function save1() {
	var data = {
		"useType.code":"useType",
		"useType.value":$("#useType").val(),
			
		"ddzsurl.code":"ddzsurl",
		"ddzsurl.value":$("#ddzsurl").val(),
		
		"ddtesturl.code":"ddtesturl",
		"ddtesturl.value":$("#ddtesturl").val(),
		
		"ddappkey.code":"ddappkey",
		"ddappkey.value":$("#ddappkey").val(),
		
		"ddappsercret.code":"ddappsercret",
		"ddappsercret.value":$("#ddappsercret").val(),
		
		"testSourceId.code":"testSourceId",
		"testSourceId.value":$("#testSourceId").val(),
		
		"sourceId.code":"sourceId",
		"sourceId.value":$("#sourceId").val()
		
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
