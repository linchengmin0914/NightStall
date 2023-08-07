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
<title>订单发货</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-add">
		<div class="formControls col-12">
			<dl class="permission-list">
				<dt>
					<label style="font-weight: bold;">订单退款申请信息</label>
				</dt>
				<dd>
					<dl class="cl permission-list2" >
						<label style="font-weight: bold;">退款原因：</label>
						<c:if test="${entity.status eq -1}">${entity.cancelDesc}</c:if>
						<c:if test="${entity.status ne -1}">商家主动发起</c:if>
					</dl>
				</dd>
				<dd style="margin-bottom:20px;">
					<dl class="cl permission-list2" >
						<label style="font-weight: bold;">申请时间：</label>
						<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.cancelTime}" />
						<c:if test="${empty(entity.cancelTime)}">${cancelTime}</c:if>
					</dl>
				</dd>
			</dl>
		</div>
		
		<div class="formControls col-12" style="margin-top: 20px;">
			<div class="col-offset-5">
				<c:if test="${entity.status ne -1}">
					<input type="hidden" name="cancelDesc" value="商家主动发起"/>
				</c:if>
				<input type="hidden" name="id" value="${entity.id}"/>
				<input type="hidden" name="status" value="${entity.status}"/>
				<input class="btn btn-primary radius" id="submitBtn" type="button" value="&nbsp;&nbsp;同意退款&nbsp;&nbsp;">
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
            ajaxForm("${ctx}/admin/01/order/cancel/done.html", function(data) {
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
