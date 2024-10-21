package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import action.ActionForward;
import action.BookCreateAction;
import action.BookDeleteAction;
import action.BookListAction;
import action.BookReadAction;
import action.BookUpdateAction;
import action.MemberDupAction;
import action.MemberInfoAction;
import action.MemberLoginAction;
import action.MemberLogoutAction;
import action.MemberRegisterAction;

/**
 * Servlet implementation class BasicServlet
 */
@WebServlet("*.do")
public class BasicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 한글처리
		request.setCharacterEncoding("utf-8");

		String requestUri = request.getRequestURI(); // /login.do
		String contextPath = request.getContextPath();
		String cmd = requestUri.substring(contextPath.length()); // /login.do

		// http://localhost:8090/list.do

		Action action = null;
		if (cmd.equals("/list.do")) {
			action = new BookListAction("/book/list.jsp");
		} else if (cmd.equals("/read.do")) {
			action = new BookReadAction("/book/read.jsp");
		} else if (cmd.equals("/modify.do")) {
			action = new BookReadAction("/book/modify.jsp");
		} else if (cmd.equals("/update.do")) {
			action = new BookUpdateAction("/read.do");
		} else if (cmd.equals("/delete.do")) {
			action = new BookDeleteAction("/list.do");
		} else if (cmd.equals("/create.do")) {
			action = new BookCreateAction("/read.do");
		} else if (cmd.equals("/login.do")) {
			action = new MemberLoginAction("/list.do");
		} else if (cmd.equals("/logout.do")) {
			action = new MemberLogoutAction("/");
		} else if (cmd.equals("/info.do")) {
			action = new MemberInfoAction("/member/login.jsp");
		} else if (cmd.equals("/register.do")) {
			action = new MemberRegisterAction("/member/login.jsp");
		} else if (cmd.equals("/dup.do")) {
			action = new MemberDupAction("member/checkid.jsp");
		}

		ActionForward af = null;

		try {
			af = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (af.isRedirect()) {
			response.sendRedirect(af.getPath());
		} else {
			RequestDispatcher rd = request.getRequestDispatcher(af.getPath());
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}