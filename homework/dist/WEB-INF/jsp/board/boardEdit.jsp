<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" /><!-- /myapp/ -->
</head>
<body>
	<h3>게시글 수정</h3>
	<form action="./app/board/updateBoard" method="post">
		<p>
			번호: <input type="text" name="seq" value="${board.seq }" readonly />
		</p>
		<p>
			이름: <input type="text" name="title" value="${board.title }" required
				autofocus />
		</p>
		<p>
			내용: <input type="text" name="content" value="${board.content }" required />
		</p>
		<p>
			<button type="submit">저장</button>
			<button type="button" onclick="history.back()">취소</button>
		</p>
	</form>
</body>
</html>