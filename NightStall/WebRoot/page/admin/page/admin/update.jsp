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
<title>用户管理</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-add">
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>账号：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.nickname}" placeholder="" id="nickname" name="nickname" datatype="*1-100" nullmsg="账号不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>密码：</label>
			<div class="formControls col-8">
				<input type="password" class="input-text" value="${entity.passwd}" placeholder="" id="passwd" name="passwd" datatype="*1-100" nullmsg="密码不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">电话号码：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.phoneno}" placeholder="" id="phoneno" name="phoneno" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">归属角色：</label>
			<div class="formControls col-8">
				<span class="select-box"> 
				<select name="roleId" class="select">
					<option value="">无</option>
					<c:forEach items="${roles}" var="role">
					<option value="${role.id}" <c:if test="${entity.roleId eq role.id}">selected="selected"</c:if>>${role.name}</option>
					</c:forEach>
				</select>
				</span>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">归属店铺：</label>
			<div class="formControls col-8">
				<span class="select-box">
				<select name="storeId" class="select">
					<option value="">无</option> 
					<c:forEach items="${stores}" var="store">
					<option value="${store.id}" <c:if test="${store.id eq entity.storeId}">selected="selected"</c:if>>${store.titile}</option>
					</c:forEach>
				</select>
				</span>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<input type="hidden" name="id" value="${entity.id}"/>
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
            ajaxForm("${ctx}/admin/admin/update/done.html", function(data) {
				if(data.resCode == 1) {
					layer.msg(data.resDes, {icon: 6,time:1000});
					
					setTimeout(function () { 
				        var index = parent.layer.getFrameIndex(window.name);
						parent.$('.search_btn').click();
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
