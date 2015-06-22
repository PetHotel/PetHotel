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

import org.cpu.pethotel.biz.IPetsBiz;
import org.cpu.pethotel.biz.IUsersBiz;
import org.cpu.pethotel.biz.impl.PetsBizImpl;
import org.cpu.pethotel.biz.impl.UsersBizImpl;
import org.cpu.pethotel.po.Pets;
import org.cpu.pethotel.po.Users;

/**
 * Servlet implementation class ShowPetsServlet
 */
public class FindAllPetsByUidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllPetsByUidServlet() {
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
		// 步骤1：调用Biz层的方法实现对Customers数据的检索
		request.setCharacterEncoding("UTF-8");
		String choice=request.getParameter("choice");
		System.out.println("choice>"+choice);
		IPetsBiz petsBiz = new PetsBizImpl();
		// 步骤2：调用findAll方法完成数据的获取
		List<Pets> lstPets = new ArrayList<Pets>();
		  //Log_in
		// 步骤3：将获取的数据添加到request级别对象中
		
		// 步骤4：使用请求转发模式完成界面的跳转（可以携带reuqest对象进行跳转）
		if(choice.equals("0")){
			
			HttpSession session = request.getSession();
			Users Suser = (Users) session.getAttribute("user");
			System.out.println("findallpetOfUSER:>"+Suser.toString());
			lstPets =(ArrayList<Pets>) petsBiz.findAllByUid(Suser.getUid()); 
			request.setAttribute("lstPets", lstPets);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Pets.jsp");
			dispatcher.forward(request, response);
			}else if(choice.equals("1")){
				String uid=request.getParameter("uid");
				lstPets = petsBiz.findAllByUid(Integer.parseInt(uid)); 
				
				// 步骤3：将获取的数据添加到request级别对象中
				IUsersBiz usersBiz = new UsersBizImpl();
				Users user = usersBiz.findById(Integer.parseInt(uid));
				request.setAttribute("user", user);
				request.setAttribute("lstPets", lstPets);
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/NPets.jsp");
				dispatcher.forward(request, response);
			}
			else{
				HttpSession session = request.getSession();
				Users Suser = (Users)session.getAttribute("user");
				lstPets = petsBiz.findAllByUid(Suser.getUid()); 
				// 步骤3：将获取的数据添加到request级别对象中
				IUsersBiz usersBiz = new UsersBizImpl();
				Users user = usersBiz.findById(Suser.getUid());
				request.setAttribute("user", user);
				request.setAttribute("lstPets", lstPets);
				RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/"+choice);
				dispatcher.forward(request, response);}

	}

}
