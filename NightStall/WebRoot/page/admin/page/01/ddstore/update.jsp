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
<title>达达门店管理</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-add">
		<div class="row cl">
			<label class="form-label col-2">归属门店：</label>
			<div class="formControls col-8">
				<select class="select" id="storeId" name="storeId" style="padding: 5px;">
					<c:forEach items="${y23Stores}" var="store">
					<option value="${store.id}" <c:if test="${entity.storeId eq store.id}">selected="selected"</c:if>>${store.name}</option>
					</c:forEach>
                </select>
                
			</div>
			<div class="col-2" style="font-size: 9px;color: #999;"></div>
		</div>
	
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>门店编号：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.no}" placeholder="" id="no" name="no" datatype="*1-100" nullmsg="门店编号不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>门店名称：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.name}" placeholder="" id="name" name="name" datatype="*1-100" nullmsg="门店名称不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>联系电话：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.phone}" placeholder="" id="phone" name="phone" datatype="*1-100" nullmsg="联系电话不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">门店坐标(纬度)：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.latitude}" placeholder="" id="latitude" name="latitude">
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">门店坐标(经度)：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.longitude}" placeholder="" id="longitude" name="longitude">
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<input type="hidden" id="id" name="id" value="${entity.id}"/>
				<input class="btn btn-primary radius" id="submitBtn" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
		
		<input type="hidden" id="base64" name="base64" value=""/>
	</form>
</div>

<script type="text/javascript">

$(function(){
	 $("#form-add").Validform({  
	 	btnSubmit:"#submitBtn",
        tiptype:2,
        beforeSubmit:function(){  
            ajaxForm("${ctx}/admin/01/order/ddstore/update/done.html", function(data) {
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

function ajaxFileUpload(picInput) {
	$.ajax( {
        url : "${ctx}/uploadYs.html",
		type : 'post',
		data : {base64:$('#base64').val()},
		timeout : 60000,
		dataType: "json",
        success:function(data){
			console.log(data);
			$("#" + picInput).val(data.resDes);
            showDiv();
        },
        error:function(XmlHttpRequest,textStatus,errorThrown){
            console.log(XmlHttpRequest);
            console.log(textStatus);
            console.log(errorThrown);
        }
    });
}

function delIMg(img,imgHid,file){
	$("#" + img).attr("src","${ctx}/images/photo_camera.png");
	$("#" + imgHid).val("");
	$("#" + file).val("");
	
}
</script>
</body>
</html>
