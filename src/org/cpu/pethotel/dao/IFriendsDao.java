package org.cpu.pethotel.dao;

import java.util.List;

import org.cpu.pethotel.po.Friends;

public interface IFriendsDao {
	public abstract int insert(final Friends friend);
	public abstract List<Friends> selectAll();
	public abstract List<Friends> selectByUid(int uid);
	public abstract int deleteById(final int cusid);
	public abstract Friends selectById(final int fsid);
	public abstract int update(final Friends friend);
	public abstract Friends selectByObject(final int uid, final int use_uid);
}
