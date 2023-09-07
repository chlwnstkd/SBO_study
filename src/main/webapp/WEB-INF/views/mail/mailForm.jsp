<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>메일 작성하기</title>
    <link rel="stylesheet" href="/css/table.css"/>
    <script type="text/javascript" src="/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">

        function doSubmit() {
            $.ajax({
                url: "/mail/sendMail",
                type: "post",
                dataType: "JSON",
                data: $("#f").serialize(),
                success: function (json) {
                    alert(json.msg);
                }
            })
        }
        function doSend() {

            let f = document.getElementById("f"); // form 태그

            // Ajax 호출해서 글 등록하기
            $.ajax({
                    url: "/mail/mailInsert",
                    type: "post", // 전송방식은 Post
                    // contentType: "application/json",
                    dataType: "JSON", // 전송 결과는 JSON으로 받기
                    data: $("#f").serialize(), // form 태그 내 input 등 객체를 자동으로 전송할 형태로 변경하기
                    success: function (json) { // /notice/noticeInsert 호출이 성공했다면..
                        alert(json.msg); // 메시지 띄우기
                        location.href = "/mail/mailList"; // 공지사항 리스트 이동
                    }
                }
            )
        }
    </script>
</head>
<body>
<h2>메일 작성하기</h2>
<hr/>
<br/>
<form id="f">
    <div class = "divTable minimalistBlack">
        <div class = "divTableBody">
            <div class = "divTableRow">
                <div class = "divTableCell">받는 사람</div>
                <div class = "divTableCell"><input type="text" name="toMail" maxlength="100" style="width: 95%"/></div>
            </div>
            <div class = "divTableRow">
                <div class = "divTableCell">메일 제목</div>
                <div class = "divTableCell"><input type="text" name="title" maxlength="100" style="width: 95%"/></div>
            </div>
            <div class = "divTableRow">
                <div class = "divTableCell">메일 내용</div>
                <div class = "divTableCell"><textarea name="contents" style="width: 95%; height: 400px"></textarea></div>
            </div>
        </div>
    </div>
    <div>
        <button id="btnSend" onclick="doSubmit(); doSend()" type="button">메일 발송</button>
        <button type="reset">다시 작성</button>
    </div>
</form>
</body>
</html>