package org.cpu.pethotel.servlet;

import java.io.IOException;

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
 * Servlet implementation class DealEndServlet
 */
public class DealEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DealEndServlet() {
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
		IApplyDealBiz applyDealBiz = new ApplyDealBizImpl();
		Trades trade = applyDealBiz.findById(tid);
		
		System.out.println("-----DealEnd Trade Status----:>"+trade.gettStatus());
//		HttpSession session = request.getSession();
//		Users user = (Users)session.getAttribute("user");
//		if (trade.getUid() == user.getUid()) {
//			trade.setUid(0);
//		}else{
//			trade.setUse_uid(0);
//		}
		if (trade.gettStatus().equals("Deal")) {
//			trade.settStatus("Done");
			
			HttpSession session = request.getSession();
			Users user = (Users)session.getAttribute("user");
			
			trade.settStatus("DealEnd"+user.getUname());

			IPetsBiz petsBiz = new PetsBizImpl();
			Pets pet = new Pets();
			pet = petsBiz.findById(trade.getPid());
			
			System.out.println(pet.toString());
			
			Posts post = new Posts();
			post.setUid(user.getUid());
			post.setRetyeId(-tid);
			post.setPotype("tradeDealEnd");
			post.setPocontext( user.getUname() +" 发出了  交易编号为 "+ tid +"的 关于 宠物 "+pet.getPnickname()+" 的交易结束申请，请到寄领详情页面查询。");
			
			IPostsBiz postsBiz = new PostsBizImpl();
			boolean poflag = postsBiz.addPosts(post);
			System.out.println("--------------交易结束申请已发送---------------poflag:>"+poflag);
			
			IUsersBiz userBiz = new UsersBizImpl();
			boolean uflag = userBiz.updateReputation(user.getUid(), user.getReputation()+10);
			if(uflag){
				user.setReputation(user.getReputation()+10);
				session.setAttribute("user", user);
			}
			
		}else{
			trade.settStatus("Done");
			HttpSession session = request.getSession();
			Users user = (Users) session.getAttribute("user");
			IUsersBiz userBiz = new UsersBizImpl();
			boolean uflag = userBiz.updateReputation(user.getUid(), user.getReputation()+10);
			if(uflag){
				user.setReputation(user.getReputation()+10);
				session.setAttribute("user", user);
			}
		}
		applyDealBiz.updateTradeStatus(trade);
		
		String url = "/PetHotel/ShowTradesServlet";
		response.sendRedirect(url);
	}
	
}
