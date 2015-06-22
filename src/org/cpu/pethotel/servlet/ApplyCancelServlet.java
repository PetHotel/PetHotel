package org.cpu.pethotel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cpu.pethotel.biz.IApplyDealBiz;
import org.cpu.pethotel.biz.IPostsBiz;
import org.cpu.pethotel.biz.impl.ApplyDealBizImpl;
import org.cpu.pethotel.biz.impl.PostsBizImpl;
import org.cpu.pethotel.po.Trades;

/**
 * Servlet implementation class ApplyCancelServlet
 */
public class ApplyCancelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyCancelServlet() {
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
		trade.settStatus("Invalid");
		boolean flag = applyDealBiz.updateTradeStatus(trade);
		System.out.println("-----------Trade Status Changed----------flag:>"+flag);
		
		IPostsBiz postsBiz = new PostsBizImpl();
		boolean poflag = postsBiz.updatePostsByTid(trade.getTid(), "tradeApply");
		System.out.println("------------Potype Changed---------------poflag:>"+poflag);
		
		
		String url = "jsp/PersonShowTrades.jsp";
		response.sendRedirect(url);
	}

}
