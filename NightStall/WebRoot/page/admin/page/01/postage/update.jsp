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
<script type="text/javascript">
var num = 1;
</script>
<title>运费模板管理</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-add">
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>名称：</label>
			<div class="formControls col-10">
				<input type="text" class="input-text" value="${entity.name}" placeholder="" id="name" name="name" >
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-2"><span class="c-red">*</span>计费方式：</label>
			<div class="formControls col-10">
				<span class="select-box">
				<select id="type" name="type" class="select" onchange="changeMethod(this);">
					<option value="1" <c:if test="${entity.type eq 1}">selected="selected"</c:if>>包邮</option>
					<option value="2" <c:if test="${entity.type eq 2}">selected="selected"</c:if>>按件数</option>
				</select>
				</span>
			</div>
			<div class="col-2"> </div>
		</div>
		
		<div id="postage1" style="display:none;margin: 10px 0;">
			<div class="row cl">
				<label class="form-label col-2"><span class="c-red">*</span>包邮地区：</label>
				<div class="formControls col-10">
					<div id="cityDuoXuan" style="width:98%;overflow: hidden;" data-id="${fn:replace(entity.areas, ',', '-')}"></div>
				</div>
				<div class="col-2"> </div>
			</div>
		</div>
		
		<div id="postage2" style="display:none;margin: 10px 0;">
			<label class="form-label col-12" style="text-align: left;margin-left: 125px;font-size: 13px;">除指定地区外，其他地区采用默认运费：</label>
			<label class="form-label col-2"><span class="c-red">*</span>运送方式：</label>
			<div class="formControls col-10">
				<dl class="permission-list" style="border: none;">
					<dt>
						<label>默认运费</label>
						<input type="text" class="input-text" value="${entity.firstNum}" placeholder="" id="firstNum" style="width: 70px;"/>
						<label>件内，</label>
						<input type="text" class="input-text" value="${entity.firstPrice}" placeholder="" id="firstPrice" style="width: 70px;"/>
						<label>元，</label>
						<label>每增加</label>
						<input type="text" class="input-text" value="${entity.continueNum}" placeholder="" id="continueNum" style="width: 70px;"/>
						<label>件，</label>
						<label>增加运费</label>
						<input type="text" class="input-text" value="${entity.continuePrice}" placeholder="" id="continuePrice" style="width: 70px;"/>
						<label>元</label>
					</dt>
				</dl>
				<dl class="permission-list" style="border: none;">
					<input class="btn btn-primary radius" id="add1" type="button" value="+添加" onclick="addPaisong();">
				</dl>
				<dl id="paisongdl" class="permission-list" style="border: none;">
					<dd style="border-bottom: 1px solid #ccc;height: 20px;padding-left: 0px;">
						<label style="width: 200px;float: left;">运送到</label>
						<label style="width: 65px;float: left;margin-left: 10px;">首件</label>
						<label style="width: 65px;float: left;margin-left: 10px;">首费</label>
						<label style="width: 65px;float: left;margin-left: 10px;">续件</label>
						<label style="width: 65px;float: left;margin-left: 10px;">续费</label>
						<label style="width: 80px;float: left;margin-left: 10px;">操作</label>
					</dd>
					<!-- 派送列表 -->
					<c:set var="sum" value="1" />
					<c:forEach items="${y23PostageAreas}" var="y23PostageArea" varStatus="st">
					<dd id="paisongdd-${sum}" style="border-bottom: 1px solid #ccc;height: 30px;padding-left: 0px;">
						<div id="cityDuoXuan${sum}" class="paisongsel" style="float:left;width:190px;overflow: hidden;" data-id="${fn:replace(y23PostageArea.areas, ',', '-')}"></div>
						<input type="text" class="input-text firstNum" value="${y23PostageArea.firstNum}" placeholder="" name="firstNum" style="width: 65px;margin-left: 10px;"/>
						<input type="text" class="input-text firstPrice" value="${y23PostageArea.firstPrice}" placeholder="" name="firstPrice" style="width: 65px;margin-left: 10px;"/>
						<input type="text" class="input-text continueNum" value="${y23PostageArea.continueNum}" placeholder="" name="continueNum" style="width: 65px;margin-left: 10px;"/>
						<input type="text" class="input-text continuePrice" value="${y23PostageArea.continuePrice}" placeholder="" name="continuePrice" style="width: 65px;margin-left: 10px;"/>
						<input class="btn radius" type="button" value="删除" onclick="delPaisong(${sum});" style="font-size: 12px;margin-left: 10px;">
						<input type="hidden" class="paisongid" value="${y23PostageArea.id}"/>
					</dd>
					<script type="text/javascript">
					$('#cityDuoXuan${sum}').hsCheckData({
						isShowCheckBox: true,
						data: cityData
					});
					num = ${sum} + 1;
					</script>
					<c:set var="sum" value="${sum + 1}" />
					</c:forEach>
					
				</dl>
				<dl class="permission-list" style="border: none;margin-top: 25px;">
					<label for="sfby">指定条件包邮（可选）</label>
				</dl>
				<dl class="permission-list" style="border: none;">
					<input class="btn btn-primary radius" id="add2" type="button" value="+添加" onclick="addBaoyou();">
				</dl>
				<dl id="baoyoudl" class="permission-list" style="border: none;">
					<dd style="border-bottom: 1px solid #ccc;height: 20px;padding-left: 0px;">
						<label style="width: 210px;float: left;">地区</label>
						<label style="width: 180px;float: left;margin-left: 10px;">设置包邮数量</label>
						<label style="width: 80px;float: left;margin-left: 10px;">操作</label>
					</dd>
					<!-- 包邮列表 -->	
					<c:forEach items="${y23PostagePieces}" var="y23PostagePiece" varStatus="st">
					<dd id="baoyoudd-${sum}" style="border-bottom: 1px solid #ccc;height: 30px;padding-left: 0px;">
						<div id="cityDuoXuan${sum}" class="baoyousel" style="float:left;width:190px;overflow: hidden;" data-id="${fn:replace(y23PostagePiece.area, ',', '-')}"></div>
						<label style="width: 65px;margin-left: 10px;background-color: #efefef;padding: 6px;">满</label>
						<input type="text" class="input-text num" value="${y23PostagePiece.num}" placeholder="" name="num" style="width: 50px;"/>
						<label style="width: 65px;background-color: #efefef;padding: 6px;">件包邮</label>
						<input class="btn radius" type="button" value="删除" onclick="delBaoyou(${sum});" style="font-size: 12px;margin-left: 55px;">
						<input type="hidden" class="baoyouid" value="${y23PostagePiece.id}"/>
					</dd>
					<script type="text/javascript">
					$('#cityDuoXuan${sum}').hsCheckData({
						isShowCheckBox: true,
						data: cityData
					});
					num = ${sum} + 1;
					</script>
					<c:set var="sum" value="${sum + 1}" />
					</c:forEach>			
				</dl>
			</div>
		</div>
			
		
		<div class="row cl">
			<div class="col-10 col-offset-2" style="margin-top:10px;">
				<input type="hidden" id="tempId" value="${entity.id}"/>
				<input class="btn btn-primary radius" id="submitBtn" type="button" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
			</div>
		</div>
	</form>
</div>

<script type="text/javascript">
var entity = {}; 
$(function(){
	changeMethod($("#type"));
	
	$('#cityDuoXuan').hsCheckData({
		isShowCheckBox: true, 
		data: cityData
	});
	
	 $("#form-add").Validform({  
	 	btnSubmit:"#submitBtn",
        tiptype:2,
        beforeSubmit:function(){  
        	save();
        	console.log(entity.y23PostageTemplete);
        	if($.trim(entity.y23PostageTemplete.name) == '') {
        		layer.msg("请填写运费模板相关内容再提交，谢谢！",{icon: 5,time:1000});
        		return false;
        	}
        	
        	if($.trim(entity.y23PostageTemplete.type) == 1) {
        		if($.trim(entity.y23PostageTemplete.areas) == '') {
        			layer.msg("请填写运费包邮地区再提交，谢谢！",{icon: 5,time:1000});
            		return false;
        		}
        	}
        	
        	if($.trim(entity.y23PostageTemplete.type) == 2) {
        		if($.trim(entity.y23PostageTemplete.firstNum) == ''
        			||  $.trim(entity.y23PostageTemplete.firstPrice) == ''
        			||  $.trim(entity.y23PostageTemplete.continueNum) == ''
        			||  $.trim(entity.y23PostageTemplete.continuePrice) == ''
        			) {
        			layer.msg("请填写运费运送方式再提交，谢谢！",{icon: 5,time:1000});
            		return false;
        		}
        	}
        	
        	$.ajax({
                type: "POST",
                url:"${ctx}/admin/01/postage/update/done.html",
                data:JSON.stringify(entity),
                dataType: "json",
                timeout: 60000, 
                contentType : "application/json;charset=utf-8",
                error: function(request) {
                    Alert("Connection error");
                },
                success: function(data) {
                	if(data.resCode == 1) {
            			layer.msg(data.resDes, {icon: 6,time:1000});
            		} else {
            			layer.msg(data.resDes,{icon: 5,time:1000});
            		}
                	
                	setTimeout(function () { 
                		var index = parent.layer.getFrameIndex(window.name);
                		parent.$('.btn-refresh').click();
                		parent.layer.close(index);
                	}, 1000);
                }
            });
        	
            return false;  
        }  
    });  
	
	
	
});

function changeMethod(_this) {
	var type = $(_this).val();
	if(type == 1) {
		$("#postage1").show();
		$("#postage2").hide();
	} else {
		$("#postage2").show();
		$("#postage1").hide();
	}
}

function addPaisong() {
	var html = '<dd id="paisongdd-' + num + '" style="border-bottom: 1px solid #ccc;height: 30px;padding-left: 0px;">' +
	           		'<div id="cityDuoXuan' + num + '" class="paisongsel" style="float:left;width:190px;overflow: hidden;"></div>' +
	           		'<input type="text" class="input-text firstNum" value="" placeholder="" name="firstNum" style="width: 65px;margin-left: 10px;"/>' +
	           		'<input type="text" class="input-text firstPrice" value="" placeholder="" name="firstPrice" style="width: 65px;margin-left: 10px;"/>' +
	           		'<input type="text" class="input-text continueNum" value="" placeholder="" name="continueNum" style="width: 65px;margin-left: 10px;"/>' +
	           		'<input type="text" class="input-text continuePrice" value="" placeholder="" name="continuePrice" style="width: 65px;margin-left: 10px;"/>' +
	           		'<input class="btn radius" type="button" value="删除" onclick="delPaisong(' + num + ');" style="font-size: 12px;margin-left: 10px;">' +
	           		'<input type="hidden" class="paisongid" value=""/>' + 
	           	'</dd>';
	$("#paisongdl").append(html);
	$('#cityDuoXuan' + num).hsCheckData({
		isShowCheckBox: true,
		data: cityData
	});
	num = num + 1;
}

function delPaisong(_num) {
	$("#paisongdd-" + _num).remove();
}

function addBaoyou() {
	var html = '<dd id="baoyoudd-' + num + '" style="border-bottom: 1px solid #ccc;height: 30px;padding-left: 0px;">' + 
			   		'<div id="cityDuoXuan' + num + '" class="baoyousel" style="float:left;width:190px;overflow: hidden;"></div>' +
			   		'<label style="width: 65px;margin-left: 10px;background-color: #efefef;padding: 6px;">满</label>' +
			   		'<input type="text" class="input-text num" value="" placeholder="" name="num" style="width: 50px;"/>' +
			   		'<label style="width: 65px;background-color: #efefef;padding: 6px;">件包邮</label>' + 
			   		'<input class="btn radius" type="button" value="删除" onclick="delBaoyou(' + num + ');" style="font-size: 12px;margin-left: 55px;">' +
			   		'<input type="hidden" class="baoyouid" value=""/>' + 
			   	'</dd>';
	
	$("#baoyoudl").append(html);
	$('#cityDuoXuan' + num).hsCheckData({
		isShowCheckBox: true,
		data: cityData
	});
	num = num + 1;
}

function delBaoyou(_num) {
	$("#baoyoudd-" + _num).remove();
}

function save() {
	var name = $("#name").val();
	var tempId = $("#tempId").val();
	var type = $("#type").val();
	var areas = $("#cityDuoXuan").attr("data-id");
	areas = areas.replaceAll(/\-/g,",");
	var firstNum = $("#firstNum").val();
	var firstPrice = $("#firstPrice").val();
	var continueNum = $("#continueNum").val();
	var continuePrice = $("#continuePrice").val();
	
	var y23PostageTemplete = {}; 
	y23PostageTemplete.id = tempId;
	y23PostageTemplete.name = name;
	y23PostageTemplete.type = type;
	y23PostageTemplete.areas = areas;
	y23PostageTemplete.firstNum = firstNum;
	y23PostageTemplete.firstPrice = firstPrice;
	y23PostageTemplete.continueNum = continueNum;
	y23PostageTemplete.continuePrice = continuePrice;
	
	entity.y23PostageTemplete = y23PostageTemplete;
	
	
	var paisongselArr = $(".paisongsel");
	var y23PostageAreasArray = new Array();  
	for(var i = 0;i < paisongselArr.length; i++) {
		var _area = $($(".paisongsel")[i]).attr("data-id");
		var _id = $($(".paisongid")[i]).val();
		_area = _area.replaceAll(/\-/g,",");
		var _firstNum = $($(".firstNum")[i]).val();
		var _firstPrice = $($(".firstPrice")[i]).val();
		var _continueNum = $($(".continueNum")[i]).val();
		var _continuePrice = $($(".continuePrice")[i]).val();
		
		var data = {
			id:_id,
			areas:_area,	
			firstNum:_firstNum,	
			firstPrice:_firstPrice,	
			continueNum:_continueNum,	
			continuePrice:_continuePrice,	
		}
		y23PostageAreasArray.push(data);
	}
	entity.y23PostageAreas = y23PostageAreasArray;
	
	var baoyouselArr = $(".baoyousel");
	var y23PostagePiecesArray = new Array();  
	for(var i = 0;i < baoyouselArr.length; i++) {
		var _area = $($(".baoyousel")[i]).attr("data-id");
		var _id = $($(".baoyouid")[i]).val();
		_area = _area.replaceAll(/\-/g,",");
		var _num = $($(".num")[i]).val();
		
		var data = {
			id:_id,
			area:_area,	
			num:_num
		}
		y23PostagePiecesArray.push(data);
	}
	entity.y23PostagePieces = y23PostagePiecesArray;
	
	console.log(JSON.stringify(entity));
}
</script>
</body>
</html>
