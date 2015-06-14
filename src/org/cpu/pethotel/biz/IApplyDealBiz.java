package org.cpu.pethotel.biz;

import java.util.List;

import org.cpu.pethotel.po.Trades;

public interface IApplyDealBiz {
	public abstract int addTrade(final Trades trades);
	public abstract Trades findById(final int tid);	
	public abstract boolean updateTradeStatus(final Trades trade);
	public abstract boolean deleteById(final int tid);
	public abstract List<Trades> selectByTstatusByTpolist(final String tstatus, final String tpostlist);
	public abstract List<Trades> selectByUid(final int uid);
}
