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

import org.cpu.pethotel.biz.IUsersBiz;
import org.cpu.pethotel.biz.impl.UsersBizImpl;
import org.cpu.pethotel.po.Users;

/**
 * Servlet implementation class PreSearchPostsServlet
 */
public class PreSearchPostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PreSearchPostsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IUsersBiz usersBiz = new UsersBizImpl();
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		
		List<String> lstprovincePre = usersBiz.FindAllProvince();
		List<String> lstprovince = new ArrayList<String>();
		String curProvince = user.getProvince();
		lstprovince.add(curProvince);
		for (String p : lstprovincePre) {
			if (!p.equals(curProvince)) {
				lstprovince.add(p);
			}
		}
		request.setAttribute("lstprovince", lstprovince);

		List<String> lstcityPre = usersBiz.FindAllCity();
		List<String> lstcity = new ArrayList<String>();
		String curCity = user.getCity();
		lstcity.add(curCity);
		for (String p : lstcityPre) {
			if (!p.equals(curCity)) {
				lstcity.add(p);
			}
		}
		request.setAttribute("lstcity", lstcity);

		List<String> lstdistrictPre = usersBiz.FindAllDistrict();
		List<String> lstdistrict = new ArrayList<String>();
		String curdistrict = user.getDistrict();
		lstdistrict.add(curdistrict);
		for (String p : lstdistrictPre) {
			if (!p.equals(curdistrict)) {
				lstdistrict.add(p);
			}
		}
		request.setAttribute("lstdistrict", lstdistrict);
		String check = "GetIn";
		request.setAttribute("check", check);
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("jsp/search.jsp");
		dispatcher.forward(request, response);
	}

}
