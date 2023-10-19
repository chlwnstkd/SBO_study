<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="kopo.poly.dto.MovieDTO" %>
<%@ page import="kopo.poly.dto.FoodDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<FoodDTO> rList = (List<FoodDTO>) request.getAttribute("rList");
%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>이번주 점심 메뉴</title>
    <link rel="stylesheet" href="/css/table.css"/>
</head>
<body>
<h2>이번주 점심 메뉴는?</h2>
<hr/>
<br/>
<div class="divTable minimalistBlack">
    <div class="divTableHeading">
        <div class="divTableRow">
            <div class="divTableHead">요일</div>
            <div class="divTableHead">메뉴</div>
        </div>
    </div>
    <div class="divTableBody">
        <%
            for (FoodDTO rDTO : rList) {
        %>
        <div class="divTableRow">
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getDay())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getMenu())%>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>