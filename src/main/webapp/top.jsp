<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/validate.js" type="text/javascript" language="javascript"></script>

<table width="210" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td><img src="images/1.jpg" width="827" height="72"/></td>
    </tr>
</table>

<table width="827" height="168" border="0" align="center" cellpadding="0" cellspacing="0" background="images/2.jpg">
    <tr>
        <td width="245">&nbsp;</td>
        <td width="276" valign="top">
            <table width="270" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td height="135" valign="bottom">
                        <marquee behavior="scroll"  direction="left" truespeed="truespeed" bgcolor="#FFFFFF"  hspace="1" vspace="1" scrollamount="2" scrolldelay="0" class="yellow">
                            <div id="bgclock"></div>
                        </marquee>
                    </td>
                </tr>
            </table>
        </td>

        <td width="306" valign="top">
            <table width="306" height="135" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td valign="bottom" align="right">
                        <c:if test="${empty sessionScope.userInfo}">
                            如果您想建立自己的个人网站，请您马上登录！
                        </c:if>
                        <c:if test="${!empty sessionScope.userInfo}">
                            <a href="netInformation?method=cannleNews">重新建站</a>
                        </c:if>
                        &nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

