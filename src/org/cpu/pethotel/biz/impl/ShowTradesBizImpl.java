package org.cpu.pethotel.biz.impl;

import java.util.List;

import org.cpu.pethotel.biz.IShowTradesBiz;
import org.cpu.pethotel.dao.IShowTradesDao;
import org.cpu.pethotel.dao.impl.ShowTradesDaoImpl;
import org.cpu.pethotel.po.Showtrades;


public class ShowTradesBizImpl implements IShowTradesBiz {

	private IShowTradesDao showTradesDao;
	
	public ShowTradesBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.showTradesDao = new ShowTradesDaoImpl();
	}

	@Override
	public List<Showtrades> findAll(int uid) {
		// TODO Auto-generated method stub
		return this.showTradesDao.selectAll(uid);
	}

}
