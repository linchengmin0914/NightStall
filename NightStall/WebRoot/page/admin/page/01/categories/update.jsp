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
<title>商品分类管理</title>
<link href="${ctx}/js/select2/select2.min.css" rel="stylesheet" />
<script src="${ctx}/js/select2/select2.min.js"></script>
<title>活动管理</title>
<script>
    $(document).ready(function () {
         //带图片
         $("#sel_3").select2({
             placeholder: '请选择',
             maximumSelectionLength: 1,
             templateResult: formatStateResult,//选择时
             templateSelection: formatStateSelection//选择后
         });
            
     });
     //填充图片
     function formatStateResult(state) {
         if (!state.id) { return state.text; }
         var $state = $(
             '<span><img src="${ctx}/' + state.id + '" style="width: 50px;height: 50px" class="img-flag" /> ' + state.text + '</span>'
         );
         return $state;
     };
     function formatStateSelection(state) {
         if (!state.id) { return state.text; }
         var $state = $('<span>' + state.text + '</span>');
       	 $("#img0").attr('src','${ctx}/' + state.id);
       	 $("#cover").val(state.id);
         return $state;
     };
</script>
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
			<label class="form-label col-2"><span class="c-red">*</span>分类名称：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.cateName}" placeholder="" id="cateName" name="cateName" datatype="*1-100" nullmsg="分类名称不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">图标模板：</label>
			<div class="formControls col-8">
				<select class="js-example-templating js-states form-control" style="width: 100%;" multiple="multiple" id="sel_3">
			        <option value="images/ico/01.png" <c:if test="${'images/ico/01.png' eq entity.cover}">selected="selected"</c:if>>图标1</option>
			        <option value="images/ico/02.png" <c:if test="${'images/ico/02.png' eq entity.cover}">selected="selected"</c:if>>图标2</option>
			        <option value="images/ico/03.png" <c:if test="${'images/ico/03.png' eq entity.cover}">selected="selected"</c:if>>图标3</option>
			        <option value="images/ico/04.png" <c:if test="${'images/ico/04.png' eq entity.cover}">selected="selected"</c:if>>图标4</option>
			        <option value="images/ico/05.png" <c:if test="${'images/ico/05.png' eq entity.cover}">selected="selected"</c:if>>图标5</option>
			        <option value="images/ico/06.png" <c:if test="${'images/ico/06.png' eq entity.cover}">selected="selected"</c:if>>图标6</option>
			        <option value="images/ico/07.png" <c:if test="${'images/ico/07.png' eq entity.cover}">selected="selected"</c:if>>图标7</option>
			        <option value="images/ico/08.png" <c:if test="${'images/ico/08.png' eq entity.cover}">selected="selected"</c:if>>图标8</option>
			        <option value="images/ico/09.png" <c:if test="${'images/ico/09.png' eq entity.cover}">selected="selected"</c:if>>图标9</option>
			        <option value="images/ico/10.png" <c:if test="${'images/ico/10.png' eq entity.cover}">selected="selected"</c:if>>图标10</option>
			        <option value="images/ico/11.png" <c:if test="${'images/ico/11.png' eq entity.cover}">selected="selected"</c:if>>图标11</option>
			        <option value="images/ico/12.png" <c:if test="${'images/ico/12.png' eq entity.cover}">selected="selected"</c:if>>图标12</option>
			        <option value="images/ico/13.png" <c:if test="${'images/ico/13.png' eq entity.cover}">selected="selected"</c:if>>图标13</option>
			        <option value="images/ico/14.png" <c:if test="${'images/ico/14.png' eq entity.cover}">selected="selected"</c:if>>图标14</option>
			        <option value="images/ico/15.png" <c:if test="${'images/ico/15.png' eq entity.cover}">selected="selected"</c:if>>图标15</option>
			        <option value="images/ico/16.png" <c:if test="${'images/ico/16.png' eq entity.cover}">selected="selected"</c:if>>图标16</option>
			        <option value="images/ico/17.png" <c:if test="${'images/ico/17.png' eq entity.cover}">selected="selected"</c:if>>图标17</option>
			    </select>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>图标：</label>
			<div class="formControls col-8">
				<span class="btn-upload form-group">
					<a href="javascript:void();" class="btn btn-primary upload-btn"><i class="Hui-iconfont">&#xe642;</i> 上传</a>
					<input id="pic" type="file" name="" accept="image/*" class="input-file">
				</span>
				<a href="javascript:void();" class="btn btn-danger upload-btn"  onclick="delIMg('img0','cover','pic')"><i class="Hui-iconfont">&#xe6e2;</i> 清空</a>
				
				<br><br>
				<c:choose>
				<c:when test="${!empty(entity.cover)}">
				<img src="${ctx}/${entity.cover}" id="img0" style="height: 50px;">
				</c:when>
				<c:otherwise>
				<img src="${ctx}/images/photo_camera.png" id="img0" style="height: 50px;">
				</c:otherwise>
				</c:choose>
				<input type="hidden" id="cover" name="cover" value="${entity.cover}"/>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>排序序号：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.sort}" placeholder="" id="sort" name="sort" datatype="*1-100" nullmsg="排序序号不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<input type="hidden" value="0" name="pid"/>
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
            ajaxForm("${ctx}/admin/01/goods/categories/update/done.html", function(data) {
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
	 
	 $("#pic").change(function(e){
			var files = e.target.files;
			$("#pic").attr("name", "pic") ;
			var objUrl = getObjectURL(this.files[0]) ;
			if (objUrl) {
				$("#img0").attr("src", objUrl) ;
			}
			
			for(var i = 0; i < files.length; i++) {
	  		   hiddenDiv();
	  		   new html5ImgCompress(e.target.files[i], {
		        before: function(file) {
		        },
		        done: function (file, base64) {
		          $('#base64').val(base64);
		        },
		        fail: function(file) {
		        },
		        complate: function(file) {
		          ajaxFileUpload("cover");
		        },
		        notSupport: function(file) {
		          alert('浏览器不支持！');
		        }
		      });
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
