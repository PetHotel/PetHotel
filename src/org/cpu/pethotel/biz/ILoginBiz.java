package org.cpu.pethotel.biz;

import org.cpu.pethotel.po.Users;

public interface ILoginBiz {
	public abstract Users isLogin(final String account, final String password);
}
