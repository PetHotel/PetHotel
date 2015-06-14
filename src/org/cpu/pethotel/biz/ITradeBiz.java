package org.cpu.pethotel.biz;

import java.util.List;

import org.cpu.pethotel.po.Trades;



public interface ITradeBiz {
	public abstract List<Trades> findInTradeByUid(final int uid,final String tstatus);
}
