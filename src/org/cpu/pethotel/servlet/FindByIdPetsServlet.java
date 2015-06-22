package org.cpu.pethotel.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cpu.pethotel.biz.IPetsBiz;
import org.cpu.pethotel.biz.impl.PetsBizImpl;
import org.cpu.pethotel.po.Pets;



/**
 * Servlet implementation class FindByIdPetsServlet
 */
public class FindByIdPetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindByIdPetsServlet() {
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
		// 步骤1：接受客户端数据
		int pid = Integer.parseInt(request.getParameter("pid"));
		// 步骤2：调用Biz层处理数据
		IPetsBiz petsBiz = new PetsBizImpl();
		Pets pets = petsBiz.findById(pid);
		// 步骤3：响应客户端（页面携带对象进行跳转）
		request.setAttribute("pets", pets);
		// 请求转发模式进行页面跳转
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/UpdatePets.jsp");
		dispatcher.forward(request, response);

	}

}
