package org.cpu.pethotel.biz;

import java.util.List;

import org.cpu.pethotel.po.Pets;

public interface IPetsBiz {
	public abstract boolean add(final Pets pets);
	public abstract List<Pets> findAllByUid(final int uid);
	public abstract boolean delete(final int pid);
	public abstract  Pets findById(final int pid);
	public abstract boolean modify(final Pets pets);

}
