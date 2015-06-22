package org.cpu.pethotel.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.cpu.pethotel.biz.IPostsBiz;
import org.cpu.pethotel.biz.impl.PostsBizImpl;
import org.cpu.pethotel.po.Posts;

/**
 * Servlet implementation class SearchPostsServlet
 */
public class SearchPostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchPostsServlet() {
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
		int pagesize=8;
		System.out.println("搜索");
		request.setCharacterEncoding("UTF-8");
		IPostsBiz postsBiz = new PostsBizImpl();
		String type = request.getParameter("type");
		if (type.equals("0")) {
			int pos = Integer.parseInt((String) request.getParameter("pos"));
			String potype = request.getParameter("potype");
			String pettype = request.getParameter("pettype");
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			String district = request.getParameter("district");
			String pobegintime = request.getParameter("bdate");
			String poendtime = request.getParameter("edate");
			
			String params=potype+","+pettype+","+province+","+city+","+district+","+pobegintime+","+poendtime;
			System.out.println("PaGet>"+params);
			List<Posts> lstPosts = postsBiz.searchPosts(potype, pettype,
					province, city, district, pobegintime, poendtime, pos, pagesize);
			request.setAttribute("lstPosts", lstPosts);
			request.setAttribute("params", params);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("jsp/showSearchPosts.jsp");
			dispatcher.forward(request, response);
		} else {
			int pos = Integer.parseInt((String) request.getParameter("pos"));
			String params = request.getParameter("params");
			params=new String(params.getBytes("iso8859-1"),
					"UTF-8");
			System.out.println("Pa>"+params);
		    String p[]=params.split(",");
			String potype=p[0];
			String pettype=p[1];
			String province=p[2];
			String city=p[3];
			String district=p[4];
			String pobegintime="";
			String poendtime="";
			try{
				  pobegintime=p[5];
				  poendtime=p[6];
			}catch(Exception e){
				
			}
			params=potype+","+pettype+","+province+","+city+","+district+","+pobegintime+","+poendtime;
			List<Posts> lstPosts = postsBiz.searchPosts(potype, pettype,
					province, city, district, pobegintime, poendtime, pos, pagesize);
			request.setAttribute("lstPosts", lstPosts);
			request.setAttribute("params", params);
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("jsp/showSearchPosts.jsp");
			dispatcher.forward(request, response);
		}
	}
}
