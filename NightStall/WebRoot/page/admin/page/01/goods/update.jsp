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
				<select class="select" id="storeId" name="storeId" style="padding: 5px;">
					<c:forEach items="${y23Stores}" var="store">
					<option value="${store.id}" <c:if test="${entity.storeId eq store.id}">selected="selected"</c:if>>${store.name}</option>
					</c:forEach>
                </select>
                
			</div>
			<div class="col-2" style="font-size: 9px;color: #999;"></div>
		</div>
	
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>商品名称：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.goodsName}" placeholder="" id="goodsName" name="goodsName" datatype="*1-100" nullmsg="商品名称不能为空！" >
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
				<c:choose>
				<c:when test="${!empty(entity.cover)}">
				<img src="${ctx}/${entity.cover}" id="img0" style="width: 150px; height: 100px;">
				</c:when>
				<c:otherwise>
				<img src="${ctx}/images/photo_camera.png" id="img0" style="width: 150px; height: 100px;">
				</c:otherwise>
				</c:choose>
				<input type="hidden" id="cover" name="cover" value="${entity.cover}"/>
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
				<input type="text" class="input-text" value="${entity.price}" placeholder="" id="price" name="price" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>商品单位：</label>
			<div class="formControls col-8">
				<select class="select" id="unit" name="unit" style="padding: 5px;">
					<option value="件" <c:if test="${entity.unit eq '件'}">selected="selected"</c:if>>件</option> 
					<option value="斤" <c:if test="${entity.unit eq '斤'}">selected="selected"</c:if>>斤</option>
					<option value="包" <c:if test="${entity.unit eq '包'}">selected="selected"</c:if>>包</option>
					<option value="双" <c:if test="${entity.unit eq '双'}">selected="selected"</c:if>>双</option>
					<option value="瓶" <c:if test="${entity.unit eq '瓶'}">selected="selected"</c:if>>瓶</option>
					<option value="千克" <c:if test="${entity.unit eq '千克'}">selected="selected"</c:if>>千克</option>
					<option value="把" <c:if test="${entity.unit eq '把'}">selected="selected"</c:if>>把</option>
					<option value="袋" <c:if test="${entity.unit eq '袋'}">selected="selected"</c:if>>袋</option>
					<option value="对" <c:if test="${entity.unit eq '对'}">selected="selected"</c:if>>对</option>
					<option value="付" <c:if test="${entity.unit eq '付'}">selected="selected"</c:if>>付</option>
					<option value="个" <c:if test="${entity.unit eq '个'}">selected="selected"</c:if>>个</option>
					<option value="根" <c:if test="${entity.unit eq '根'}">selected="selected"</c:if>>根</option>
					<option value="罐" <c:if test="${entity.unit eq '罐'}">selected="selected"</c:if>>罐</option>
					<option value="盒" <c:if test="${entity.unit eq '盒'}">selected="selected"</c:if>>盒</option>
					<option value="卷" <c:if test="${entity.unit eq '卷'}">selected="selected"</c:if>>卷</option>
					<option value="卡" <c:if test="${entity.unit eq '卡'}">selected="selected"</c:if>>卡</option>
					<option value="块" <c:if test="${entity.unit eq '块'}">selected="selected"</c:if>>块</option>
					<option value="粒" <c:if test="${entity.unit eq '粒'}">selected="selected"</c:if>>粒</option>
					<option value="片" <c:if test="${entity.unit eq '片'}">selected="selected"</c:if>>片</option>
					<option value="瓶" <c:if test="${entity.unit eq '瓶'}">selected="selected"</c:if>>瓶</option>
					<option value="台" <c:if test="${entity.unit eq '台'}">selected="selected"</c:if>>台</option>
					<option value="套" <c:if test="${entity.unit eq '套'}">selected="selected"</c:if>>套</option>
					<option value="条" <c:if test="${entity.unit eq '条'}">selected="selected"</c:if>>条</option>
					<option value="张" <c:if test="${entity.unit eq '张'}">selected="selected"</c:if>>张</option>
					<option value="只" <c:if test="${entity.unit eq '只'}">selected="selected"</c:if>>只</option>
					<option value="支" <c:if test="${entity.unit eq '支'}">selected="selected"</c:if>>支</option>
					<option value="篮" <c:if test="${entity.unit eq '篮'}">selected="selected"</c:if>>篮</option>
                </select>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">商品原价：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.original}" placeholder="" id="original" name="original" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">商品标签：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.tags}" placeholder="请输入商品标签，以便客户搜索更快搜索到心宜的商品" id="tags" name="tags" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">商品重量：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.weight}" placeholder="" id="weight" name="weight" >
			</div>
			<div class="col-2">单位：KG</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>库存量：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.storeNum}" placeholder="" id="storeNum" name="storeNum" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">销售量：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.saleNum}" placeholder="" id="saleNum" name="saleNum" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<%--
		<div class="row cl">
			<label class="form-label col-2">运费模板：</label>
			<div class="formControls col-8">
				<c:set var="whusers" value=","/>
				<select class="select" id="postageSel" multiple="multiple" style="width: 500px;">
					<c:forEach items="${postageTempletes}" var="templete" > 
					<option value="${templete.id}" <c:if test="${fn:contains(entity.postageIds,templete.idStr.concat(whusers))}">selected="selected"</c:if>
					<c:if test="${fn:contains(entity.postageIds,whusers.concat(templete.idStr))}">selected="selected"</c:if>
					<c:if test="${!fn:contains(entity.postageIds,',')}"><c:if test="${fn:contains(entity.postageIds,templete.idStr)}">selected="selected"</c:if></c:if>
					>${templete.name}</option>
					</c:forEach> 
                </select>
			</div>
			<div class="col-2" style="font-size: 9px;color: #999;">不选择默认以通用模板进行</div>
		</div>
		--%>
		
		<div class="row cl">
			<label class="form-label col-2">描述：</label>
			<div class="formControls col-8">
				<textarea id="summary" name="summary" cols="" rows="" class="textarea"  placeholder="请输入商品的描述信息..." dragonfly="true" onKeyUp="textarealength(this,200)">${entity.summary }</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">内容：</label>
			<div class="formControls col-8">
				<script id="content" type="text/plain" name="content" style="height:300px;">${entity.content }</script>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<input type="hidden" name="id" value="${entity.id}"/>
				<input type="hidden" id="postageIds" name="postageIds" value="${entity.postageIds}"/>
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
	if(csid == storeId) {
		html += '<option value="${mymap.key.id}" <c:if test="${entity.cateId eq mymap.key.id}">selected="selected"</c:if>>${mymap.key.cateName}</option>';
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
        	
            ajaxForm("${ctx}/admin/01/goods/update/done.html", function(data) {
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
