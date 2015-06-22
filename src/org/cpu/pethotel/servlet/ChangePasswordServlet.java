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
 * Servlet implementation class ChangePasswordServlet
 */
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
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
		IUsersBiz usersBiz = new UsersBizImpl();
		Users users = usersBiz.findById(uid);
		String oldpassword=request.getParameter("oldpassword");
		String newpassword=request.getParameter("newpassword");
		String renewpassword=request.getParameter("renewpassword");
		if(newpassword.equals(renewpassword)){
			System.out.println(users.getPassword()+"         "+oldpassword);
			boolean flag=(users.getPassword()).equals(oldpassword)?true:false;
			if(flag){
				users.setPassword(newpassword);
				boolean flag2=usersBiz.changePassword(users);
				
				if(flag2){
					response.sendRedirect("/PetHotel/LogoutServlet");
				}else{
					System.out.println("更新失败");
					String error = "更新失败";
					request.setAttribute("error", error);
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Error.jsp");
					dispatcher.forward(request, response);
				}
			}else{
				System.out.println("密码不对");
				String error = "密码不对";
				request.setAttribute("error", error);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Error.jsp");
				dispatcher.forward(request, response);
			}
		}else{
			System.out.println("密码不一致");
			String error = "密码不一致";
			request.setAttribute("error", error);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
