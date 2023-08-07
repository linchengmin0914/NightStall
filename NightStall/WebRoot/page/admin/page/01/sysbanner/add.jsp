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
             '<span><img src="${ctx}/' + state.id + '" style="width: 80px;height: 100px" class="img-flag" /> ' + state.text + '</span>'
         );
         return $state;
     };
     function formatStateSelection(state) {
         if (!state.id) { return state.text; }
         var $state = $('<span>' + state.text + '</span>');
         return $state;
     };
</script>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-add">
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>标题：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="" id="title" name="title" datatype="*1-100" nullmsg="标题不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">封面：</label>
			<div class="formControls col-8">
				<span class="btn-upload form-group">
					<a href="javascript:void();" class="btn btn-primary upload-btn"><i class="Hui-iconfont">&#xe642;</i> 上传</a>
					<input id="pic" type="file" name="pic" accept="image/*" class="input-file">
				</span>
				<a href="javascript:void();" class="btn btn-danger upload-btn"  onclick="delIMg('img0','cover','pic')"><i class="Hui-iconfont">&#xe6e2;</i> 清空</a>
				<br><br>
				<img src="${ctx}/images/photo_camera.png" id="img0" style="height: 100px;">
				</a>
				<input type="hidden" id="cover" name="cover" value="${entity.cover}"/>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">活动背景：</label>
			<div class="formControls col-8">
				<select class="js-example-templating js-states form-control" style="width: 100%;" name="enCover" multiple="multiple" id="sel_3">
			        <option value="images/banner/bg01.png">电商促销海报背景</option>
			        <option value="images/banner/bg02.png">祥云红色喜庆中国风海报背景</option>
			        <option value="images/banner/bg03.png">兔年大吉红色喜庆春节元旦海报背景</option>
			        <option value="images/banner/bg04.png">红色中国风元旦节日海报背景</option>
			    </select>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<%--
		<div class="row cl">
			<label class="form-label col-2">链接地址：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="请输入点击活动要跳转的地址" id="url" name="url" >
			</div>
			<div class="col-2"> </div>
		</div>
		--%>
		
		<div class="row cl">
			<label class="form-label col-2">简要描述：</label>
			<div class="formControls col-8">
				<textarea id="summary" name="summary" cols="" rows="" class="textarea"  placeholder="请输入活动的描述信息..." dragonfly="true" onKeyUp="textarealength(this,200)"></textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">内容：</label>
			<div class="formControls col-8">
				<script id="content" type="text/plain" name="content" style="height:300px;"></script>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<input type="hidden" id="status" name="status" value="0"/>
				<input class="btn btn-primary radius" id="submitBtn" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
		<input type="hidden" id="base64" name="base64" value=""/>
	</form>
</div>

<script type="text/javascript">
var content;

$(function(){
	content = UE.getEditor('content',{savePath:['upload']});
	
	 $("#form-add").Validform({  
	 	btnSubmit:"#submitBtn",
        tiptype:2,
        beforeSubmit:function(){  
            ajaxForm("${ctx}/admin/01/sys/banner/add/done.html", function(data) {
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
	
	$("#pic").change(function(e){
		var files = e.target.files;
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
	
	$("#pic2").change(function(e){
		var files = e.target.files;
		$("#pic").attr("name", "pic") ;
		$("#pic1").attr("name", "") ;
		var objUrl = getObjectURL(this.files[0]) ;
		if (objUrl) {
			$("#img2").attr("src", objUrl) ;
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
	          ajaxFileUpload("enCover");
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
