<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
<script type="text/javascript">
	window.onload = function() { // 브라우저가 페이지 로딩을 끝냈을 때 이벤트
		document.getElementById("deleteBtn").onclick = onDelete;
	}
	function onDelete() {
		if (!confirm("삭제하시겠습니까?"))
			return false; // [Cancel] 할 경우 링크 진행 안됨
	}
</script>
</head>
<body>
	<h3>게시글 정보</h3>
	<p>글 번호: ${board.seq }</p>
	<p>제목: ${board.title }</p>
	<p>내용: ${board.content }</p>
	<p>작성 시간: ${board.regdate }</p>
	<p>작성자: ${board.writer }</p>
	<p>조회수: ${board.cnt }</p>
	<p>
		<a href="./app/board/boardList">목록</a> <a
			href="./app/board/boardEdit?seq=${board.seq }">수정</a> <a
			id="deleteBtn" href="./app/board/deleteBoard?seq=${board.seq }">삭제</a>
	</p>
</body>
</html>