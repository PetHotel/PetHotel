package org.cpu.pethotel.biz.impl;

import org.cpu.pethotel.biz.ILoginBiz;
import org.cpu.pethotel.dao.IUsersDao;
import org.cpu.pethotel.dao.impl.UsersDaoImpl;
import org.cpu.pethotel.po.Users;

public class LoginBizImpl implements ILoginBiz {

	private IUsersDao usersDao;
	
	public LoginBizImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.usersDao = new UsersDaoImpl();
		}

	@Override
	public Users isLogin(String uname, String password) {
		// TODO Auto-generated method stub
		return this.usersDao.selectByObject(uname, password);
	}

}
