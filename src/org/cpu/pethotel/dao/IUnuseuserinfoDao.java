package org.cpu.pethotel.dao;

import java.util.List;

import org.cpu.pethotel.po.UnUseUserInfo;

public interface IUnuseuserinfoDao {
	public abstract int insert(final UnUseUserInfo unUseUserInfo);
	public abstract List<UnUseUserInfo> selectAll();
	public abstract int deleteById(final int unuseid);
	public abstract UnUseUserInfo selectById(final int unuseid);
	public abstract int update(final UnUseUserInfo unUseUserInfo);
}
