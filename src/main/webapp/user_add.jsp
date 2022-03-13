<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/style.css" type="text/css" rel="stylesheet">
    <title>自动建站系统</title>
</head>
<body>
<jsp:include page="top.jsp" flush="true"></jsp:include>
<table width="813" height="311" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td valign="top" bgcolor="EBEBED"><table width="195" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="195"><img src="images/8.jpg" width="195" height="208"></td>
            </tr>
        </table></td>
        <td valign="top" >
            <table width="632" height="48" border="0" align="center" cellpadding="0" cellspacing="0" background="images/3.jpg">
                <tr>
                    <td width="64">&nbsp;</td>
                    <td width="568">用户注册</td>
                </tr>
            </table>

            <form name="userInfo" method="post" action="userInfoServlet?method=userAdd" onSubmit="return checkUserInfo(userInfo)">
                <table width="442" height="257" border="0" align="center" cellpadding="1" cellspacing="1">
                    <tr>
                        <td width="128" height="30" align="right">用户名：</td>
                        <td width="308"><input name="account" type="text" size="40" title="请输入用户名"></td>
                    </tr>

                    <tr>
                        <td height="30" align="right">密码：</td>
                        <td><input name="password" type="password" size="40" title="请输入密码"></td>
                    </tr>

                    <tr>
                        <td height="30" align="right">确认密码：</td>
                        <td><input name="repassword" type="password" size="40" title="请输入确认密码"></td>
                    </tr>

                    <tr>
                        <td height="30" align="right">真实姓名：</td>
                        <td><input name="realname" type="text" size="40" title="请输入用户真实姓名"></td>
                    </tr>

                    <tr>
                        <td height="30" align="right">性别：</td>
                        <td>
                            <input name="sex" type="radio" class="cannleLine" value="男" checked>
                            &nbsp;&nbsp;男&nbsp;&nbsp;&nbsp;&nbsp;
                            <input name="sex" type="radio" class="cannleLine" value="女">
                            &nbsp;&nbsp;女
                        </td>
                    </tr>

                    <tr>
                        <td height="30" align="right">年龄：</td>
                        <td><input name="age" type="text" size="40" title="请输入年龄"></td>
                    </tr>

                    <tr>
                        <td height="30" align="right">职业：</td>
                        <td><input name="profession" type="text" size="40" title="请输入职业名称"></td>
                    </tr>

                    <tr>
                        <td height="30" align="right">验证码：</td>
                        <td><input name="code" type="text" title="请输入校验码"></td>
                    </tr>

                    <tr>
                        <td height="30">&nbsp;</td>
                        <td>
                            <a href="javascript:refreshImg('validateCodeImg');">
                                <img src="image.jsp" name="validateCodeImg" border=0 align="absmiddle" id="validateCodeImg" />
                            </a>
                        </td>
                    </tr>

                    <tr>
                        <td height="30">&nbsp;</td>
                        <td>
                            <input type="submit" name="Submit" value="保存">&nbsp;&nbsp;&nbsp;
                            <input type="reset" name="Submit2" value="重置">&nbsp;&nbsp;&nbsp;
                            <input type="button" name="Submit3" value="返回" onClick="javascript:window.location.href='index.jsp'">
                        </td>
                    </tr>
                </table>
            </form>
            <center>${requestScope.result}</center>
        </td>
    </tr>
</table>
<jsp:include page="down.jsp" flush="true"></jsp:include>
</body>
</html>
