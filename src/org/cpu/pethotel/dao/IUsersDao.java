package org.cpu.pethotel.dao;

import java.util.List;

import org.cpu.pethotel.po.Users;

public interface IUsersDao {
	public abstract int insert(final Users user);
	public abstract List<Users> selectAll();
	public abstract int deleteById(final int uid);
	public abstract Users selectById(final int uid);
	public abstract int update(final Users users);
	public abstract Users selectByObject(final String uname, final String password);
	public abstract int updatePassword(final Users users);
	public abstract List<String> selectAllProvince();
	public abstract List<String> selectAllCity();
	public abstract List<String> selectAllDistrict();
	public abstract List<Users> selectFriends(String searchfriends);
	public abstract List<Users> selectRecommendFriends(String mycity);
	public abstract boolean checkDupByUname(final String uname);
	public abstract int updateReputation(final int uid, final int reputation);
}
