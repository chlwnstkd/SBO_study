<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String res = CmmUtil.nvl((String) request.getAttribute("res"));

    res = res.replaceAll("\n", " ");
    res = res.replaceAll("\"", " ");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>이미지 파일로부터 인식된 문자열 읽어주기</title>
    <link rel="stylesheet" href="/css/table.css"/>
    <script type="text/javascript" src="/static/js/jquery-3.6.0.min.js"></script>
    <script>

        const myOcrText = <%=res%>;

        $(document).ready(function () {
            $("#btnTextRead").on("click", function () {
                speak(myOcrText);
            })
        })

        function speak(text) {
            alert("1");
            if (typeof SpeechSynthesisUtterance === "undefined" ||
                typeof window.speechSynthesis === "undefined") {
                alert("이 브라우저는 문자읽기 기능을 지원하지 않습니다");
                return;
            }
            window.speechSynthesis.cancel()

            const speechMsg = new SpeechSynthesisUtterance()
            speechMsg.rate = 1;
            speechMsg.pitch = 1;
            speechMsg.lang = "ko-KR";
            speechMsg.text = text;

            window.speechSynthesis.speak(speechMsg);
        }
    </script>
</head>
<body>
<h2>이미지 파일로부터 인식된 문자열 읽어주기</h2>
<hr/>
<br/>
    <div class="divTable minimalistBlack">
        <div class="divTableHeading">
            <div class="divTableRow">
                    <div class="divTableHead">이미지로부터 텍스트 인식 결과</div>
            </div>
        </div>
            <div class="divTableBody">
                <div class="divTableRow">
                    <div class="divTableCell">
                        <%=res%>
                    </div>
                </div>
            </div>
        </div>
    <div>
        <button id="btnTextRead" type="button">문자열 읽기</button>
    </div>
</body>
</html>