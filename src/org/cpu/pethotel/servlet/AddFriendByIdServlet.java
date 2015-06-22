package org.cpu.pethotel.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cpu.pethotel.biz.IFriendsBiz;
import org.cpu.pethotel.biz.impl.FriendsBizImpl;
import org.cpu.pethotel.po.Friends;
import org.cpu.pethotel.po.Users;


/**
 * Servlet implementation class AddFriendByIdServlet
 */
public class AddFriendByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFriendByIdServlet() {
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
		//int myid = Integer.parseInt(request.getParameter("myid"));
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("user");
		int myid= user.getUid();
		int friendid = Integer.parseInt(request.getParameter("friendid"));
		Friends friend = new Friends();
		friend.setUid(myid);
		friend.setUse_uid(friendid);
		// 步骤2：创建Biz层对象，调用响应的方法实现对数据库的删除操作
		IFriendsBiz friendsBiz = new FriendsBizImpl();
		Friends oldfriend = friendsBiz.isFriend(myid,friendid);	
		if( oldfriend != null ){
			int msgcode = 101;
			String url = "jsp/searchFriend.jsp";
			request.setAttribute("msgcode", msgcode);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}else{
			boolean flag = friendsBiz.addById(friend);
			// 步骤3：根据步骤2的操作结果进行页面的响应
			if(flag){
				response.sendRedirect("jsp/FriendTrends.jsp");
			}else{
				response.sendRedirect("jsp/FriendTrends.jsp");
			}
		}
	}

}
