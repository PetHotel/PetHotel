package org.cpu.pethotel.biz;

import java.util.List;

import org.cpu.pethotel.po.Posts;

public interface IPostsBiz {
	public abstract boolean addPosts(final Posts posts);
	public abstract Posts showOnePosts(final int poid);
	public abstract List<Posts> findApplyPosts(final int uid);
	public abstract boolean updatePostsByTid(final int retypeid, final String potype);
	public abstract boolean updateByPoid(final int poid);
	public abstract List<Posts> findTradePostsByUidByType(final int uid);
	public abstract boolean removePostsByPoid(final int poid );
	
	public abstract List<Posts> searchPosts(final String potype,final String pettype,final String province,final String city,final String district,final String pobegintime,final String poendtime,final int pos, final int pagesize);
}
