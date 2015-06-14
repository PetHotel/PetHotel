package org.cpu.pethotel.biz;

import org.cpu.pethotel.po.Posts;


public interface ISendReplyBiz {
	public abstract boolean reply(final Posts replyPost);
}
