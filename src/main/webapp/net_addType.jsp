<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/style.css" type="text/css" rel="stylesheet">
    <title>自动建站系统</title>
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
                    <td width="568">建立网站第二步：添加网站信息类别</td>
                </tr>
            </table>

            <form name="form" method="post" action="netInformation?method=addType" onsubmit="return checkType(form)">
                <table width="364" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="72" height="30">类别名称：</td>
                        <td width="170">
                            <input type="text" name="type_name">
                            <input type="hidden" name="maxId" value="${requestScope.maxId}">
                        </td>
                        <td width="119"><input type="submit" name="Submit" value=" 添加 "></td>
                    </tr>
                </table>
            </form>
            <p>${requestScope.result}</p>
        </td>
    </tr>
</table>
<jsp:include page="down.jsp" flush="true"></jsp:include>
</body>
</html>
