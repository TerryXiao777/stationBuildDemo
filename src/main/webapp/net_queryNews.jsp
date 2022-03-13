<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>自动建站系统</title>
    <link href="css/style.css" type="text/css" rel="stylesheet">
</head>
<body onLoad="clockon(bgclock);">
<jsp:include page="common.jsp" flush="true"></jsp:include>
<jsp:include page="top.jsp" flush="true"></jsp:include>
<jsp:include page="net_Type.jsp" flush="true"></jsp:include>

<table border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td width="195" align="center" valign="top">
            <jsp:include page="left.jsp" flush="true"></jsp:include>
        </td>
        <td align="center" valign="top">

            <table width="632" height="48" border="0" align="center" cellpadding="0" cellspacing="0" background="images/3.jpg">
                <tr>
                    <td width="64">&nbsp;</td>
                    <td width="568">查询新闻</td>
                </tr>
            </table>

            <c:if test="${!empty requestScope.newsList}">
                <div align="right"><a href="createNews">自动创建网站信息</a> &nbsp;&nbsp;</div><br>
            </c:if>
            <table width="549" border="1" cellpadding="0" cellspacing="0">
                <tr align="center">
                    <td width="119" height="25">新闻类型</td>
                    <td width="167">新闻标题</td>
                    <td width="140">发布时间</td>
                    <td width="113">操作</td>
                </tr>
                <c:forEach items="${requestScope.newsList}" var="news">
                    <tr align="center">
                        <td height="25">${news.typeName}</td>
                        <td>${news.conTitle}</td>
                        <td>${news.conTime}</td>
                        <td>
                            <a href="netInformation?method=queryNewsObject&id=${news.id}">修改</a>&nbsp;&nbsp;
                            <a href="netInformation?method=deleteNews&id=${news.id}">删除</a>
                        </td>
                    </tr>
                </c:forEach>

            </table>

            <br>
        </td>
    </tr>
</table>
<jsp:include page="down.jsp" flush="true"></jsp:include>
</body>
</html>
