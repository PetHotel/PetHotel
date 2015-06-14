package org.cpu.pethotel.dao;

import java.util.List;

import org.cpu.pethotel.po.Posts;

public interface IPostsDao {
	public abstract int insert(final Posts posts);
	public abstract int deleteByPoid(final int poid);
	public abstract Posts selectByPoid(final int poid);
	public abstract List<Posts> selectByPotype(final String potype,final int uid,
			                                     final int pos,final int pageSize);
	public abstract List<Posts> selectByRetypeId(final int retypeid);
	public abstract List<Posts> selectApplyPostsByUid(final int uid);
	public abstract int updateByTid(final int retypeid, final String potype);
	
	public abstract List<Posts> selectTradePostsByUidByType(final int uid);
	
	public abstract int updateByPoid(final int poid);
	public abstract List<Posts> selectAllOwnPostsByUid(final int uid,final int pos,final int pageSize);
	
	public abstract List<Posts> selectPosts(final String potype,
			final String pettype, final String province, final String city,
			final String district, final String pobegintime,
			final String poendtime, final int pos, final int pageSize);
	public abstract List<Posts> selectFriendPostsByUid(final int uid);
	public boolean updateAppByPoid(final int poid,final int app);
}
