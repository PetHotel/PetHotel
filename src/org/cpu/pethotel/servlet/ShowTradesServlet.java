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

import org.cpu.pethotel.biz.IApplyDealBiz;
import org.cpu.pethotel.biz.IPetsBiz;
import org.cpu.pethotel.biz.IUsersBiz;
import org.cpu.pethotel.biz.impl.ApplyDealBizImpl;
import org.cpu.pethotel.biz.impl.PetsBizImpl;
import org.cpu.pethotel.biz.impl.UsersBizImpl;
import org.cpu.pethotel.po.Pets;
import org.cpu.pethotel.po.Trades;
import org.cpu.pethotel.po.Users;
import org.cpu.pethotel.po.Showtrades;

/**
 * Servlet implementation class ShowTrades
 */
public class ShowTradesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowTradesServlet() {
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
//		IShowTradesBiz tradesBiz = new ShowTradesBizImpl();
//		HttpSession session = request.getSession();
//		Users user = (Users)session.getAttribute("user");
//		List<Showtrades> lstTrades = tradesBiz.findAll(user.getUid());
//		request.setAttribute("lstTrades", lstTrades);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/PersonShowTrades.jsp");
//		dispatcher.forward(request, response);
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("user");
		String uname = user.getUname();
		List<Showtrades> lstShowTrades = new ArrayList<Showtrades>();
		
		IApplyDealBiz applyDealBiz = new ApplyDealBizImpl();
		List<Trades> lstTrades = applyDealBiz.selectByUid(user.getUid());
		for(Trades t:lstTrades){
			Showtrades showTrade = new Showtrades();
			showTrade.setTid(t.getTid());
			showTrade.settBeginDate(t.gettBeginDate());
			showTrade.settEndDate(t.gettEndDate());
			
			String status = t.gettStatus();
			if(status.contains("DealEnd")){
				if(status.length()>7){
					String aUname = status.substring(7,status.length());
					System.out.println("---___________________-------aUname:>"+aUname);
					if(aUname.equals(uname)){
						status = "DealEndApplyByMyself";
					}else{
						status = "DealEndApplyByAnother";
					}
				}
			}
			showTrade.settStatus(status);
			
			IUsersBiz userBiz = new UsersBizImpl();
			Users userReceive = userBiz.findById(t.getUid());
			showTrade.setUname(userReceive.getUname());
			
			Users userApply = userBiz.findById(t.getUse_uid());
			showTrade.setU1name(userApply.getUname());
			
			
			IPetsBiz petBiz = new PetsBizImpl();
			Pets pet = petBiz.findById(t.getPid());
			showTrade.setPname(pet.getPnickname());
			showTrade.setPtype(pet.getPtype());
			showTrade.setPvariety(pet.getPvariety());
			
			lstShowTrades.add(showTrade);
			
		}
		
		request.setAttribute("lstTrades", lstShowTrades);
//		request.setAttribute("name", name);
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/PersonShowTrades.jsp");
		dispatcher.forward(request, response);
	}

}
