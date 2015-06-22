package org.cpu.pethotel.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cpu.pethotel.biz.IUsersBiz;
import org.cpu.pethotel.biz.impl.UsersBizImpl;
import org.cpu.pethotel.po.Users;


/**
 * Servlet implementation class UserChangeServlet
 */
public class FindUsersByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindUsersByIdServlet() {
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
		int uid = Integer.parseInt(request.getParameter("uid"));
		// 步骤2：调用Biz层处理数据
		IUsersBiz usersBiz = new UsersBizImpl();
		Users users = usersBiz.findById(uid);
		
		// 步骤3：响应客户端（页面携带对象进行跳转）
		boolean isAdopt =users.isAdopt();
		String strAdopt=null;
		if(isAdopt){
			 strAdopt="领养";
		}else{
			strAdopt="不领养";
		}
		request.setAttribute("users", users);
		request.setAttribute("strAdopt", strAdopt);
		// 请求转发模式进行页面跳转
		System.out.println("页面跳转");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/updateUsers.jsp");
		dispatcher.forward(request, response);
		
	}

}
