<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.page import="java.util.List"/>
<jsp:directive.page import="com.demo.bean.NetInforamtion"/>
<jsp:directive.page import="com.demo.bean.UserInfo"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/style.css" type="text/css" rel="stylesheet">
    <title>自动建站系统</title>
</head>
<jsp:useBean id="netInformationDao" scope="page" class="com.demo.dao.NetInformationDao"></jsp:useBean>
<jsp:useBean id="templeDao" scope="page" class="com.demo.dao.TempleDao"></jsp:useBean>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    UserInfo userInfo = null;
    String net_sql = "select * from t_net_inforamtion";
    String account = "";
    if(null!=session.getAttribute("userInfo")){
        userInfo = (UserInfo)session.getAttribute("userInfo");
        net_sql = "select * from t_net_inforamtion where account = ?";
        account = userInfo.getAccount();
    }
    request.setAttribute("netList",netInformationDao.queryNetList(net_sql,account));
%>
<body onLoad="clockon(bgclock);">
<jsp:include page="top.jsp" flush="true"></jsp:include>
<table width="813" height="311" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td valign="top" bgcolor="#EBEBED"  width="195">
            <jsp:include page="left.jsp" flush="true"></jsp:include></td>
        <td valign="top" >
            <table width="632" height="48" border="0" align="center" cellpadding="0" cellspacing="0" background="images/3.jpg">
                <tr>
                    <td width="64">&nbsp;</td>
                    <td width="568">
                        <c:if test="${!empty sessionScope.userInfo}">
                            <a href="net_addInformation.jsp">我马上建站</a>
                        </c:if>
                        <c:if test="${empty sessionScope.userInfo}">
                            选择网站模板
                        </c:if>

                    </td>
                </tr>
            </table>

            <table width="143" height="175" border="0" align="center" cellpadding="0" cellspacing="0">
                <%
                    List list = (List)request.getAttribute("netList");
                    int lineCount = 5 ;
                    int newsCount = list.size();
                    int rowCount = newsCount/lineCount;
                    if(newsCount/lineCount!=0){
                        rowCount++;
                    }
                %>
                <tr>
                    <%
                        for(int i = 0;i<newsCount;i++){
                            NetInforamtion net = (NetInforamtion)list.get(i);
                            String image=templeDao.queryTemObject(net.getNetType()).getTem();
                    %>
                    <td  height="163" align="center" >
                        <table width="105" height="149"  border="0" align="center" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="114" height="103">
                                    <div align="center">
                                        <img src="<%=basePath%><%=image%>" width="90" height="90">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td align="center" height="25">
                                    <a href="<%=net.getNetAddress()%>/index.html" target="_blank">
                                        <%=net.getNetIndividual() %></a>
                                </td>
                            </tr>
                            <%if(null!=userInfo) {
                                if(userInfo.getAccount().equals(net.getAccount())){
                            %>
                            <tr>
                                <td align="center" height="25">
                                    <a href="netInformation?method=enterNetInformation&net_id=<%=net.getId()%>&sign=<%=net.getSign()%>">修改内容</a>
                                </td>
                            </tr>
                            <%}} %>
                        </table>
                    </td>
                    <%
                            if(i%lineCount==lineCount-1){
                                out.print("</tr><tr>");
                            }
                        }
                        if(rowCount*lineCount-newsCount>0){
                            int overCount=rowCount*lineCount-newsCount;
                            for(int j=0;j<overCount;j++){
                                out.print("<td align=center>&nbsp;</td>");
                            }
                        }
                    %>
                </tr>
            </table>

        </td>
    </tr>
</table>

<jsp:include page="down.jsp" flush="true"></jsp:include>
</body>
</html>
