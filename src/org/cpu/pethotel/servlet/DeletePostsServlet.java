package org.cpu.pethotel.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cpu.pethotel.biz.IPostsBiz;
import org.cpu.pethotel.biz.impl.PostsBizImpl;
import org.cpu.pethotel.po.Posts;

/**
 * Servlet implementation class DeletePostsServlet
 */
public class DeletePostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePostsServlet() {
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
		String strPoid = request.getParameter("poid");
		int poid = Integer.parseInt(strPoid);
		IPostsBiz postsBiz = new PostsBizImpl();
		boolean flag = postsBiz.updateByPoid(poid);
		String strFlag = "false";
		if(flag){
			strFlag = "true";
		}
		out.print(strFlag);
		out.flush();
		out.close();
		
	}

}
