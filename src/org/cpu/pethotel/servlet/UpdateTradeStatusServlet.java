package org.cpu.pethotel.servlet;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class UpdateTradeStatusServlet
 */
public class UpdateTradeStatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTradeStatusServlet() {
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
		request.setCharacterEncoding("UTF-8");
		
		int tid = Integer.parseInt(request.getParameter("tid"));
		
		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");
		
		IApplyDealBiz applyDealBiz = new ApplyDealBizImpl();
		Trades trade =  applyDealBiz.findById(tid);
		
		trade.settStatus("Deal");
		
		boolean flag = applyDealBiz.updateTradeStatus(trade);
		
		System.out.println("---------------TradeStatus Changed--------------");
		
		if(flag == true){
			IPetsBiz petsBiz = new PetsBizImpl();
			Pets pet = new Pets();
			pet = petsBiz.findById(trade.getPid());
			
			IUsersBiz usersBiz = new UsersBizImpl();
			Users send = usersBiz.findById(trade.getUid());
			Users receive = usersBiz.findById(trade.getUse_uid());
			
			System.out.println("---------Find Pets for Confirm----------"+pet.toString());
			
			Posts post = new Posts();
			post.setUid(user.getUid());
			post.setRetyeId(-tid);
			post.setPotype("tradeConfirm");
			post.setPocontext("委托人 "+ send.getUname() +" 同意了  申请人 "+ receive.getUname() +"关于 宠物 "+pet.getPnickname()+" 的申请， 交易开始");
			
			IPostsBiz postsBiz = new PostsBizImpl();
			boolean poflag = postsBiz.addPosts(post);
			System.out.println("---------Add Deal Post---------- poflag:>"+poflag);
			
			postsBiz.updatePostsByTid(tid, "tradeApply");
			System.out.println("-----------Change Apply Post Type to Invalid------------");
			
			
			List<Trades> tradesRefuse = applyDealBiz.selectByTstatusByTpolist("Apply", trade.gettPostList());
			System.out.println("----------All Trade Status Changed------------");
			if (!(tradesRefuse.isEmpty())) {
				for (Trades t : tradesRefuse) {
					System.out.println("updateTrade:>" + t.toString());

					IPetsBiz tpetsBiz = new PetsBizImpl();
					Pets tpet = new Pets();
					tpet = tpetsBiz.findById(t.getPid());

					IUsersBiz tusersBiz = new UsersBizImpl();
					Users tsend = tusersBiz.findById(t.getUse_uid());
					Users treceive = tusersBiz.findById(t.getUid());

					System.out
							.println("---------Find Pets for Refuse----------"
									+ tpet.toString());

					Posts tpost = new Posts();
					tpost.setUid(user.getUid());
					tpost.setRetyeId(-t.getTid());
					tpost.setPotype("tradeRefused");
					tpost.setPocontext("（委托人） " + tsend.getUname()
							+ " 拒绝了  （申请人） " + treceive.getUname() + "关于 宠物 "
							+ tpet.getPnickname() + " 的申请， 交易中止");

					IPostsBiz tpostsBiz = new PostsBizImpl();
					boolean tpoflag = tpostsBiz.addPosts(tpost);
					System.out
							.println("-------Refuse Posts Added-------- tpoflag:>"
									+ tpoflag);

					tpostsBiz.updatePostsByTid(t.getTid(), "tradeApply");
					System.out
							.println("----------------Post tradeApply Changed to tradeRefused-----------");

				}
			}
			boolean pflag = postsBiz.updateByPoid(Integer.parseInt(trade.gettPostList()));
			System.out.println("------------Delete SenderPost---------- flag:>"+pflag);
			
			String choice = "1";
			if(pflag){
				choice = "2";
			}else{
				choice = "4";
			}
			System.out.println("Choice:>"+choice);
			
			request.setAttribute("choice",choice );
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/ConfirmDeal.jsp");
			dispatcher.forward(request, response);
			
		}else{
			String error = "确认订单失败，请重试";
			request.setAttribute("error", error);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/Error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
