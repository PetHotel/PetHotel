package org.cpu.pethotel.dao;

import java.util.List;

import org.cpu.pethotel.po.Chats;

public interface IChatsDao {
	public abstract int insert(final Chats chats);
	public abstract List<Chats> SelectAll();
	public abstract int deleteById(final int cid);
	public abstract Chats selectById(final int cid);
	public abstract int update(final Chats chats);
}
