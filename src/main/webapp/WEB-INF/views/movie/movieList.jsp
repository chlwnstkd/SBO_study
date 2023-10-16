<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="kopo.poly.dto.MailDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="kopo.poly.dto.MovieDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<MovieDTO> rList = (List<MovieDTO>) request.getAttribute("rList");
%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CGV 영화 순위 결과</title>
    <link rel="stylesheet" href="/css/table.css"/>
</head>
<body>
<h2>영화 순위 정보</h2>
<hr/>
<br/>
<div class="divTable minimalistBlack">
    <div class="divTableHeading">
        <div class="divTableRow">
            <div class="divTableHead">순위</div>
            <div class="divTableHead">제목</div>
            <div class="divTableHead">평점</div>
            <div class="divTableHead">개봉일</div>
        </div>
    </div>
    <div class="divTableBody">
        <%
            for (MovieDTO rDTO : rList) {
        %>
        <div class="divTableRow">
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getMovieRank())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getMovieNm())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getScore())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getOpenDay())%>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>