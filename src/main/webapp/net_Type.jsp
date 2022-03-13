<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.ResultSet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:directive.page import="java.util.List"/>
<%@ page import="com.demo.tools.JDBConnection"%>

<jsp:useBean id="connection" scope="page"
     class="com.demo.tools.JDBConnection"></jsp:useBean>
<jsp:useBean id="netInformationDao" scope="page"
     class="com.demo.dao.NetInformationDao"></jsp:useBean>

<%
    Integer id=(Integer)session.getAttribute("maxId");
    request.setAttribute("net",netInformationDao.queryNetObject(id));
    String path=request.getServletPath();
    path=path.substring(1);
%>
<table width="826" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td bgcolor="EAEBED">
            <c:if test="${!empty sessionScope.maxId}">
                <br>
                <table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <td align="right">
                            网站类型：${requestScope.net.netType}&nbsp;&nbsp;
                            网站名称：<a href = "net_updateNet.jsp?net_individual=${requestScope.net.netIndividual}&path=<%=path%>">${requestScope.net.netIndividual}</a>
                        </td>
                    </tr>

                </table>
                <%
                    Integer maxId = (Integer) session.getAttribute("maxId");
                    StringBuilder sql = new StringBuilder();
                    sql.append("select id,type_name,(select count(*) from t_news where type_name = b.type_name and net_id = ?) as count from t_type b where net_id = ?");
                    connection = new JDBConnection();
                    String[] params={String.valueOf(maxId),String.valueOf(maxId)};
                    ResultSet rs = connection.queryByPsStatement(sql.toString(),params);
                    int i = 0;
                    try {
                        boolean flag = rs.next();
                        if(flag){
                            rs.beforeFirst();
                %>

                <table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <td>
                            <%
                                while(rs.next()){
                                    out.print("<a href=netInformation?method=queryNews&type_id="+rs.getInt("id")+">");
                                    out.print(rs.getString("type_name"));
                                    out.print("("+rs.getString("count")+"条)");
                                    out.print("</a>");
                                    out.print("&nbsp;&nbsp;");
                                    if(i==7){
                                        out.print("<br>");
                                    }
                                    i++;
                                }
                            %>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">
                            <a href="netInformation?method=queryNews">查询全部文章</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                            <a href="netInformation?method=queryType">查询文章的类别</a>&nbsp;&nbsp;|&nbsp;&nbsp;
                            <a href="net_addNews.jsp?maxId=<%=maxId%>">添加网站内容</a>
                        </td>
                    </tr>

                </table>
                <%
                        }
                    }
                    catch (Exception e) {
                    }
                %>
            </c:if>
            <br>

        </td>
    </tr>
</table>
