<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="kopo.poly.dto.NoticeDTO" %>
<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="kopo.poly.dto.OcrDTO" %>
<%
    List<OcrDTO> rList = (List<OcrDTO>) request.getAttribute("rList");
%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>공지 리스트</title>
    <link rel="stylesheet" href="/css/table.css"/>
    <script type="text/javascript" src="/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#download").on("click", function () { // 버튼 클릭했을때, 발생되는 이벤트 생성함(onclick 이벤트와 동일함)
                var filenameDiv = document.getElementById('filename');
                var filepathDiv = document.getElementById('filepath');
                var filename = filenameDiv.innerHTML;
                var filepath = filepathDiv.innerHTML;
                console.log(filename);
                console.log(filepath);
                $.ajax({
                    url: "/ocr/ocrDownload",
                    type: "post", // 전송방식은 Post
                    dataType: "JSON", // 전송 결과는 JSON으로 받기
                    data: {"filename" : filename,
                        "filepath" : filepath}, // form 태그 내 input 등 객체를 자동으로 전송할 형태로 변경하기
                    success: function () { // 호출이 성공했다면..
                        alert("다운로드 성공")
                    }
                })
            })
        })
    </script>
</head>
<body>
<h2>Ocr List</h2>
<hr/>
<br/>
<div class="divTable minimalistBlack">
    <div class="divTableHeading">
        <div class="divTableRow">
            <div class="divTableHead">다운로드</div>
            <div class="divTableHead">순번</div>
            <div class="divTableHead">서버에 저장된 파일 이름</div>
            <div class="divTableHead">서버에 저장된 파일의 경로</div>
            <div class="divTableHead">원래 파일명</div>
            <div class="divTableHead">파일 확장자</div>
            <div class="divTableHead">이미지 인식 문자열</div>
            <div class="divTableHead">최초 등록자</div>
            <div class="divTableHead">최초 등록일</div>
            <div class="divTableHead">최근 수정자</div>
            <div class="divTableHead">최근 수정일</div>
        </div>
    </div>
    <div class="divTableBody">
        <%
            for (OcrDTO dto : rList) {
        %>
        <div class="divTableRow">
            <div class="divTableCell">
                <button type="button" id="download">
                    다운로드
                </button>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(dto.getSeq())%>
            </div>
            <div class="divTableCell" id="filename"><%=CmmUtil.nvl(dto.getFileName())%>
            </div>
            <div class="divTableCell" id="filepath"><%=CmmUtil.nvl(dto.getFilePath())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(dto.getOrgFileName())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(dto.getExt())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(dto.getTextFormImage())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(dto.getRegId())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(dto.getRegDt())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(dto.getChgId())%>
            </div>
            <div class="divTableCell"><%=CmmUtil.nvl(dto.getChgDt())%>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
