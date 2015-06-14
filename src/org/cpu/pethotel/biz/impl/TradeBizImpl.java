package org.cpu.pethotel.biz.impl;

import java.util.List;
import org.cpu.pethotel.biz.ITradeBiz;
import org.cpu.pethotel.dao.ITradesDao;
import org.cpu.pethotel.dao.impl.TradesDaoImpl;
import org.cpu.pethotel.po.Trades;


public class TradeBizImpl implements ITradeBiz {
     private ITradesDao tradesDao;
     
     
	public TradeBizImpl() {
		super();
		this.tradesDao = new TradesDaoImpl();
	}


	@Override
	public List<Trades> findInTradeByUid(int uid,String tstatus) {
		// TODO Auto-generated method stub
		return tradesDao.selectInTradeByUid(uid,tstatus) ;
	}


	
}
