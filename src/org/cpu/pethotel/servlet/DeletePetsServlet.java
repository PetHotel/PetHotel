package org.cpu.pethotel.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cpu.pethotel.biz.IPetsBiz;
import org.cpu.pethotel.biz.impl.PetsBizImpl;



/**
 * Servlet implementation class DeletePetsServlet
 */
public class DeletePetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePetsServlet() {
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
		// 步骤1：获取客户端传入的数据，同时将其转型为整型数据
		int pid = Integer.parseInt(request.getParameter("pid"));
		// 步骤2：创建Biz层对象，调用响应的方法实现对数据库的删除操作
		IPetsBiz petsBiz = new PetsBizImpl();
		boolean flag = petsBiz.delete(pid);
		// 步骤3：根据步骤2的操作结果进行页面的响应
		if(flag){
			response.sendRedirect("FindAllPetsByUidServlet?choice=0");
		}else{
			String error = "删除失败，请返回重试";
			request.setAttribute("error", error);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Error.jsp");
			dispatcher.forward(request, response);
//			response.sendRedirect("jsp/Error.jsp");
		}
	}

}
