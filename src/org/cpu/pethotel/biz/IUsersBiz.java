package org.cpu.pethotel.biz;

import java.util.List;

import org.cpu.pethotel.po.Users;

public interface IUsersBiz {
	public abstract boolean register(final Users user);
	public abstract Users findById(final int uid);
	public abstract boolean modify(final Users users);
	public abstract boolean changePassword(final Users users);
	public abstract List<Users> FindAll();
	public abstract List<String> FindAllProvince();
	public abstract List<String> FindAllCity();
	public abstract List<String> FindAllDistrict();
	public abstract List<Users> searchFriends(String searchfriends);
	public abstract List<Users> recommendFriends(String mycity);
	public abstract boolean findDupByUname(final String uname);
	public abstract boolean updateReputation(final int uid, final int reputation);
}
