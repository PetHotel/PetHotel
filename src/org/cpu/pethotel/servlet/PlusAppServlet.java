package org.cpu.pethotel.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cpu.pethotel.dao.IPostsDao;
import org.cpu.pethotel.dao.impl.PostsDaoImpl;

/**
 * Servlet implementation class PlusAppServlet
 */
public class PlusAppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlusAppServlet() {
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
	
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String strParams = request.getParameter("poidApp");
		System.out.println(strParams);
		String str[]=strParams.split(",");
		int poid =Integer.parseInt(str[0]);
		int app = Integer.parseInt(str[1]);
        IPostsDao postsDao = new PostsDaoImpl();
        boolean flag = postsDao.updateAppByPoid(poid, app);
		System.out.println(poid+"点赞："+flag);
		
	}

}
