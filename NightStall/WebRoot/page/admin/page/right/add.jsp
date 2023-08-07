<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../include/import.jsp" %>

<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<%@include file="../include/style.jsp" %>
<title>权限管理</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-add">
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>名称：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="" id="name" name="name" datatype="*1-100" nullmsg="名称不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">链接地址：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="" id="url" name="url" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">归属类别：</label>
			<div class="formControls col-8">
				<span class="select-box">
				<select name="parentId" class="select">
					<option value="">无</option>
					<c:forEach items="${parents}" var="parent">
					<option value="${parent.id}">${parent.name}</option>
					</c:forEach>
				</select>
				</span>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">描述：</label>
			<div class="formControls col-8">
				<textarea id="des" name="des" cols="" rows="" class="textarea"  placeholder="请输入..." dragonfly="true" onKeyUp="textarealength(this,1000)"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/1000</p>
			</div>
			<div class="col-2"> </div>
		</div>
		
		
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<input class="btn btn-primary radius" id="submitBtn" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">
$(function(){
	
	 $("#form-add").Validform({  
	 	btnSubmit:"#submitBtn",
        tiptype:2,
        beforeSubmit:function(){  
            ajaxForm("${ctx}/admin/right/add/done.html", function(data) {
				if(data.resCode == 1) {
					layer.msg(data.resDes, {icon: 6,time:1000});
					
					setTimeout(function () { 
				        var index = parent.layer.getFrameIndex(window.name);
						parent.$('.btn-refresh').click();
						parent.layer.close(index);
				    }, 1000);
				} else {
					layer.msg(data.resDes,{icon: 5,time:1000});
				}
			});
            return false;  
        }  
    });  
	
	
	
});
</script>
</body>
</html>
