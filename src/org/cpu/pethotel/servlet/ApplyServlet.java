package org.cpu.pethotel.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cpu.pethotel.po.Users;

/**
 * Servlet implementation class ApplyServlet
 */
@SuppressWarnings("unused")
public class ApplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyServlet() {
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
		
		String uname = request.getParameter("uname");
		String pettype =request.getParameter("pettype");
//		HttpSession session = request.getSession();
//		Users user = (Users) session.getAttribute("user");

		int uid = Integer.parseInt(request.getParameter("uid"));
		int pid = -(Integer.parseInt(request.getParameter("pid")));
		int poid = Integer.parseInt(request.getParameter("poid"));
		
		System.out.println("Apply:>"+uname);
		System.out.println("Apply:>"+pettype);
		System.out.println("Apply:>"+pid);
		
		request.setAttribute("uname", uname);
		request.setAttribute("pettype", pettype);
		request.setAttribute("uid", uid);
		request.setAttribute("pid", pid);
		request.setAttribute("poid",poid);
		String choice="3";
		request.setAttribute("choice",choice );
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/ApplyDeal.jsp");
		dispatcher.forward(request, response);
	}

}
