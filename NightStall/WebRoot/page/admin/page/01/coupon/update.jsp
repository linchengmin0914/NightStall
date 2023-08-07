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
<title>优惠券管理</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-add">
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>可用范围：</label>
			<div class="formControls col-8">
				<select class="select" id="scope" name="scope" style="height: 30px;" onchange="changeScope()">
					<option value="1" <c:if test="${entity.scope eq 1}">selected="selected"</c:if>>全场通用</option>
					<option value="2" <c:if test="${entity.scope eq 2}">selected="selected"</c:if>>指定商品</option>
					<option value="3" <c:if test="${entity.scope eq 3}">selected="selected"</c:if>>指定门店</option>
					<option value="4" <c:if test="${entity.scope eq 4}">selected="selected"</c:if>>指向私发</option>
                </select>
			</div>
			<div class="col-2" style="font-size: 9px;color: #999;"></div>
		</div>
		
		<div id="goods-div" class="row cl" >
			<label class="form-label col-2"><span class="c-red">*</span>指定商品：</label>
			<div class="formControls col-8">
				<c:set var="whusers" value=","/>
				<select class="select" id="postageSel" multiple="multiple" style="width: 500px;">
					<c:forEach items="${goodsList}" var="goods" >
					<option value="${goods.id}" <c:if test="${fn:contains(goodsIds,goods.idStr.concat(whusers))}">selected="selected"</c:if>
					<c:if test="${fn:contains(goodsIds,whusers.concat(goods.idStr))}">selected="selected"</c:if>
					<c:if test="${!fn:contains(goodsIds,',')}"><c:if test="${fn:contains(goodsIds,goods.idStr)}">selected="selected"</c:if></c:if>
					>${goods.goodsName}</option>
					</c:forEach>
                </select>
			</div>
			<div class="col-2"></div>
		</div>
		
		<div id="store-div" class="row cl" >
			<label class="form-label col-2"><span class="c-red">*</span>指定门店：</label>
			<div class="formControls col-8">
				<c:set var="whusers2" value=","/>
				<select class="select" id="storeSel" multiple="multiple" style="width: 500px;">
					<c:forEach items="${storeList}" var="store" >
					<option value="${store.id}" <c:if test="${fn:contains(storeIds,store.idStr.concat(whusers2))}">selected="selected"</c:if>
					<c:if test="${fn:contains(storeIds,whusers2.concat(store.idStr))}">selected="selected"</c:if>
					<c:if test="${!fn:contains(storeIds,',')}"><c:if test="${fn:contains(storeIds,store.idStr)}">selected="selected"</c:if></c:if>
					>${store.name}</option>
					</c:forEach>
                </select>
			</div>
			<div class="col-2"></div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>券类型：</label>
			<div class="formControls col-8">
				<select class="select" id="type" name="type" onchange="changeType()" style="height: 30px;">
					<option value="1" <c:if test="${entity.type eq 1}">selected="selected"</c:if>>满减券</option>
					<option value="2" <c:if test="${entity.type eq 2}">selected="selected"</c:if>>打折券</option>
                </select>
			</div>
			<div class="col-2" style="font-size: 9px;color: #999;"></div>
		</div>
		
		<div id="price-div" class="row cl" >
			<label class="form-label col-2"><span class="c-red">*</span>券面额：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.face}" placeholder="请输入优惠券金额，如20" id="face1" name="" >
			</div>
			<div class="col-2">元</div>
		</div>
		
		<div id="discount-div" class="row cl" >
			<label class="form-label col-2"><span class="c-red">*</span>券面额：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.face}" placeholder="请输入优惠券折扣，如0.95" id="face2" name="">
			</div>
			<div class="col-2"> </div>
		</div>
	
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>优惠券名称：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.name}" placeholder="" id="name" name="name" datatype="*1-100" nullmsg="优惠券名称不能为空！" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl" >
			<label class="form-label col-2"><span class="c-red">*</span>消费门槛：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.userequire}" placeholder="请输入优惠券消费门槛，如100" id="userequire" name="userequire" datatype="*1-100" nullmsg="消费门槛不能为空！" >
			</div>
			<div class="col-2">元</div>
		</div>
		
		<div class="row cl" >
			<label class="form-label col-2"><span class="c-red">*</span>优惠券库存：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.skunum}" placeholder="请输入优惠券库存，如100" id="skunum" name="skunum" datatype="*1-100" nullmsg="库存不能为空！" >
			</div>
			<div class="col-2">张</div>
		</div>
		
		<div class="row cl" >
			<label class="form-label col-2"><span class="c-red">*</span>每人最多可领取：</label>
			<div class="formControls col-8">
				<input type="text" class="input-text" value="${entity.moreget}" placeholder="请输入每人最多可领取张数，如1" id="moreget" name="moreget" datatype="*1-100" nullmsg="每人最多可领取张数不能为空！" >
			</div>
			<div class="col-2">张</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>可领取时间：</label>
			<div class="formControls col-4">
				<input type="text" class="Wdate" value="<fmt:formatDate value="${entity.getstart}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="getstartStr" name="getstartStr" placeholder="请输入优惠券可领取开始时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 290px;height: 30px;"/>
			</div>
			<div class="col-1" style="width: 30px;margin-left: -17px;margin-top: 3px;">至</div>
			<div class="formControls col-4">
				<input type="text" class="Wdate" value="<fmt:formatDate value="${entity.getend}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="getendStr" name="getendStr" placeholder="请输入优惠券可领取结束时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 290px;height: 30px;"/>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>券有效期类型：</label>
			<div class="formControls col-8">
				<select class="select" id="validtype" name="validtype" onchange="changeValidtype()" style="height: 30px;">
					<option value="1" <c:if test="${entity.validtype eq 1}">selected="selected"</c:if>>相对时间</option>
					<option value="2" <c:if test="${entity.validtype eq 2}">selected="selected"</c:if>>固定时间</option>
                </select>
			</div>
			<div class="col-2" style="font-size: 9px;color: #999;"></div>
		</div>
		
		<div class="xdtime-div row cl">
			<label class="form-label col-2">从发券第：</label>
			<div class="formControls col-4">
				<input type="text" class="input-text" value="${entity.validsnum}" placeholder="" id="validsnum" name="validsnum" style="width: 280px;">
			</div>
			<div class="col-1" style="width: 30px;margin-right: 10px;margin-left: -22px;margin-top: 3px;">天至</div>
			<div class="formControls col-4">
				<input type="text" class="input-text" value="${entity.validenum}" placeholder="" id="validenum" name="validenum" style="width: 280px;">
			</div>
			<div class="col-1" style="width: 30px;margin-left: -20px;margin-top: 3px;">天</div>
		</div>
		
		
		<div class="gdtime-div row cl">
			<label class="form-label col-2">可用时间：</label>
			<div class="formControls col-4">
				<input type="text" class="Wdate" value="<fmt:formatDate value="${entity.validstart}" pattern="yyyy-MM-dd HH:mm:ss"/>" id="validstartStr" name="validstartStr" placeholder="请输入优惠券可用开始时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 290px;height: 30px;"/>
			</div>
			<div class="col-1" style="width: 30px;margin-left: -17px;margin-top: 3px;">至</div>
			<div class="formControls col-4">
				<input type="text" class="Wdate" value="<fmt:formatDate value="${entity.validend}" pattern="yyyy-MM-dd HH:mm:ss"/>"  id="validendStr" name="validendStr" placeholder="请输入优惠券可用结束时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" style="width: 290px;height: 30px;"/>
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2">券说明：</label>
			<div class="formControls col-8">
				<textarea id="summary" name="summary" cols="" rows="" class="textarea"  placeholder="请输入商品的描述信息..." dragonfly="true" onKeyUp="textarealength(this,200)">${entity.summary}</textarea>
				<p class="textarea-numberbar"><em class="textarea-length">0</em>/200</p>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<div class="col-10 col-offset-2">
				<input type="hidden" name="id" value="${entity.id}"/>
				<input type="hidden" id="goodsIds" name="goodsIds" value="${goodsIds}"/>
				<input type="hidden" id="storeIds" name="storeIds" value="${storeIds}"/>
				<input class="btn btn-primary radius" id="submitBtn" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
		
		<input type="hidden" id="base64" name="base64" value=""/>
	</form>
</div>

<script type="text/javascript">
$(function(){
	changeScope();
	changeType();
	changeValidtype();
	$("#postageSel").ySelect();
	$("#storeSel").ySelect();
	
	
	 $("#form-add").Validform({  
	 	btnSubmit:"#submitBtn",
        tiptype:2,
        beforeSubmit:function(){  
        	$("#goodsIds").val($("#postageSel").ySelectedValues(","));
        	$("#storeIds").val($("#storeSel").ySelectedValues(","));
        	
            ajaxForm("${ctx}/admin/01/coupon/update/done.html", function(data) {
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

function changeType() {
	var type = $("#type").val();
	if(type == 1) {
		$("#price-div").show();
		$("#face1").attr("name","face");
		$("#face2").attr("name","face2");
		$("#discount-div").hide();
	} else {
		$("#price-div").hide();
		$("#face1").attr("name","face1");
		$("#face2").attr("name","face");
		$("#discount-div").show();
	}
}

function changeScope() {
	var scope = $("#scope").val();
	if(scope == 2) {
		$("#goods-div").show();
		$("#store-div").hide();
	} else if(scope == 3) {
		$("#goods-div").hide();
		$("#store-div").show();
	} else {
		$("#goods-div").hide();
		$("#store-div").hide();
	}
}

function changeValidtype() {
	var validtype = $("#validtype").val();
	if(validtype == 1) {
		$(".xdtime-div").show();
		$(".gdtime-div").hide();
	} else {
		$(".xdtime-div").hide();
		$(".gdtime-div").show();
	}
}
</script>
</body>
</html>
