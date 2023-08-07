<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../include/import.jsp" %>

<aside class="Hui-aside">
	<input runat="server" id="divScrollValue" type="hidden" value="" />
	
	<div class="menu_dropdown bk_2">
		<c:forEach items="${sessionScope.rightMap}" var="map" varStatus="st">
		<dl id="menu-${st.index + 1}">
			<dt><i class="Hui-iconfont">${map.key.ico}</i> ${map.key.name}<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<c:forEach items="${map.value}" var="right"> 
						<li><a _href="${ctx}/${right.url}" href="javascript:void(0)">${right.name}</a></li>
					</c:forEach>
				</ul>
			</dd>
		</dl>
		</c:forEach>
		
	</div>
</aside>

<script type="text/javascript">
	function openwindow(url,name,iWidth,iHeight)  {  
		// url 转向网页的地址  
		// name 网页名称，可为空  
		// iWidth 弹出窗口的宽度  
		// iHeight 弹出窗口的高度  
		//window.screen.height获得屏幕的高，window.screen.width获得屏幕的宽  
		var iTop = (window.screen.height-30-iHeight)/2; //获得窗口的垂直位置;  
		var iLeft = (window.screen.width-10-iWidth)/2; //获得窗口的水平位置;  
		window.open(url,name,'height='+iHeight+',,innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');  
	}  
</script>
