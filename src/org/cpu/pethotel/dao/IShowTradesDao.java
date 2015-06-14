package org.cpu.pethotel.dao;

import java.util.List;

import org.cpu.pethotel.po.Showtrades;

public interface IShowTradesDao {
	public abstract List<Showtrades> selectAll(int uid);
}
