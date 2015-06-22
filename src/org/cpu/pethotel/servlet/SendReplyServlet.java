package org.cpu.pethotel.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cpu.pethotel.biz.ISendReplyBiz;
import org.cpu.pethotel.biz.impl.SendReplyBizImpl;
import org.cpu.pethotel.po.Posts;
import org.cpu.pethotel.po.Users;

/**
 * Servlet implementation class SendReplyServlet
 */
public class SendReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendReplyServlet() {
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
		// 步骤1：设置字符编码集合
				request.setCharacterEncoding("UTF-8");
				// 步骤2：接受客户端传入的数据
				
				String where = request.getParameter("where");
				String puname=request.getParameter("puname");
				String potype = request.getParameter("potype");
				int followid = Integer.parseInt(request.getParameter("followid").trim());
				String context = request.getParameter("context").trim();
				HttpSession session = request.getSession();
				Users user = (Users)session.getAttribute("user");
				int uid= user.getUid();

				if(where.equals("own")){
					String pos=request.getParameter("pos");
					if(context.isEmpty()){
						response.sendRedirect("jsp/showAllOwnPosts.jsp?pos="+pos);
						return;
					}
				    // 步骤3：将其进行对象封装
					Posts replyPost = new Posts();
					replyPost.setUid(uid);
					
					replyPost.setRetyeId(followid);
					replyPost.setPofilelist("");
					replyPost.setPotype("replyPosts");
					replyPost.setAppreciate(0);
					replyPost.setPettype("");
					replyPost.setPobegintime("2014-08-08");
					replyPost.setPoendtime("2014-08-08");
					replyPost.setPocontext ("回复"+puname+":"+ context);
					replyPost.setPomark("");
				// 步骤4：调用Biz层业务实现回复动作
					ISendReplyBiz replyBiz = new SendReplyBizImpl();
					boolean flag = replyBiz.reply(replyPost);
				// 步骤5：根据动作结果响应客户端
					if(flag){
						response.sendRedirect("jsp/showAllOwnPosts.jsp?pos="+pos);
					}else{
						System.out.println("回复失败！");
					}
				}
				if(where.equals("f")){
					String pos=request.getParameter("pos");
					if(context.isEmpty()){
						response.sendRedirect("jsp/FriendTrends.jsp?pos="+pos);
						return;
					}
				    // 步骤3：将其进行对象封装
					Posts replyPost = new Posts();
					replyPost.setUid(uid);
					
					replyPost.setRetyeId(followid);
					replyPost.setPofilelist("");
					replyPost.setPotype("replyPosts");
					replyPost.setAppreciate(0);
					replyPost.setPettype("");
					replyPost.setPobegintime("2014-08-08");
					replyPost.setPoendtime("2014-08-08");
					replyPost.setPocontext ("回复"+puname+":"+ context);
					replyPost.setPomark("");
				// 步骤4：调用Biz层业务实现回复动作
					ISendReplyBiz replyBiz = new SendReplyBizImpl();
					boolean flag = replyBiz.reply(replyPost);
				// 步骤5：根据动作结果响应客户端
					if(flag){
						response.sendRedirect("jsp/FriendTrends.jsp?pos="+pos);
					}else{
						System.out.println("回复失败！");
					}
				}
				if(where.equals("out")|| where.equals("outup")){
					String pos=request.getParameter("pos");
					if(context.isEmpty()){
						response.sendRedirect("jsp/showPosts.jsp?pos="+pos+"&potype="+potype);
						return;
					}
					
				    // 步骤3：将其进行对象封装
					Posts replyPost = new Posts();
					replyPost.setUid(uid);
					
					replyPost.setRetyeId(followid);
					replyPost.setPofilelist("");
					replyPost.setPotype("replyPosts");
					replyPost.setAppreciate(0);
					replyPost.setPettype("");
					replyPost.setPobegintime("2014-08-08");
					replyPost.setPoendtime("2014-08-08");
					replyPost.setPocontext ("回复"+puname+":"+ context);
					replyPost.setPomark("");
				// 步骤4：调用Biz层业务实现回复动作
					ISendReplyBiz replyBiz = new SendReplyBizImpl();
					boolean flag = replyBiz.reply(replyPost);
				// 步骤5：根据动作结果响应客户端
					if(flag){
					
						response.sendRedirect("jsp/showPosts.jsp?pos="+pos+"&potype="+potype);
					}else{
						System.out.println("回复失败！");
					}
				}
				
				if(where.equals("in")){
					String poid=Integer.toString(followid);
					if(context.isEmpty()){
						response.sendRedirect("/PetHotel/ShowPostsServlet?poid="+poid+"&potype="+potype);
						return;
					}
					
					Posts replyPost = new Posts();
					replyPost.setUid(uid);
					replyPost.setRetyeId(followid);
					replyPost.setPofilelist("");
					replyPost.setPotype("replyPosts");
					replyPost.setAppreciate(0);
					replyPost.setPettype("");
					replyPost.setPobegintime("2014-08-08");
					replyPost.setPoendtime("2014-08-08");
					replyPost.setPocontext ("回复"+puname+":"+ context);
					replyPost.setPomark("");
				    // 步骤4：调用Biz层业务实现回复动作
					ISendReplyBiz replyBiz = new SendReplyBizImpl();
					boolean flag = replyBiz.reply(replyPost);
				     // 步骤5：根据动作结果响应客户端
					if(flag){
					
						response.sendRedirect("/PetHotel/ShowPostsServlet?poid="+poid+"&potype="+potype);
					}else{
						System.out.println("回复失败！");
						String error = "回复失败，请重试";
						request.setAttribute("error", error);
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Error.jsp");
						dispatcher.forward(request, response);
					}
					
					
				}
			
	}

}
