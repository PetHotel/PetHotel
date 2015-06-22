package org.cpu.pethotel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cpu.pethotel.biz.IApplyDealBiz;
import org.cpu.pethotel.biz.impl.ApplyDealBizImpl;
import org.cpu.pethotel.po.Trades;

/**
 * Servlet implementation class HiddenTradeServlet
 */
public class HiddenTradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HiddenTradeServlet() {
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
//		HttpSession session = request.getSession();
		Trades trade = applyDealBiz.findById(tid);
//		Users user = (Users)session.getAttribute("user");
//		if (trade.getUid() == user.getUid()) {
//			trade.setTmark(trade.getTmark()+trade.getUid());
//			trade.setUid(trade.getUse_uid());
//		}else{
//			trade.setTmark(trade.getTmark()+trade.getUse_uid());
//			trade.setUse_uid(trade.getUid());
//		}
//		applyDealBiz.updateTradeStatus(trade);
		trade.settStatus("Invalid");
		boolean flag = applyDealBiz.updateTradeStatus(trade);
		System.out.println("----------Hidden Trades TStatus changed----------flag:>" +flag);
		
		String url = "jsp/PersonShowTrades.jsp";
		response.sendRedirect(url);
		
	}

}
