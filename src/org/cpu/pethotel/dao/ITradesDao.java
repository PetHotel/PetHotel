package org.cpu.pethotel.dao;

import java.util.List;

import org.cpu.pethotel.po.Trades;

public interface ITradesDao {
	public abstract int insert(final Trades trades);
	public abstract List<Trades> SelectAll();
	public abstract int deleteById(final int tid);
	public abstract Trades selectById(final int tid);
	public abstract int update(final Trades trades);
	public abstract int selectTid(final int uid, final int pid, final int use_uid);
	public abstract List<Trades> selectInTradeByUid(final int uid,final String tstatus);
	public abstract List<Trades> selectByTstatusByTpolist(final String tstatus, final String tpostlist);
	public abstract List<Trades> selectByUid(final int uid);
}
