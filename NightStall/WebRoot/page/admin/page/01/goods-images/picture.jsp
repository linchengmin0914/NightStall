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

<link href="${ctx}/css/client/pc/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="${ctx}/css/client/pc/style.css" rel="stylesheet" type="text/css">
<link href="${ctx}/css/client/pc/public.css" rel="stylesheet" type="text/css">

<title>图片管理</title>

</head>
<body>
<div>
	<form action="" method="post" class="form form-horizontal" id="form-add">
		<c:forEach items="${pictures}" var="pic">
		<div class="col-3 mt20 show-pic-div" pic="${pic.link}" cover="false">
			<a class="db rel" href="javascript:void(0);">
				<div class=""><img src="${ctx}/${pic.link}" height="235" style="width: 100%;"></div>
				<span class="remove-pic" onClick="removePic(this);" style="line-height:25px;">X</span>
			</a>
		</div>
       	</c:forEach>
	
		<div class="col-3 mt20" id="upload_btn_div">
			<a class="upload-out bdd">
				<img src="${ctx}/images/client/pc/photo_camera.png" width="40">
				<p>添加图片</p>
				<input id="uploadedfile" type="file" accept="image/*" multiple="multiple"/>
			</a>
		</div>
		
		<div class="row cl" style="float: left;">
			<div class="col-10 col-offset-5">
				<input type="hidden" name="goodsId" value="${goodsId}"/>
				<input type="hidden" id="base64" name="base64" value=""/>
				<input type="hidden" id="uploadPics" name="uploadPics" value=""/>
				<input type="hidden" id="isCovers" name="isCovers" value=""/>
				<input class="btn btn-primary radius" id="submitBtn" type="button" style="background-color:#5a98de;color:#FFF;border-color:#5a98de;width: 100px;" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
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
		 	var showPic = $('.show-pic-div');
			var arr = new Array();
			var arr2 = new Array();
			for(var i=0;i<showPic.length;i++) {
				arr.push($(showPic[i]).attr("pic"));
				arr2.push($(showPic[i]).attr("cover"));
			}
			
			var uploadPics = arr.join(',');
			var isCovers = arr2.join(',');
			$('#uploadPics').val(uploadPics);
			$('#isCovers').val(isCovers);
		 
            ajaxForm("${ctx}/admin/01/goods/images/picture/update.html", function(data) {
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

<script>
  $(function () {
   	$('#uploadedfile').on('change', function (e) {
  	  var files = e.target.files;
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
	          ajaxFileUpload();
	        },
	        notSupport: function(file) {
	          alert('浏览器不支持！');
	        }
	      });
  	  }
      
    });
	
  });

  function ajaxFileUpload() {
		 $.ajax( {
	        url : "${ctx}/uploadYs.html",
			type : 'post',
			data : {base64:$('#base64').val()},
			timeout : 60000,
			dataType: "json",
	        success:function(data){
				console.log(data);
	            var fengmiaNum = $('.fenmian').length;
	            var html = "";
	            if(fengmiaNum > 0) {
	            	html = '<div class="col-3 mt20 show-pic-div" pic="' + data.resDes + '" cover="false">' +
		            				'<a class="db rel" href="javascript:void(0);">' +
		            				'<div class=""><img src="' + imgPrefix + '/' + data.resDes + '" height="235" style="width: 100%;"></div>' +
		            				'<span class="remove-pic" onClick="removePic(this);" style="line-height:25px;">X</span>' +
		            				'</a>' +
		            				'</div>';
	            } else {
	            	//if(window.confirm('是否设置为封面？')){
		            	html = '<div class="col-3 mt20 show-pic-div" pic="' + data.resDes + '" cover="true">' +
		            				'<a class="db rel" href="javascript:void(0);">' +
		            				'<div class=""><img src="' + imgPrefix + '/' + data.resDes + '" height="235" style="width: 100%;"></div>' +
		            				'<span class="remove-pic" onClick="removePic(this);" style="line-height:25px;">X</span>' +
		            				'</a>' +
		            				'</div>';
		           	//}else{
		            //   html = '<div class="col-3 mt20 show-pic-div" pic="' + data[0].webUrl + '" cover="false">' +
		            //				'<a class="db rel" href="javascript:void(0);">' +
		            //				'<div class=""><img class="pct100" src="' + basePath + '/' + data[0].webUrl + '" height="235"></div>' +
		            //				'<span class="remove-pic" onClick="removePic(this);"><i class="icon icon-remove"></i></span>' +
		            //				'</a>' +
		            //				'</div>';
		            //}
	            }
	            
	            $("#upload_btn_div").before(html);
	            showDiv();
	        },
	        error:function(XmlHttpRequest,textStatus,errorThrown){
	            console.log(XmlHttpRequest);
	            console.log(textStatus);
	            console.log(errorThrown);
	        }
	    });
	}
	
	function removePic(_this) {
		$(_this).parent().parent().remove();
		var showPic = $('.show-pic-div');
		if(showPic.length > 0) {
			var obj = showPic[0];
			//$(obj).find('.remove-pic').first().before('<div class="fenmian">主图</div>');
			//$(obj).attr("cover","true");
		}
	}

</script>


</body>
</html>
