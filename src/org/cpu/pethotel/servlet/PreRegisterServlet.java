package org.cpu.pethotel.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cpu.pethotel.biz.IUsersBiz;
import org.cpu.pethotel.biz.impl.UsersBizImpl;
import org.cpu.pethotel.po.Users;

/**
 * Servlet implementation class PreRegisterServlet
 */
public class PreRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String uname =  new String(request.getParameter("uname").getBytes("iso8859-1"),
				"UTF-8");

		IUsersBiz userBiz = new UsersBizImpl();
		boolean flag = userBiz.findDupByUname(uname);
		String responseText = "none";
		if(flag){
			responseText ="exist";
		}
	    out.print(responseText);
		out.flush();
		out.close();
		
		
	}

}
