<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<table width="195" height="27" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td><img src="images/4.jpg" width="195" height="47"></td>
    </tr>
</table>

<c:if test="${empty sessionScope.userInfo}">
    <table width="195" border="0" align="center" cellpadding="0" cellspacing="0" background="images/6.jpg">
    <form name="form2" method="post" action="userInfoServlet?method=userCheck">

    <tr>
        <td width="75" height="25" align="center">
            <div align="right">用户名：</div>
        </td>
        <td width="120">
        <input type="text" name="account" size="15" title="请输入用户名！" >
        </td>
    </tr>

    <tr>
        <td height="25" align="center">
            <div align="right">密&nbsp;&nbsp;码：</div>
        </td>
        <td>
            <input type="password" name="password" title="请输入密码！" size="15">
        </td>
    </tr>

    <tr>
        <td height="25" align="center">
            <div align="right">验证码：</div>
        </td>
        <td>
            <input name="code" type="text" title="请输入验证码！" size="15">
        </td>
    </tr>

    <tr>
        <td height="25">
            <div align="right"></div>
        </td>
        <td>
        <a href="javascript:refreshImg('validateCodeImg');">
            <img src="image.jsp" name="validateCodeImg" border=0 align="absmiddle" id="validateCodeImg" />
        </a>
        </td>
    </tr>

    <tr>
        <td height="40" colspan="2">
            <div align="center">
            <input type="submit" name="Submit2" value="登录">
            &nbsp;&nbsp;&nbsp;&nbsp;
            <input type="button" name="Submit3" value="注册" onClick="javascript:window.location.href='user_add.jsp'">
            </div>
        </td>
    </tr>

    </form>
    </table>
    <center>${requestScope.result}</center>
</c:if>

<c:if test="${!empty sessionScope.userInfo}">
    <table width="195" border="0" align="center" cellpadding="0" cellspacing="0" background="images/6.jpg">
        <tr>
            <td align="center">
                ${sessionScope.userInfo.account}<br><br>
                ${sessionScope.userInfo.realname}<br><br>
                ${sessionScope.userInfo.sex}<br><br>
                ${sessionScope.userInfo.age}岁<br><br>
                <a href="userInfoServlet?method=landOut">安全退出</a>
                <br><br>
            </td>
        </tr>
    </table>
</c:if>

<table width="195" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td width="195"><img src="images/8.jpg" width="195" height="208"></td>
    </tr>
</table>