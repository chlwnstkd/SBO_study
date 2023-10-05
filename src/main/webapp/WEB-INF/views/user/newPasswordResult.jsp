<%@ page import="kopo.poly.dto.UserInfoDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String msg = CmmUtil.nvl((String) request.getAttribute("msg"));

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>로그인하기</title>
    <link rel="stylesheet" href="/css/table.css"/>
    <script type="text/javascript" src="/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">

        // HTML로딩이 완료되고, 실행됨
        $(document).ready(function () {

            //로그인 이동
            $("#btnLogin").on("click", function () {
                location.href = "/user/login";
            })

        })
    </script>
</head>
<body>
<h2>로그인하기</h2>
<hr/>
<br/>
<form id="f">
    <div class="divTable minimalistBlack">
        <div class="divTableBody">
            <div class="divTableRow">
                <div class="divTableCell">
                    <%=msg%>
                </div>
            </div>
        </div>
    </div>
    <div>
        <button id="btnLogin" type="button">로그인</button>
    </div>
</form>
</body>
</html>