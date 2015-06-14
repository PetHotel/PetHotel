package org.cpu.pethotel.biz.impl;

import java.util.List;

import org.cpu.pethotel.biz.IUsersBiz;
import org.cpu.pethotel.dao.IUsersDao;
import org.cpu.pethotel.dao.impl.UsersDaoImpl;
import org.cpu.pethotel.po.Users;

public class UsersBizImpl implements IUsersBiz {

	private IUsersDao userDao;
		
	public UsersBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.userDao = new UsersDaoImpl();
	}



	@Override
	public boolean register(Users user) {
		// TODO Auto-generated method stub
		return this.userDao.insert(user)>0?true:false;
	}

	@Override
	public Users findById(int uid) {
		// TODO Auto-generated method stub
		return this.userDao.selectById(uid);
	}



	@Override
	public boolean modify(Users users) {
		// TODO Auto-generated method stub
		return this.userDao.update(users)>0?true:false;
	}



	@Override
	public boolean changePassword(Users users) {
		// TODO Auto-generated method stub
		return this.userDao.updatePassword(users)>0?true:false;
	}
	

	@Override
	public List<Users> FindAll() {
		// TODO Auto-generated method stub
		return this.userDao.selectAll();
	}



	@Override
	public List<String> FindAllProvince() {
		// TODO Auto-generated method stub
		return this.userDao.selectAllProvince();
	}



	@Override
	public List<String> FindAllCity() {
		// TODO Auto-generated method stub
		return this.userDao.selectAllCity();
	}



	@Override
	public List<String> FindAllDistrict() {
		// TODO Auto-generated method stub
		return this.userDao.selectAllDistrict();
	}
	
	@Override
	public List<Users> searchFriends(String searchfriends) {
		// TODO Auto-generated method stub
		return this.userDao.selectFriends(searchfriends);
	}



	@Override
	public List<Users> recommendFriends(String mycity) {
		// TODO Auto-generated method stub
		return this.userDao.selectRecommendFriends(mycity);
	}
	@Override
	public boolean findDupByUname(String uname) {
		// TODO Auto-generated method stub
		return this.userDao.checkDupByUname(uname);
	}



	@Override
	public boolean updateReputation(int uid, int reputation) {
		// TODO Auto-generated method stub
		return this.userDao.updateReputation(uid, reputation)>0?true:false;
	}
}
