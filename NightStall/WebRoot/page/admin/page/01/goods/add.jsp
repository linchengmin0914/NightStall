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
<title>商品管理</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-add">
		<div class="row cl">
			<label class="form-label col-2">归属门店：</label>
			<div class="formControls col-8">
				<select class="select" id="storeId" name="storeId" style="padding: 5px;" onchange="getCategoryByStoreId();">
					<c:forEach items="${y23Stores}" var="store">
					<option value="${store.id}">${store.name}</option>
					</c:forEach>
                </select>
                
			</div>
			<div class="col-2" style="font-size: 9px;color: #999;"></div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>商品名称：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="" id="goodsName" name="goodsName" datatype="*1-100" nullmsg="商品名称不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>封面：</label>
			<div class="formControls col-8">
				<span class="btn-upload form-group">
					<a href="javascript:void();" class="btn btn-primary upload-btn"><i class="Hui-iconfont">&#xe642;</i> 上传</a>
					<input id="pic" type="file" name="" accept="image/*" class="input-file">
				</span>
				<a href="javascript:void();" class="btn btn-danger upload-btn"  onclick="delIMg('img0','cover','pic')"><i class="Hui-iconfont">&#xe6e2;</i> 清空</a>
				
				<br><br>
				<img src="${ctx}/images/logo.png" id="img0" style="width: 150px; height: 100px;">
				<input type="hidden" id="cover" name="cover" value="images/logo.png"/>
			</div>
			<div class="col-2"> </div>
		</div>
		
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>归属分类：</label>
			<div class="formControls col-8">
				<select class="select" id="cateId" name="cateId" style="padding: 5px;">
					
                </select>
			</div>
			<div class="col-2" style="font-size: 9px;color: #999;"></div>
		</div>
		
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>商品价格：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="" id="price" name="price" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>商品单位：</label>
			<div class="formControls col-8">
				<select class="select" id="unit" name="unit" style="padding: 5px;">
					<option value="双">双</option>
					<option value="包">包</option>
					<option value="件">件</option>
					<option value="斤">斤</option>
					<option value="瓶">瓶</option>
					<option value="千克">千克</option>
					<option value="把">把</option>
					<option value="袋">袋</option>
					<option value="对">对</option>
					<option value="付">付</option>
					<option value="个">个</option>
					<option value="根">根</option>
					<option value="罐">罐</option>
					<option value="盒">盒</option>
					<option value="卷">卷</option>
					<option value="卡">卡</option>
					<option value="块">块</option>
					<option value="粒">粒</option>
					<option value="片">片</option>
					<option value="瓶">瓶</option>
					<option value="台">台</option>
					<option value="套">套</option>
					<option value="条">条</option>
					<option value="张">张</option>
					<option value="只">只</option>
					<option value="支">支</option>
					<option value="篮">篮</option>
                </select>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">商品原价：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="" id="original" name="original" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">商品标签：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="请输入商品标签，以便客户搜索更快搜索到心宜的商品" id="tags" name="tags" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">商品重量：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="" placeholder="" id="weight" name="weight" >
			</div>
			<div class="col-2">单位：KG</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>库存量：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="100" placeholder="" id="storeNum" name="storeNum" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">销售量：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="0" placeholder="" id="saleNum" name="saleNum" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<%--<div class="row cl">
			<label class="form-label col-2">运费模板：</label>
			<div class="formControls col-8">
				<select class="select" id="postageSel" multiple="multiple" style="width: 500px;">
					<c:forEach items="${postageTempletes}" var="templete" >
					<option value="${templete.id}" >${templete.name}</option>
					</c:forEach>
                </select>
			</div>
			<div class="col-2" style="font-size: 9px;color: #999;">不选择默认以通用模板进行</div>
		</div>
		--%>
		<div class="row cl">
			<label class="form-label col-2">描述：</label>
			<div class="formControls col-8">
				<textarea id="summary" name="summary" cols="" rows="" class="textarea"  placeholder="请输入商品的描述信息..." dragonfly="true" onKeyUp="textarealength(this,200)"></textarea>
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
				<input type="hidden" id="postageIds" name="postageIds" value=""/>
				<input class="btn btn-primary radius" id="submitBtn" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
		
		<input type="hidden" id="base64" name="base64" value=""/>
	</form>
</div>

<script type="text/javascript">
var content;
var enContent;

function getCategoryByStoreId() {
	var storeId = $("#storeId").val();
	var html = '';
	<c:forEach items="${categoryMap}" var="mymap" >
	var csid = ${mymap.key.storeId};
	var index = '';
	if(csid == storeId) {
		if("${mymap.key.id}" == "12") {
			index = ' selected="selected"';
		}
		html += '<option value="${mymap.key.id}" ' + index + '>${mymap.key.cateName}</option>';
	}
	</c:forEach>
	$("#cateId").html(html);
}

$(function(){
	//$("#postageSel").ySelect();
	getCategoryByStoreId();
	
	content = UE.getEditor('content',{savePath:['upload']});
	
	 $("#form-add").Validform({  
	 	btnSubmit:"#submitBtn",
        tiptype:2,
        beforeSubmit:function(){  
        	//$("#postageIds").val($("#postageSel").ySelectedValues(","));
        	
            ajaxForm("${ctx}/admin/01/goods/add/done.html", function(data) {
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
		$("#pic").attr("name", "pic") ;
		$("#pic2").attr("name", "") ;
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
		$("#pic2").attr("name", "pic") ;
		$("#pic").attr("name", "") ;
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
