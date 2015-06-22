package org.cpu.pethotel.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cpu.pethotel.biz.IUsersBiz;
import org.cpu.pethotel.biz.impl.UsersBizImpl;
import org.cpu.pethotel.po.Users;

/**
 * Servlet implementation class ShowOtherUserServlet
 */
public class ShowOtherUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowOtherUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		
		if(user.getUid() == uid){
			String url = "jsp/PersonIndex.jsp";
			response.sendRedirect(url);
		}else{
			IUsersBiz userBiz = new UsersBizImpl();
			user = userBiz.findById(uid);
			
			request.setAttribute("users", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/NPersonIndex.jsp");
			dispatcher.forward(request, response);
		}
	}

}
