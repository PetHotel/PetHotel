package org.cpu.pethotel.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cpu.pethotel.biz.IApplyDealBiz;
import org.cpu.pethotel.biz.IPetsBiz;
import org.cpu.pethotel.biz.IUsersBiz;
import org.cpu.pethotel.biz.impl.ApplyDealBizImpl;
import org.cpu.pethotel.biz.impl.PetsBizImpl;
import org.cpu.pethotel.biz.impl.UsersBizImpl;
import org.cpu.pethotel.po.Pets;
import org.cpu.pethotel.po.Trades;
import org.cpu.pethotel.po.Users;

/**
 * Servlet implementation class ConfirmDealServlet
 */
public class ConfirmDealServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmDealServlet() {
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
		Trades trade = new Trades();
		IApplyDealBiz applyDealBiz = new ApplyDealBizImpl();
		
		trade = applyDealBiz.findById(tid);
		System.out.println(trade.toString());
		
		IUsersBiz usersBiz = new UsersBizImpl();
		Users send = usersBiz.findById(trade.getUid());
		Users receive = usersBiz.findById(trade.getUse_uid());
		
		IPetsBiz petsBiz = new PetsBizImpl();
		Pets pet = petsBiz.findById(trade.getPid());
		
		String choice = "3";
		
		request.setAttribute("tid", tid);
		request.setAttribute("uname", send.getUname());
		request.setAttribute("receiveuid", receive.getUid());
		request.setAttribute("receiveuname", receive.getUname());
		request.setAttribute("pettype", pet.getPtype());
		request.setAttribute("tbegindate", trade.gettBeginDate());
		request.setAttribute("tenddate", trade.gettEndDate());
		request.setAttribute("tmark", trade.getTmark());
		request.setAttribute("choice", choice);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/ConfirmDeal.jsp");
		 dispatcher.forward(request, response);
	}

}
