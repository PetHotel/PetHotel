package org.cpu.pethotel.dao;

import java.util.List;

import org.cpu.pethotel.po.Resources;

public interface IResourcesDao {
	public abstract int insert(final Resources resource);
	public abstract List<Resources> selectAll();
	public abstract int deleteById(final int rid);
	public abstract Resources selectById(final int rid);
	public abstract int update(final Resources resource);
}
