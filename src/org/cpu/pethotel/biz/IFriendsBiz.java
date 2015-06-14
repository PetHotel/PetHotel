package org.cpu.pethotel.biz;

import org.cpu.pethotel.po.Friends;

public interface IFriendsBiz {
	public abstract boolean deleteById(final int fsid);
	public abstract boolean addById(final Friends friend);
	public abstract Friends isFriend(final int uid, final int use_uid);
}
