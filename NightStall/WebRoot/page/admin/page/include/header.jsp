<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../include/import.jsp" %>

<header class="Hui-header cl"> <a class="Hui-logo l" title="厦门市湖里区叶有清隆便利店后台管理平台" href="${ctx}/admin/index.html">厦门市湖里区叶有清隆便利店后台管理平台</a> <span class="Hui-subtitle l">V1.0</span>
	<ul class="Hui-userbar">
		<li>${sessionScope.sessionAdmin.nickname}</li>
		<li class="dropDown dropDown_hover"><a href="#" class="dropDown_A"> <i class="Hui-iconfont">&#xe6d5;</i></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="${ctx}/admin/logout.html">退出系统</a></li>
			</ul>
		</li>
		<li id="Hui-skin" class="dropDown right dropDown_hover"><a href="javascript:;" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="javascript:;" data-val="default" title="黑色">黑色</a></li>
				<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
				<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
				<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
				<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
				<li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
			</ul>
		</li>
	</ul>
	<a aria-hidden="false" class="Hui-nav-toggle" href="#"></a> 
</header>

<script type="text/javascript">
function wdliuyan(title,url,id,w,h){
	layer_show(title,url,w,h);
}
</script>
