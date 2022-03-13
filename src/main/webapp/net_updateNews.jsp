<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.page import="com.demo.tools.CurrentlyTime"/>
<jsp:directive.page import="com.demo.bean.News"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/style.css" type="text/css" rel="stylesheet">
    <title>自动建站系统</title>
</head>
<body onLoad="clockon(bgclock);">
<jsp:include page="common.jsp" flush="true"></jsp:include>
<jsp:include page="top.jsp" flush="true"></jsp:include>
<jsp:include page="net_Type.jsp" flush="true"></jsp:include>
<jsp:useBean id="typeDao" scope="request" class="com.demo.dao.TypeDao"></jsp:useBean>
<%
    Integer net_id = (Integer) session.getAttribute("maxId");
    request.setAttribute("list",typeDao.queryTypeList(net_id));
%>
<table border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td width="195" height="388" align="center" valign="top">
            <jsp:include page="left.jsp" flush="true"></jsp:include>
        </td>
        <td align="center" valign="top">

            <table width="632" height="48" border="0" align="center" cellpadding="0" cellspacing="0" background="images/3.jpg">
                <tr>
                    <td width="64">&nbsp;</td>
                    <td width="568">建立网站第三步：添加网站新闻</td>
                </tr>
            </table>
            <form name="form" method="post" action="netInformation?method=updateNews&id=${requestScope.news.id}" onsubmit="return checkNews(form)">
                <table width="435" height="319" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="76" height="25">标题:</td>
                        <td width="359">
                            <input type="text" name="con_title" value="${requestScope.news.conTitle}">
                        </td>
                    </tr>
                    <tr>
                        <td height="25">类型:</td>
                        <td>
                            <select name="type_id">
                                <c:forEach var="type" items="${requestScope.list}">
                                    <option value="${type.id}"
                                            <c:if test="${requestScope.news.typeId==type.id}">
                                                selected="selected"
                                            </c:if>
                                    >${type.typeName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td height="244">内容:</td>
                        <td>
                            <textarea name="con_content" cols="55" rows="20">${requestScope.news.conContent}</textarea>
                        </td>
                    </tr>
                    <tr>
                        <td height="25">发布时间：</td>
                        <td><%=CurrentlyTime.currentlyTime()%>
                            <input name="con_time" type="hidden" value="<%=CurrentlyTime.currentlyTime()%>">
                        </td>
                    </tr>
                </table>

                <input type="submit" name="Submit" value="添加">
                &nbsp;&nbsp;
                <input type="reset" name="Submit2" value="重置">
            </form>
            <p>${requestScope.result}</p>
        </td>
    </tr>
</table>
<jsp:include page="down.jsp" flush="true"></jsp:include>
</body>
</html>
