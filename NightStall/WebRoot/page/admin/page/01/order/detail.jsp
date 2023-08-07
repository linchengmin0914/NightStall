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
<title>订单详情</title>
</head>
<body>
<div class="pd-20">
	<form action="" method="post" class="form form-horizontal" id="form-add">
		<div class="row cl">
			<div class="formControls col-12">
				<dl class="permission-list">
					<dt>
						<label style="font-weight: bold;">一、订单总览</label>
					</dt>
					<dd style="margin-bottom:20px;">
						<dl class="cl permission-list2" style="width: 50%;float: left;">
							<label style="font-weight: bold;">订单编号：</label>${entity.orderno}
						</dl>
						<dl class="cl permission-list2" style="width: 50%;float: left;">
							<label style="font-weight: bold;">订单状态：</label>${entity.displayStatus}
						</dl>
					</dd>
					<dd style="margin-bottom:20px;">
						<dl class="cl permission-list2" style="width: 50%;float: left;">
							<label style="font-weight: bold;">订单金额(元)：</label>¥${entity.payment}<c:if test="${entity.oldPayment != 0 || !empty(entity.oldPayment)}">（原价：${entity.oldPayment}）</c:if>
						</dl>
						<dl class="cl permission-list2" style="width: 50%;float: left;">
							<label style="font-weight: bold;">订单运费(元)：</label>¥${entity.postage}
						</dl>
					</dd>
					<dd style="margin-bottom:20px;">
						<dl class="cl permission-list2" style="width: 50%;float: left;">
							<label style="font-weight: bold;">下单时间：
							</label><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.createTime}" />
						</dl>
						<dl class="cl permission-list2" style="width: 50%;float: left;">
							<label style="font-weight: bold;">发货时间：
							</label><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.sendtime}" />
							<c:if test="${empty(entity.sendtime)}">-</c:if>
						</dl>
					</dd>
					<dd style="margin-bottom:20px;">
						<dl class="cl permission-list2" style="width: 50%;float: left;">
							<label style="font-weight: bold;">交易完成时间：
							</label><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.endtime}" />
							<c:if test="${empty(entity.endtime)}">-</c:if>
						</dl>
						<dl class="cl permission-list2" style="width: 50%;float: left;">
							<label style="font-weight: bold;">交易关闭时间：
							</label><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${entity.closetime}" />
							<c:if test="${empty(entity.closetime)}">-</c:if>
						</dl>
					</dd>
					<dd style="margin-bottom:20px;">
						<dl class="cl permission-list2">
							<label style="font-weight: bold;">订单备注：</label>${entity.remark}
						</dl>
					</dd>
				</dl>
			</div>
			<div class="formControls col-12">
				<dl class="permission-list">
					<dt>
						<label style="font-weight: bold;">二、订单详情</label>
					</dt>
					<c:forEach items="${itemList}" var="item" varStatus="st">
					<dd>
						<dl class="cl permission-list2">
							<label style="font-weight: bold;color: red;">商品（${st.index + 1}）信息</label>
						</dl>
					</dd>
					<dd style="margin-bottom:20px;">
						<dl class="cl permission-list2" title="${item.goodsName}" style="width: 50%;float: left;text-indent: 2em;overflow: hidden;text-overflow: ellipsis;white-space: nowrap;">
							<label style="font-weight: bold;">商品名称：</label>${item.goodsName}
						</dl>
						<dl class="cl permission-list2" style="width: 50%;float: left;">
							<label style="font-weight: bold;">商品编号：</label>${item.goodsId}
						</dl>
					</dd>
					<dd style="margin-bottom:20px;">
						<dl class="cl permission-list2" style="width: 50%;float: left;text-indent: 2em;">
							<label style="font-weight: bold;">商品单价：</label>¥${item.price}
						</dl>
						<dl class="cl permission-list2" style="width: 50%;float: left;">
							<label style="font-weight: bold;">购买数量：</label>${item.quantity}件
						</dl>
					</dd>
					<dd>
						<dl class="cl permission-list2" style="text-indent: 2em;">
							<label style="font-weight: bold;">商品属性：</label>${item.propsStr}
						</dl>
					</dd>
					<dd>
						<dl class="cl permission-list2" style="text-indent: 2em;">
							<label style="font-weight: bold;">商品封面：</label><img src="${ctx}/${item.cover}" id="img0" style="height: 80px;">
						</dl>
					</dd>
					</c:forEach>
				</dl>
			</div>
			<div class="formControls col-12">
				<dl class="permission-list">
					<dt>
						<label style="font-weight: bold;">三、收货信息</label>
					</dt>
					<dd style="margin-bottom:20px;">
						<dl class="cl permission-list2" style="width: 50%;float: left;">
							<label style="font-weight: bold;">收货姓名：</label>${userShopping.receivername}
						</dl>
						<dl class="cl permission-list2" style="width: 50%;float: left;">
							<label style="font-weight: bold;">收货移动电话：</label>${userShopping.receivermobile}
						</dl>
					</dd>
					<dd>
						<dl class="cl permission-list2">
							<label style="font-weight: bold;">所在区域：</label>${userShopping.receiverarea}
						</dl>
					</dd>
					<dd>
						<dl class="cl permission-list2">
							<label style="font-weight: bold;">详细地址：</label>${userShopping.receiveraddress}
						</dl>
					</dd>
					<dd>
						<dl class="cl permission-list2">
							<label style="font-weight: bold;">房号：</label>${userShopping.houseno}
						</dl>
					</dd>
					<dd style="margin-bottom:20px;">
						<dl class="cl permission-list2" style="width: 50%;float: left;">
							<label style="font-weight: bold;">派送类型：</label>
							${userShopping.displayExpress}
							<c:if test="${empty(userShopping.displayExpress)}">-</c:if>
						</dl>
						<dl class="cl permission-list2" style="width: 50%;float: left;">
							<label style="font-weight: bold;">达达门店编号：</label>
							<c:if test="${userShopping.express eq 'dada'}">${userShopping.expressnum}</c:if>
							<c:if test="${empty(userShopping.expressnum || userShopping.express ne 'dada')}">-</c:if>
						</dl>
					</dd>
					
				</dl>
			</div>
			<div class="formControls col-12">
				<dl class="permission-list">
					<dt>
						<label style="font-weight: bold;">四、支付信息</label>
					</dt>
					<dd style="margin-bottom:20px;">
						<dl class="cl permission-list2" style="width: 50%;float: left;">
							<label style="font-weight: bold;">支付平台：</label>
							${userPayinfo.payplatform}
							<c:if test="${empty(userPayinfo.payplatform)}">-</c:if>
						</dl>
						<dl class="cl permission-list2" style="width: 50%;float: left;">
							<label style="font-weight: bold;">支付流水号：</label>
							${userPayinfo.platformnumber}
							<c:if test="${empty(userPayinfo.platformnumber)}">-</c:if>
						</dl>
					</dd>
					<dd style="margin-bottom:20px;">
						<dl class="cl permission-list2" >
							<label style="font-weight: bold;">支付状态：</label>
							${userPayinfo.platformstatus}
							<c:if test="${empty(userPayinfo.platformstatus)}">-</c:if>
						</dl>
					</dd>
				</dl>
			</div>
			<div class="formControls col-12">
				<dl class="permission-list">
					<dt>
						<label style="font-weight: bold;">五、评价信息</label>
					</dt>
					<dd>
						<dl class="cl permission-list2" >
							<label style="font-weight: bold;">评价内容：</label>
							${userComment.content}
							<c:if test="${empty(userComment.content)}">-</c:if>
						</dl>
					</dd>
					<dd style="margin-bottom:20px;">
						<dl class="cl permission-list2" >
							<label style="font-weight: bold;">评价时间：</label>
							<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${userComment.createTime}" />
							<c:if test="${empty(userComment.createTime)}">-</c:if>
						</dl>
					</dd>
				</dl>
			</div>
			
		</div>
	
	</form>
</div>

<script type="text/javascript">
$(function(){
	
});
</script>
</body>
</html>
