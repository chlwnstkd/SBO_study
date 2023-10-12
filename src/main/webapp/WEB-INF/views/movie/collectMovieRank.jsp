<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String msg = CmmUtil.nvl((String) request.getAttribute("msg"));
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CGV 영화 수집 결과</title>
</head>
<body>
<%=msg%>
</body>
</html>