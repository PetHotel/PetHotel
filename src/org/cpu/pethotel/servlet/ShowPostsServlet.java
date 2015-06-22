package org.cpu.pethotel.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cpu.pethotel.biz.IPostsBiz;
import org.cpu.pethotel.biz.IUsersBiz;
import org.cpu.pethotel.biz.impl.PostsBizImpl;
import org.cpu.pethotel.biz.impl.UsersBizImpl;
import org.cpu.pethotel.dao.IPostsDao;
import org.cpu.pethotel.dao.impl.PostsDaoImpl;
import org.cpu.pethotel.po.Posts;
import org.cpu.pethotel.po.Users;


/**
 * Servlet implementation class ShowPostsServlet
 */

public class ShowPostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPostsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
	    int poid=Integer.parseInt(request.getParameter("poid"));
	    String potype = request.getParameter("potype");
		
		IPostsBiz postsBizImpl=new PostsBizImpl();
		IUsersBiz usersBiz= new UsersBizImpl();
		
		Posts posts = postsBizImpl.showOnePosts(poid);
		Users users = usersBiz.findById(posts.getUid());
		System.out.println("posts>" + posts.toString());
		
		String pofilelist;
		List<String> lstImg = new ArrayList<String>();
		String[] img;
		int imgCount = 0;
		
		if (posts.getPofilelist() != null) {                     //得到图片
			pofilelist = posts.getPofilelist();
			if (pofilelist.indexOf(",") < 0) {
				lstImg.add(pofilelist);
				imgCount = 1;
			} else {

				img = pofilelist.split(",");
				for (int i = 0; i < img.length; i++) {
					lstImg.add(img[i]);
				}
				imgCount = img.length;
			}
		}
		
		if (imgCount != 0) {
			request.setAttribute("imgCount", imgCount);
			request.setAttribute("lstImg", lstImg);
		}
		
		request.setAttribute("users", users);
		request.setAttribute("posts", posts);

		if(potype.contains("Sender")){
			
			
			System.out.println(">转向jsp/showASenderPosts.jsp");
			
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("jsp/showASenderPosts.jsp");
			dispatcher.forward(request, response);
		}
		else if(potype.contains("Receive")){
            System.out.println(">转向jsp/showAReceivePosts.jsp");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("jsp/showAReceivePosts.jsp");
			dispatcher.forward(request, response);
			
		}
		else if(potype.contains("Share")){
			System.out.println(">转向jsp/showASharePosts.jsp");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("jsp/showASharePosts.jsp");
			dispatcher.forward(request, response);
		}
		else if(potype.contains("trade")){
			System.out.println(">转向jsp/showATradePosts.jsp");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("jsp/showATradePosts.jsp");
			dispatcher.forward(request, response);
			}
		
}
	public static void main(String[] arg){
		try {
			new ShowPostsServlet().doPost(null, null);
		}  catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
