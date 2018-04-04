<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>dashuaigege</title>
</head>
<body>
	<c:if test="${!empty error} }">//使用JSTLL标签对登录错误返回的信息进行处理
		<font color="red"><c:out value="${error}" /></font>
	</c:if>
	<form action="<c:url value="/loginCheck.html" />" method="post">
		用户名: <input type="text" name="userName"> <br> 密码： <input
			type="password" name="password"> <br> <input
			type="submit" value="登录"> <input type="reset" value="重置">
	</form>
</body>
</html>