<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<c:forEach items="${lists}" var="lists">
			<tr>
				<td><c:out value="${lists.id}"></c:out></td>
				<td><c:out value="${lists.name}"></c:out></td>
				<td><c:out value="${lists.path}"></c:out></td>
				<td><c:out value="${lists.author}"></c:out></td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>