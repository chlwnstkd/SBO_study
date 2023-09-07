<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="kopo.poly.dto.MailDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%
    // MailController 함수에서 model 객체에 저장된 값 불러오기
    List<MailDTO> rList = (List<MailDTO>) request.getAttribute("rList");
%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>공지 리스트</title>
    <link rel="stylesheet" href="/css/table.css"/>
</head>
<body>
<h2>공지사항</h2>
<hr/>
<br/>
<div class="divTable minimalistBlack">
    <div class="divTableHeading">
        <div class="divTableRow">
            <div class="divTableHead">순번</div>
            <div class="divTableHead">받는사람</div>
            <div class="divTableHead">메일제목</div>
            <div class="divTableHead">메일내용</div>
            <div class="divTableHead">발송시간</div>
        </div>
    </div>
    <div class="divTableBody">
        <%
            for (MailDTO dto : rList) {
        %>
        <div class="divTableRow">
            <div class="divTableCell"><%=CmmUtil.nvl(dto.getMailSeq())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(dto.getToMail())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(dto.getTitle())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(dto.getContents())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(dto.getSendTime())%>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
