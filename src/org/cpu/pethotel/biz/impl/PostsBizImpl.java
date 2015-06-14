package org.cpu.pethotel.biz.impl;

import java.util.List;

import org.cpu.pethotel.biz.IPostsBiz;
import org.cpu.pethotel.dao.IPostsDao;
import org.cpu.pethotel.dao.impl.PostsDaoImpl;
import org.cpu.pethotel.po.Posts;

public class PostsBizImpl implements IPostsBiz {
    private IPostsDao postsDao;
    
	public PostsBizImpl() {
		super();
		postsDao=new PostsDaoImpl();
	}

	@Override
	public boolean addPosts(Posts posts) {
		// TODO Auto-generated method stub
		return postsDao.insert(posts)>0?true:false;
		
	}

	@Override
	public Posts showOnePosts(int poid) {
		// TODO Auto-generated method stub
		return postsDao.selectByPoid(poid);
		
	}

	@Override
	public List<Posts> findApplyPosts(int uid) {
		// TODO Auto-generated method stub
        
		return postsDao.selectApplyPostsByUid(uid);
	}

	public static void main(String[] a){
		
		IPostsBiz postsBiz=new PostsBizImpl();
		 List<Posts> lstApplyPosts = postsBiz.findApplyPosts(3);
		 for(Posts p:lstApplyPosts){
			 System.out.println(p);
		 }
	}

	@Override
	public boolean updatePostsByTid(int retypeid, String potype) {
		// TODO Auto-generated method stub
		return postsDao.updateByTid(retypeid, potype)>0?true:false;
	}

	@Override
	public boolean updateByPoid(int poid) {
		// TODO Auto-generated method stub
		return postsDao.updateByPoid(poid)>0?true:false;
	}
	@Override
	public List<Posts> findTradePostsByUidByType(int uid) {
		// TODO Auto-generated method stub
		return postsDao.selectTradePostsByUidByType(uid);
	}
	@Override
	public boolean removePostsByPoid(int poid) {
		// TODO Auto-generated method stub
		return postsDao.deleteByPoid(poid)>0?true:false;
	}
	
	@Override
	public List<Posts> searchPosts(String potype, String pettype,
			String province, String city, String district, String pobegintime,
			String poendtime, int pos, int pagesize) {
		// TODO Auto-generated method stub
		return postsDao.selectPosts(potype, pettype, province, city, district, pobegintime, poendtime, pos, pagesize);
	}

	
}
