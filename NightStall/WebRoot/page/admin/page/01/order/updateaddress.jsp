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
<link href="${ctxAdmin}/css/swiper-3.4.2.min.css" rel="stylesheet" type="text/css">
<link href="${ctxAdmin}/css/city-picker.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.city-picker-dropdown{
	-webkit-box-sizing:border-box;
}
</style>
<script src="${ctxAdmin}/js/city-picker.data.js"></script>
<script src="${ctxAdmin}/js/city-picker.js"></script>
<script src="${ctxAdmin}/js/main.js"></script>
<title>订单收货信息更新</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-add">
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>收货姓名：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${userShopping.receivername}" placeholder="" id="receivername" name="receivername" datatype="*1-200" nullmsg="收货姓名不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>收货移动电话：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${userShopping.receivermobile}" placeholder="" id="receivermobile" name="receivermobile" datatype="*1-200" nullmsg="收货移动电话不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">地区：</label>
			<div class="formControls col-8">
				<input id="city-picker3" name="receiverarea" class="form-control" readonly type="text" value="${area}" data-toggle="city-picker" style="width: 484px;height: 30px;line-height: 30px;">
				
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>详细地址：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${userShopping.receiveraddress}" placeholder="" id="receiveraddress" name="receiveraddress" datatype="*1-200" nullmsg="详细地址不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<input type="hidden" name="id" value="${userShopping.id}"/>
				<input type="hidden" name="orderId" value="${userShopping.orderId}"/>
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
            ajaxForm("${ctx}/admin/01/order/updateaddress/done.html", function(data) {
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
