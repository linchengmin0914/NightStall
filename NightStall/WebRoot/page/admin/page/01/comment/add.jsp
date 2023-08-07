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
<link href="${ctxAdmin}/lib/ySelect/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${ctxAdmin}/lib/ySelect/ySelect.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctxAdmin}/lib/ySelect/ySelect.js"></script> 
<title>商品评价管理</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-add">
		<div class="row cl">
			<label class="form-label col-2">评价商品：</label>
			<div class="formControls col-8">
				<select class="select" id="goodsIdsSel" multiple="multiple" style="width: 500px;">
					<c:forEach items="${goodsList}" var="goods" > 
					<option value="${goods.id}" <c:if test="${goodsId eq goods.id}">selected="selected"</c:if>>${goods.goodsName}_${goods.storeName}</option>
					</c:forEach>
                </select>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">评价内容：</label>
			<div class="formControls col-8">
				<textarea id="content" name="content" cols="" rows="" class="textarea"  placeholder="" dragonfly="true">${entity.content }</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">评价时间：</label>
			<div class="formControls col-8">
				<input type="text" class="Wdate" id="d413" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<input type="hidden" id="goodsIds" name="goodsIds" value=""/>
				<input class="btn btn-primary radius" id="submitBtn" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">

$(function(){
	 $("#goodsIdsSel").ySelect();
	
	 $("#form-add").Validform({  
	 	btnSubmit:"#submitBtn",
        tiptype:2,
        beforeSubmit:function(){  
        	$("#goodsIds").val($("#goodsIdsSel").ySelectedValues(","));
        	
            ajaxForm("${ctx}/admin/01/user/comment/add/done.html", function(data) {
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
