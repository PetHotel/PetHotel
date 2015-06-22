package org.cpu.pethotel.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cpu.pethotel.biz.ILoginBiz;
import org.cpu.pethotel.biz.impl.LoginBizImpl;
import org.cpu.pethotel.po.Users;


/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
	@SuppressWarnings({ "unchecked" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		// 获取客户端传入的数据
		String uname = request.getParameter("uname").trim();
		String password = request.getParameter("password").trim();
		
		// 单点登录验证
		// 获取全局Application级别的用户在线列表
		List<String> usersOnLineList = (List<String>) this.getServletContext().getAttribute("usersOnLineList");
//		for(String s:usersOnLineList){
//			System.out.println(s);
//		}
		if(usersOnLineList == null){
			usersOnLineList = new ArrayList<String>();
		}
		// 判断当前登录用户是否存在于该在线列表中
		boolean singleLoginFlag;
		if(usersOnLineList.contains(uname)){
			singleLoginFlag = true;
		}else{
			singleLoginFlag = false;			
		}
		
		if(!singleLoginFlag){
			// 调用Biz层实现数据库验证
			ILoginBiz loginBiz = new LoginBizImpl();
			Users user = loginBiz.isLogin(uname, password);
			// 响应客户端		
			int msgcode = user == null ? 101 : 102;
			String url = "";
			switch (msgcode) {
			case 101:
				url = "jsp/userNotFound.jsp";
				System.out.println("------------101-------------");
				String error = "用户名或密码错误，请返回重试";
				request.setAttribute("error", error);
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Error.jsp");
				dispatcher.forward(request, response);
				break;
			case 102:
				url = "jsp/index.jsp";
				System.out.println("----------------102---------");
				HttpSession session = request.getSession(); // 获取session对象
				// 将登录的用户添加到在线列表中
				usersOnLineList.add(user.getUname());
				// 刷新在线用户列表
				this.getServletContext().setAttribute("usersOnLineList", usersOnLineList);
				session.setAttribute("user", user);
				System.out.println("------------Session added---------------");
				response.sendRedirect(url+"?isLogin=true");
				break;
			default:
				break;
			}
		
			
		}else{
			int msgcode = 103;
			request.setAttribute("msgcode", msgcode);
			System.out.println("-------------103-----------");
			String error = "用户已登录，登录失败";
			request.setAttribute("error", error);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Error.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

}
