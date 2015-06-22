package org.cpu.pethotel.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cpu.pethotel.biz.IApplyDealBiz;
import org.cpu.pethotel.biz.IPetsBiz;
import org.cpu.pethotel.biz.IPostsBiz;
import org.cpu.pethotel.biz.IUsersBiz;
import org.cpu.pethotel.biz.impl.ApplyDealBizImpl;
import org.cpu.pethotel.biz.impl.PetsBizImpl;
import org.cpu.pethotel.biz.impl.PostsBizImpl;
import org.cpu.pethotel.biz.impl.UsersBizImpl;
import org.cpu.pethotel.po.Pets;
import org.cpu.pethotel.po.Posts;
import org.cpu.pethotel.po.Trades;
import org.cpu.pethotel.po.Users;

/**
 * Servlet implementation class TradeRefuseServlet
 */
public class TradeRefuseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TradeRefuseServlet() {
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
		int tid = Integer.parseInt(request.getParameter("tid"));
		
		IApplyDealBiz applyDealBiz = new ApplyDealBizImpl();
		Trades trade =  applyDealBiz.findById(tid);
		
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		
		trade.settStatus("Refused");
//		int tmp = trade.getUid();
//		trade.setUid(trade.getUse_uid());
//		trade.setUse_uid(tmp);
		
		boolean flag = applyDealBiz.updateTradeStatus(trade);
		
		if(flag == true){
			IPetsBiz petsBiz = new PetsBizImpl();
			Pets pet = new Pets();
			pet = petsBiz.findById(trade.getPid());
			
			IUsersBiz usersBiz = new UsersBizImpl();
			Users send = usersBiz.findById(user.getUid());
			Users receive = usersBiz.findById(trade.getUse_uid());
			
			System.out.println(pet.toString());
			
			Posts post = new Posts();
			post.setUid(user.getUid());
			post.setRetyeId(-tid);
			post.setPotype("tradeRefused");
			post.setPocontext("（委托人） "+ send.getUname() +" 拒绝了  （申请人） "+ receive.getUname() +"关于 宠物 "+pet.getPnickname()+" 的申请， 交易中止");
			
			IPostsBiz postsBiz = new PostsBizImpl();
			boolean poflag = postsBiz.addPosts(post);
			System.out.println("------Trade Refused------poflag:>"+poflag);
				
			postsBiz.updatePostsByTid(tid, "tradeApply");
			
			String choice = "1";
			if(flag){
				choice = "1";
			}else{
				choice = "0";
			}
			System.out.println("-------------Trade Refused--------------Choice:>"+choice);
			
			request.setAttribute("choice",choice );
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/ConfirmDeal.jsp");
			dispatcher.forward(request, response);
			
		}else{
			String error = "拒绝失败，请重试";
			request.setAttribute("error", error);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Error.jsp");
			dispatcher.forward(request, response);
		}
	}
}


