package org.cpu.pethotel.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.cpu.pethotel.biz.IUsersBiz;
import org.cpu.pethotel.biz.impl.UsersBizImpl;
import org.cpu.pethotel.po.Users;

/**
 * Servlet implementation class AddFriendsServlet
 */
public class AddFriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriendsServlet() {
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
		// 步骤1：设置请求数据字符集
		request.setCharacterEncoding("UTF-8");
		// 步骤2：接受请求数据
		String searchfriends = request.getParameter("searchfriends");
		System.out.println("searchfriends>"+searchfriends);
		// 步骤3：调用Biz业务逻辑层的add方法完成新客户的添加操作
		IUsersBiz usersBiz = new UsersBizImpl();
		List<Users> lstFriends = usersBiz.searchFriends(searchfriends);
		// 步骤3：将获取的数据添加到request级别对象中
		request.setAttribute("lstFriends", lstFriends);
		for(Users u:lstFriends){
			System.out.println(u);
		}
		// 步骤4：使用请求转发模式完成界面的跳转（可以携带reuqest对象进行跳转）
		
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("user");
		System.out.println("-----------session----------->:"+user.toString());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/AddFriends.jsp");
		dispatcher.forward(request, response);
	}

}
