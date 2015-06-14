package org.cpu.pethotel.biz.impl;

import org.cpu.pethotel.biz.IFriendsBiz;
import org.cpu.pethotel.dao.IFriendsDao;
import org.cpu.pethotel.dao.impl.FriendsDaoImpl;
import org.cpu.pethotel.po.Friends;

public class FriendsBizImpl implements IFriendsBiz{
	private IFriendsDao friendsDao;
	
	public FriendsBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.friendsDao = new FriendsDaoImpl();
	}

	@Override
	public boolean deleteById(int fsid) {
		// TODO Auto-generated method stub
		return this.friendsDao.deleteById(fsid)>0?true:false;
	}

	@Override
	public boolean addById(Friends friend) {
		// TODO Auto-generated method stub
		return this.friendsDao.insert(friend)>0?true:false;
	}

	@Override
	public Friends isFriend(int uid, int use_uid) {
		// TODO Auto-generated method stub
		return this.friendsDao.selectByObject(uid,use_uid);
	}

}
