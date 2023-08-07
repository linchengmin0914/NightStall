<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="ctxAdmin" value="${pageContext.request.contextPath}/page/admin"/>
 <c:set var="imgPrefix" value="${ctx}"/>

<script>
var basePath = '${ctx}';
var bgBasePath = '${ctxAdmin}';
var imgPrefix = '${imgPrefix}';


function Alert(msg) {
	alert(msg);
}
</script>