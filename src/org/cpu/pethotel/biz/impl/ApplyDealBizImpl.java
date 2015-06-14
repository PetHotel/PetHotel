package org.cpu.pethotel.biz.impl;

import java.util.List;

import org.cpu.pethotel.biz.IApplyDealBiz;
import org.cpu.pethotel.dao.ITradesDao;
import org.cpu.pethotel.dao.impl.TradesDaoImpl;
import org.cpu.pethotel.po.Trades;

public class ApplyDealBizImpl implements IApplyDealBiz {

	private ITradesDao tradesDao; 
	
	public ApplyDealBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.tradesDao = new TradesDaoImpl();
	}

	@Override
	public int addTrade(Trades trades) {
		// TODO Auto-generated method stub
		return (this.tradesDao.insert(trades))*(this.tradesDao.selectTid(trades.getUid(), trades.getPid(), trades.getUse_uid()));
	}

	@Override
	public Trades findById(int tid) {
		// TODO Auto-generated method stub
		return this.tradesDao.selectById(tid);
	}

	@Override
	public boolean updateTradeStatus(Trades trade) {
		// TODO Auto-generated method stub
		return this.tradesDao.update(trade)>0?true:false;
	}

	@Override
	public boolean deleteById(int tid) {
		// TODO Auto-generated method stub
		return this.tradesDao.deleteById(tid)>0?true:false;
	}

	@Override
	public List<Trades> selectByTstatusByTpolist(String tstatus,
			String tpostlist) {
		// TODO Auto-generated method stub
		return this.tradesDao.selectByTstatusByTpolist(tstatus, tpostlist);
	}

	@Override
	public List<Trades> selectByUid(int uid) {
		// TODO Auto-generated method stub
		return this.tradesDao.selectByUid(uid);
	}

}
