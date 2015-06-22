package org.cpu.pethotel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cpu.pethotel.po.Users;

/**
 * Servlet implementation class Logout
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 步骤1：通过session获取登录的账号
		HttpSession session = request.getSession();
		Users user = new Users(); 
		
		try {
			user = (Users) session.getAttribute("user");

			//System.out.println(user.toString());
			// 步骤2：销毁session
			session.removeAttribute("user");
			session.invalidate();
			// 步骤3：从在线列表中移除当前登录账号
			List<String> usersOnLineList = (List<String>) this.getServletContext().getAttribute("usersOnLineList");
			usersOnLineList.remove(user.getUname());  // 移除
			this.getServletContext().setAttribute("usersOnLineList", usersOnLineList);  // 刷新在线列表
			// 步骤4：页面跳转
			response.sendRedirect("jsp/index.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			String error = "服务器错误退出失败，请重启浏览器";
			request.setAttribute("error", error);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Error.jsp");
			dispatcher.forward(request, response);
		}
				
	}

}
