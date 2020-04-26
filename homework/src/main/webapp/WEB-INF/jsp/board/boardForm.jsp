<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
</head>
<body>
	<h3>게시글 등록</h3>
	<form action="./app/board/addBoard" method="post">
		<p>
			제목: <input type="text" name="title" required autofocus />
		</p>
		<p>
			내용: <input type="text" name="content" required />
		</p>
		<p>
			작성자: <input type="text" name="writer" required />
		</p>
		<p>
			<button type="submit">저장</button>
			<button type="button" onclick="history.back()">취소</button>
		</p>
	</form>
</body>
</html>