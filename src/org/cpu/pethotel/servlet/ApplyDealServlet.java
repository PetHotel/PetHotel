package org.cpu.pethotel.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cpu.pethotel.biz.IApplyDealBiz;
import org.cpu.pethotel.biz.IPetsBiz;
import org.cpu.pethotel.biz.IPostsBiz;
import org.cpu.pethotel.biz.impl.ApplyDealBizImpl;
import org.cpu.pethotel.biz.impl.PetsBizImpl;
import org.cpu.pethotel.biz.impl.PostsBizImpl;
import org.cpu.pethotel.po.Pets;
import org.cpu.pethotel.po.Posts;
import org.cpu.pethotel.po.Trades;

/**
 * Servlet implementation class ApplyDealServlet
 */
public class ApplyDealServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyDealServlet() {
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
		
		int uid = Integer.parseInt(request.getParameter("uid"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		String poid = request.getParameter("poid");
		int use_uid = Integer.parseInt(request.getParameter("user_uid"));
//		String uname = request.getParameter("uname");
//		String pettype = request.getParameter("pettype");
		String receiveuname = request.getParameter("receiveuname");
		String bdate = request.getParameter("bdate");
		String edate = request.getParameter("edate");
		String tmark = request.getParameter("umark");
		
		System.out.println("--------deal uid:>"+uid);
		System.out.println("--------deal pid:>"+pid);
		
		Trades trade = new Trades();
		trade.setUid(uid);
		trade.setPid(pid);
		trade.setUse_uid(use_uid);
		trade.settBeginDate(bdate);
		trade.settEndDate(edate);
		trade.settPostList(poid);
		trade.settStatus("Apply");
		trade.setTmark(tmark);
		
		IApplyDealBiz applyDealBiz = new ApplyDealBizImpl();
		int tid = applyDealBiz.addTrade(trade);
		System.out.println("ApplyDealServlet tid:> "+tid);
		
		IPetsBiz petsBiz = new PetsBizImpl();
		Pets pet = new Pets();
		pet = petsBiz.findById(pid);
		
		System.out.println(pet.toString());
		
		Posts post = new Posts();
		post.setUid(use_uid);
		post.setRetyeId(-tid);
		post.setPotype("tradeApply");
		post.setPocontext("(申请人)"+receiveuname+" 申请了关于宠物 "+pet.getPnickname()+" 的交易。");
		
		IPostsBiz postsBiz = new PostsBizImpl();
		boolean flag = postsBiz.addPosts(post);
		System.out.println("ApplyDealServlet flag:>"+flag);
		String choice = "1";
		if(flag){
			choice = "1";
		}else{
			choice = "0";
		}
		System.out.println("Choice:>"+choice);
		
		request.setAttribute("choice",choice );
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/ApplyDeal.jsp");
		dispatcher.forward(request, response);
		
	}

}
