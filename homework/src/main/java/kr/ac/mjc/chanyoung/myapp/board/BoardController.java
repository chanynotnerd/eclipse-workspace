package kr.ac.mjc.chanyoung.myapp.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.ac.mjc.chanyoung.myapp.common.mvc.Controller;
import kr.ac.mjc.chanyoung.myapp.common.mvc.ControllerException;
import kr.ac.mjc.chanyoung.myapp.common.mvc.RequestMapping;
import kr.ac.mjc.chanyoung.myapp.common.mvc.RequestMapping.RequestMethod;

@Controller
public class BoardController {

	BoardDao boardDao = null;

	public BoardController() {
		boardDao = new BoardDao();
	}

	/**
	 * 목록 화면
	 */
	@RequestMapping(value = "/board/boardList", method = RequestMethod.GET)
	public void boardList(HttpServletRequest request,
			HttpServletResponse response) {
		List<Board> boardList = boardDao.listBoards(10,1);
		request.setAttribute("boardList", boardList);
		forward(request, response, "board/boardList.jsp");
	}

	/**
	 * 조회 화면
	 */
	@RequestMapping(value = "/board/boardInfo", method = RequestMethod.GET)
	public void boardInfo(HttpServletRequest request,
			HttpServletResponse response) {
		String seq = request.getParameter("seq");
		Board board = boardDao.getBoard(seq);
		request.setAttribute("board", board);
		forward(request, response, "board/boardInfo.jsp");
	}

	/**
	 * 수정 화면
	 */
	@RequestMapping(value = "/board/boardEdit", method = RequestMethod.GET)
	public void boardEdit(HttpServletRequest request,
			HttpServletResponse response) {
		String seq = request.getParameter("seq");
		Board board = boardDao.getBoard(seq);
		request.setAttribute("board", board);
		forward(request, response, "board/boardEdit.jsp");
	}

	/**
	 * 추가 액션
	 */
	@RequestMapping(value = "/board/addBoard", method = RequestMethod.POST)
	public void addBoard(HttpServletRequest request,
			HttpServletResponse response) {

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");

		Board board = new Board(null, title, content, null, writer, 0);
		boardDao.addBoard(board);
		redirect(request, response, "/app/board/boardList");
	}

	/**
	 * 수정 액션
	 */
	@RequestMapping(value = "/board/updateBoard", method = RequestMethod.POST)
	public void updateBoard(HttpServletRequest request,
			HttpServletResponse response) {

		String seq = request.getParameter("seq");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");

		Board board = new Board(seq, title, content, null, writer, 0);
		boardDao.updateBoard(board);
		redirect(request, response, "/app/board/boardInfo?seq=" + seq);
	}

	/**
	 * 삭제 액션
	 */
	@RequestMapping(value = "/board/deleteBoard", method = RequestMethod.GET)
	public void deleteBoard(HttpServletRequest request,
			HttpServletResponse response) {

		String seq = request.getParameter("seq");
		boardDao.deleteBoard(seq);
		redirect(request, response, "/app/board/boardList");
	}

	/**
	 * forward
	 */
	private void forward(HttpServletRequest request,
			HttpServletResponse response, String jsp) {
		try {
			request.getServletContext()
					.getRequestDispatcher("/WEB-INF/jsp/" + jsp)
					.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
			throw new ControllerException(e);
		}
	}

	/**
	 * redirect
	 */
	private void redirect(HttpServletRequest request,
			HttpServletResponse response, String url) {
		try {
			response.sendRedirect(request.getContextPath() + url);
		} catch (IOException e) {
			e.printStackTrace();
			throw new ControllerException(e);
		}
	}
}