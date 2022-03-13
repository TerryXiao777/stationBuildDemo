<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/style.css" type="text/css" rel="stylesheet">
    <title>自动建站系统</title>
    <script language="javascript">
        function Myopen(divID,id,name){ //根据传递的参数确定显示的层
            divID.style.display='block';
            divID.style.left=(document.body.clientWidth-240)/2;
            divID.style.top=(document.body.clientHeight-139)/2;
            form1.ids.value=id;
            form1.type_name.value=name;
        }
    </script>
</head>
<body onLoad="clockon(bgclock);">
<jsp:include page="common.jsp" flush="true"></jsp:include>
<jsp:include page="top.jsp" flush="true"></jsp:include>
<jsp:include page="net_Type.jsp" flush="true"></jsp:include>

<table border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td width="195" align="center" valign="top">
            <jsp:include page="left.jsp" flush="true"></jsp:include>	</td>
        <td align="center" valign="top">

            <table width="632" height="48" border="0" align="center" cellpadding="0" cellspacing="0" background="images/3.jpg">
                <tr>
                    <td width="64">&nbsp;</td>
                    <td width="568">查询网站信息类别</td>
                </tr>
            </table>
            <table width="435" height="23" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td><a href="net_addType.jsp">添加类别</a></td>
                </tr>
            </table>
            <table width="435" height="30" border="1" cellpadding="0" cellspacing="0">
                <tr align="center">
                    <td height="20">编号</td>
                    <td>名称</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${requestScope.typlist}" var="type">
                    <tr align="center">
                        <td height="20">${type.id}</td>
                        <td>${type.typeName}</td>
                        <td>

                            <c:if test="${type.typeName=='公告信息'||type.typeName=='焦点导读'}">
                                无操作
                            </c:if>


                            <c:if test="${type.typeName!='焦点导读'&&type.typeName!='公告信息'}">
                                <a  href="#" onClick="Myopen(type1,'${type.id}','${type.typeName}')">修改</a>&nbsp;&nbsp;&nbsp;
                                <a href="netInformation?method=deleteType&type_id=${type.id}">删除</a>
                            </c:if>

                        </td>
                    </tr>
                </c:forEach>
            </table>

            <div id="type1" style="position:width:240px; height:139px;display:none;">
                <br>
                <form name="form1" method="post" action="netInformation?method=updateType">
                    <table width="305" border="1" cellspacing="0" cellpadding="0">
                        <tr>
                            <td height="30">新类别名称：</td>
                            <td><input type="text" name="type_name">
                                <input name="ids"  type="hidden" value="">
                            </td>
                        </tr>
                        <tr>
                            <td height="30">&nbsp;</td>
                            <td><input type="submit" name="Submit" value=" 修改 "></td>
                        </tr>
                    </table>
                </form>
            </div>	</td>
    </tr>
</table>
<jsp:include page="down.jsp" flush="true"></jsp:include>
</body>
</html>
