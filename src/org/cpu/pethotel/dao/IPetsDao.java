package org.cpu.pethotel.dao;

import java.util.List;

import org.cpu.pethotel.po.Pets;

public interface IPetsDao {
	public abstract int insert(final Pets pets);
	public abstract int delete(final int pid);
	public abstract Pets selectByPid(final int pid);
	public abstract int update(final Pets pets);
	public abstract List<Pets> selectAllByUid(final int uid);

}
