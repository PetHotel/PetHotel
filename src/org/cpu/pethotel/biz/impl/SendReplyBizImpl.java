package org.cpu.pethotel.biz.impl;

import org.cpu.pethotel.biz.ISendReplyBiz;
import org.cpu.pethotel.dao.IPostsDao;
import org.cpu.pethotel.dao.impl.PostsDaoImpl;
import org.cpu.pethotel.po.Posts;


public class SendReplyBizImpl implements ISendReplyBiz {

	private IPostsDao postDao;
	
	public SendReplyBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.postDao = new PostsDaoImpl();
	}

	@Override
	public boolean reply(Posts replyPost) {
		// TODO Auto-generated method stub
		return this.postDao.insert(replyPost)>0?true:false;
	}

}
