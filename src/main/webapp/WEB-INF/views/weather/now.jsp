<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="kopo.poly.dto.WeatherDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<WeatherDTO> rList1 = (List<WeatherDTO>) request.getAttribute("rList1");
    List<WeatherDTO> rList2 = (List<WeatherDTO>) request.getAttribute("rList2");
%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>날씨</title>
    <link rel="stylesheet" href="/css/table.css"/>
</head>
<body>
<h2>오늘의 날씨는?</h2>
<hr/>
<br/>
<div class="divTable minimalistBlack">
    <div class="divTableHeading">
        <div class="divTableRow">
            <div class="divTableHead">온도</div>
            <div class="divTableHead">날씨</div>
            <div class="divTableHead">현재 위치</div>
        </div>
    </div>
    <div class="divTableBody">
        <%
            for (WeatherDTO rDTO : rList1) {
        %>
        <div class="divTableRow">
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getTemp())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getSummary())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getLoc())%>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
<div class="divTable minimalistBlack">
    <div class="divTableHeading">
        <div class="divTableRow">
            <div class="divTableHead">날짜</div>
            <div class="divTableHead">오전 강수확률</div>
            <div class="divTableHead">오전 날씨</div>
            <div class="divTableHead">오후 강수확률</div>
            <div class="divTableHead">오전 날씨</div>
            <div class="divTableHead">최저 기온</div>
            <div class="divTableHead">최고 기온</div>
        </div>
    </div>
    <div class="divTableBody">
        <%
            for (WeatherDTO rDTO : rList2) {
        %>
        <div class="divTableRow">
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getTime())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getAmRain())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getAmWeather())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getPmRain())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getPmWeather())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getHighTemp())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(rDTO.getLowTemp())%>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>