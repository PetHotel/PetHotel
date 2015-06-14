package org.cpu.pethotel.biz;

import java.util.List;

import org.cpu.pethotel.po.Showtrades;



public interface IShowTradesBiz {
	public abstract List<Showtrades> findAll(int uid);
}
