<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.request.contextPath }/" />
</head>
<body>
	<h3>게시글 목록</h3>
	<ul>
		<c:forEach var="board" items="${boardList }">
			<li>글번호: <a href="./app/board/boardInfo?seq=${board.seq }">${board.seq }</a>,
				제목: ${board.title }, 작성자: ${board.writer }
			</li>
		</c:forEach>
	</ul>
	<p>
		<input type = "button" value="등록"
			onclick="location.href='./app/board/boardForm'">
	</p>
</body>
</html>
