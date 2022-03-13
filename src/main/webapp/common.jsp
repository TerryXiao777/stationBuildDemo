<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:if test="${empty sessionScope.userInfo}">
    <%
        out.print("<script language=javascript>alert('您与服务器已经断开，请重新登录');window.location.href='index.jsp';</script>");
    %>
</c:if>
