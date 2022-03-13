<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="css/style.css" type="text/css" rel="stylesheet">
    <title>自动建站系统</title>
</head>
<%
    String net_individual = request.getParameter("net_individual");
    net_individual=new String(net_individual.getBytes("ISO8859_1"),"UTF-8");
%>
<body onLoad="clockon(bgclock);">
<jsp:include page="common.jsp" flush="true"></jsp:include>
<jsp:include page="top.jsp" flush="true"></jsp:include>
<table border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td width="195" height="388" align="center" valign="top">
            <jsp:include page="left.jsp" flush="true"></jsp:include>
        </td>
        <td align="center" valign="top">

            <table width="632" height="48" border="0" align="center" cellpadding="0" cellspacing="0" background="images/3.jpg">
                <tr>
                    <td width="64">&nbsp;</td>
                    <td width="568">修改网站基本信息</td>
                </tr>
            </table>
            <form name="form1" method="post" action="netInformation?method=updateNetInformation&path=${param.path}" onsubmit="return checkNetInformation(form1)">
                <table width="382" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                        <td width="78" height="50">网站名称：</td>
                        <td width="359">
                            <input type="text" name="net_individual" value="<%=net_individual %>">
                        </td>
                    </tr>
                    <tr>
                        <td height="50">网站类型：</td>
                        <td>
                            <input name="net_type" type="radio" class="cannleLine" value="新闻网站" checked>新闻网站&nbsp;&nbsp;
                            <input name="net_type" type="radio" class="cannleLine" value="博客网站">博客网站&nbsp;&nbsp;
                            <input name="net_type" type="radio" class="cannleLine" value="文章网站">文章网站
                        </td>
                    </tr>
                </table>
                <br><br>
                <input type="submit" name="Submit" value="提交">
                &nbsp;&nbsp;
                <input type="reset" name="Submit2" value="重置">
            </form>
            <p>${requestScope.result}</p></td>
    </tr>
</table>
<jsp:include page="down.jsp" flush="true"></jsp:include>
</body>
</html>
