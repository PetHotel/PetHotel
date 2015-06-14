package org.cpu.pethotel.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cpu.pethotel.dao.IUsersDao;
import org.cpu.pethotel.db.ConnectionManager;
import org.cpu.pethotel.db.DBUtils;
import org.cpu.pethotel.po.Users;
import org.cpu.pethotel.db.TransactionManager;;

public class UsersDaoImpl implements IUsersDao {
	//步骤1：声明数据库连接管理器、数据库操作对象
	private ConnectionManager connectionManager;
	private DBUtils dbUtils;
	private Connection conn;
		
	public UsersDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
	}

	@Override
	public int insert(Users user) {
		// TODO Auto-generated method stub
		//准备1：获取一个数据库的连接对象
		this.conn = this.connectionManager.openConnection();
		//准备2：开启一个事务处理
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();
		//步骤3：拆分对象的属性
		String uname = user.getUname();
		String password = user.getPassword();
		String province = user.getProvince();
		String city = user.getCity();
		String district = user.getDistrict();
		String address = user.getAddress();
		int reputation = user.getReputation();
		boolean isAdopt = user.isAdopt();
		String qq = user.getQq();
		String email = user.getEmail();
		String tel = user.getTel();
		String headportrait = user.getHeadportrait();
		int status = user.getStatus();
		//步骤4：创建SQL语句模板
		String strSQL = "insert into users values(null,null,?,?,?,?,?,?,null,?,?,?,?,?,?,null,?)";
		Object[] params = new Object[]{ uname, password,province,city,district,address,
				reputation,isAdopt,qq,email,tel,headportrait,status};
		//步骤5：创建dbutils对象并调用execOthers方法实现对数据库的添加操作
		int affecteRows = this.dbUtils.execOthers(conn, strSQL, params);
		if(affecteRows > 0){
			TransactionManager.commit();//事务提交
		}else{
			TransactionManager.rollback();//事务回滚
		}
		return affecteRows;
	}

	@Override
	public List<Users> selectAll() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Users> lstUsers = new ArrayList<Users>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from users order by uid";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, new Object[]{});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while(resultSet.next()){
				// 步骤5-1：创建一个对象
				Users users= new Users();
				users.setUid(resultSet.getInt(1));
				users.setUname(resultSet.getString(3));
				users.setPassword(resultSet.getString(4));
				users.setProvince(resultSet.getString(5));
				users.setCity(resultSet.getString(6));
				users.setDistrict(resultSet.getString(7));
				users.setAddress(resultSet.getString(8));
				users.setUfid(resultSet.getString(9));
				users.setReputation(resultSet.getInt(10));
				users.setAdopt(resultSet.absolute(12));
				users.setQq(resultSet.getString(12));
				users.setEmail(resultSet.getString(13));
				users.setTel(resultSet.getString(14));
				users.setHeadportrait(resultSet.getString(15));
				users.setUmark(resultSet.getString(16));
				users.setStatus(resultSet.getInt(17));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstUsers.add(users);
			}
			// 返回结果
			return lstUsers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally{
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(conn);
		}
	}

	@Override
	public int deleteById(int uid) {
		// TODO Auto-generated method stub
		//准备1：获取一个数据库的连接对象
		this.conn = this.connectionManager.openConnection();
		//准备2：开启一个事务处理
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();
		//步骤3：创建SQL语句模板
		String strSQL = "delete from users where uid=?";
		Object[] params = new Object[]{uid};
		//步骤4：创建dbutils对象并调用execOthers方法实现对数据库的添加操作
		int affecteRows = this.dbUtils.execOthers(conn, strSQL, params);
		//步骤5：根据步骤5的执行结果完成相对应的事务处理
		if(affecteRows > 0){
			TransactionManager.commit();//事务提交
		}else{
			TransactionManager.rollback();//事务回滚
		}
		return affecteRows;
	}

	@Override
	public Users selectById(int uid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from users where uid=?";
		Object[] params = new Object[]{uid};
		//步骤4：创建dbutils对象并调用execOthers方法实现对数据库的添加操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		//步骤5：根据步骤5的执行结果完成相对应的事务处理
		try {
			if(resultSet.next()){
				Users users = new Users();
				users.setUid(resultSet.getInt(1));
				users.setUname(resultSet.getString(3));
				users.setPassword(resultSet.getString(4));
				users.setProvince(resultSet.getString(5));
				users.setCity(resultSet.getString(6));
				users.setDistrict(resultSet.getString(7));
				users.setAddress(resultSet.getString(8));
				users.setUfid(resultSet.getString(9));
				users.setReputation(resultSet.getInt(10));
				users.setAdopt(resultSet.getBoolean(11));
				users.setQq(resultSet.getString(12));
				users.setEmail(resultSet.getString(13));
				users.setTel(resultSet.getString(14));
				users.setHeadportrait(resultSet.getString(15));
				users.setUmark(resultSet.getString(16));
				users.setStatus(resultSet.getInt(17));
				return users;
			}else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally{
			this.connectionManager.closeConnection(conn);
		}
		
	}

	@Override
	public int update(Users users) {
		// TODO Auto-generated method stub
		//准备1：获取一个数据库的连接对象
		this.conn = this.connectionManager.openConnection();
		//准备2：开启一个事务处理
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();
		//步骤3：创建SQL语句模板
		System.out.println(users.toString());
		String strSQL = "update users set province=?,city=?,district=?,address=?,ufid=?,reputation=?,isAdopt=?,qq=?,email=?,tel=?,headportrait=?,umark=? where uid=?";
		Object[] params = new Object[]{users.getProvince(),users.getCity(),users.getDistrict(),users.getAddress(),users.getUfid(),users.getReputation(),users.isAdopt(),users.getQq(),users.getEmail(),users.getTel(),users.getHeadportrait(),users.getUmark(),users.getUid()};
		//步骤4：创建dbutils对象并调用execOthers方法实现对数据库的添加操作
		int affecteRows = this.dbUtils.execOthers(conn, strSQL, params);
		//步骤5：根据步骤5的执行结果完成相对应的事务处理
		if(affecteRows > 0){
			TransactionManager.commit();//事务提交
		}else{
			TransactionManager.rollback();//事务回滚
		}
		return affecteRows;
	}

	@Override
	public Users selectByObject(String uname, String password) {
		// TODO Auto-generated method stub
		Connection conn = this.connectionManager.openConnection();
		
		String strSQL = "select * from users where uname=? and password=?";
		Object[] params = new Object[]{uname, password};
		
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		try {
			if(resultSet.next()){
				Users user = new Users();
				user.setUid(resultSet.getInt(1));
				user.setUname(resultSet.getString(3));
				user.setPassword(resultSet.getString(4));
				user.setProvince(resultSet.getString(5));
				user.setCity(resultSet.getString(6));
				user.setDistrict(resultSet.getString(7));
				user.setAddress(resultSet.getString(8));
				user.setReputation(resultSet.getInt(10));
				user.setAdopt((resultSet.getInt(11))==0?false:true);
				user.setQq(resultSet.getString(12));
				user.setEmail(resultSet.getString(13));
				user.setTel(resultSet.getString(14));
				user.setHeadportrait(resultSet.getString(15));
				user.setStatus(resultSet.getInt(17));
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return  null;
		} finally{
			this.connectionManager.closeConnection(conn);
		}	
		return null;
	}
	
	@Override
	public int updatePassword(Users users) {
		// TODO Auto-generated method stub
		// 准备1：获取一个数据库的连接对象
		this.conn = this.connectionManager.openConnection();
		// 准备2：开启一个事务处理
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "update users set password=? where uid=?";
		Object[] params = new Object[] { users.getPassword(),users.getUid() };
		// 步骤4：创建dbutils对象并调用execOthers方法实现对数据库的添加操作
		int affecteRows = this.dbUtils.execOthers(conn, strSQL, params);
		// 步骤5：根据步骤5的执行结果完成相对应的事务处理
		if (affecteRows > 0) {
			TransactionManager.commit();// 事务提交
		} else {
			TransactionManager.rollback();// 事务回滚
		}
		return affecteRows;
	}
	
	@Override
	public List<String> selectAllProvince() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<String> lstprovince = new ArrayList<String>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select distinct province from users order by province";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,
				new Object[] {});
		// 步骤5：将resultSet结果集转换成List数据结构

		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个对象
				String province=resultSet.getString(1);
				// 步骤5-2：将封装好的对象添加到List集合中
				lstprovince.add(province);
			}
			// 返回结果
			return lstprovince;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(conn);
		}
	}

	@Override
	public List<String> selectAllCity() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
				List<String> lstcity = new ArrayList<String>();
				// 步骤2：获取一个数据库的连接对象
				this.conn = connectionManager.openConnection();
				// 步骤3：创建查询语句的模板
				String strSQL = "select distinct city from users order by city";
				// 步骤4：使用dbutils方法实现查询操作
				ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,
						new Object[] {});
				// 步骤5：将resultSet结果集转换成List数据结构

				try {
					while (resultSet.next()) {
						// 步骤5-1：创建一个对象
						String city=resultSet.getString(1);
						// 步骤5-2：将封装好的对象添加到List集合中
						lstcity.add(city);
					}
					// 返回结果
					return lstcity;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} finally {
					// 步骤6：关闭数据库连接
					this.connectionManager.closeConnection(conn);
				}
	}

	@Override
	public List<String> selectAllDistrict() {
		// TODO Auto-generated method stub
		List<String> lstdistrict = new ArrayList<String>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select distinct district from users order by district";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,
				new Object[] {});
		// 步骤5：将resultSet结果集转换成List数据结构

		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个对象
				String district=resultSet.getString(1);
				// 步骤5-2：将封装好的对象添加到List集合中
				lstdistrict.add(district);
			}
			// 返回结果
			return lstdistrict;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(conn);
		}
	}
	
	@Override
	public List<Users> selectFriends(String searchfriends) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				// 步骤1：创建一个空的集合准备存放查询的结果
				List<Users> lstUsers = new ArrayList<Users>();
				// 步骤2：获取一个数据库的连接对象
				this.conn = connectionManager.openConnection();
				// 步骤3：创建查询语句的模板
				String strSQL = "select * from users where uname like '%"+searchfriends+"%' or province like '%"+searchfriends+"%' or city like '%"+searchfriends+"%' or district like '%"+searchfriends+"%' order by uid";
				//searchfriends= "'%"+searchfriends+ "%'";
				//Object[] params = new Object[]{searchfriends,searchfriends,searchfriends,searchfriends};
				// 步骤4：使用dbutils方法实现查询操作
				ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, new Object[]{});
				// 步骤5：将resultSet结果集转换成List数据结构
				try {
					while(resultSet.next()){
						// 步骤5-1：创建一个对象
						Users users= new Users();
						users.setUid(resultSet.getInt(1));
						users.setUname(resultSet.getString(3));
						users.setPassword(resultSet.getString(4));
						users.setProvince(resultSet.getString(5));
						users.setCity(resultSet.getString(6));
						users.setDistrict(resultSet.getString(7));
						users.setAddress(resultSet.getString(8));
						users.setUfid(resultSet.getString(9));
						users.setReputation(resultSet.getInt(10));
						//users.setAdopt(resultSet.absolute(11));
						users.setQq(resultSet.getString(12));
						users.setEmail(resultSet.getString(13));
						users.setTel(resultSet.getString(14));
						users.setHeadportrait(resultSet.getString(15));
						users.setUmark(resultSet.getString(16));
						users.setStatus(resultSet.getInt(17));
						// 步骤5-2：将封装好的对象添加到List集合中
						lstUsers.add(users);
					}
					// 返回结果
					System.out.println(lstUsers.size());
					return lstUsers;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} finally{
					// 步骤6：关闭数据库连接
					this.connectionManager.closeConnection(conn);
				}
	}

	@Override
	public List<Users> selectRecommendFriends(String mycity) {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Users> lstUsers = new ArrayList<Users>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from users where city like '%"+mycity+"%' order by uid";
		//searchfriends= "'%"+searchfriends+ "%'";
		//Object[] params = new Object[]{searchfriends,searchfriends,searchfriends,searchfriends};
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, new Object[]{});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while(resultSet.next()){
				// 步骤5-1：创建一个对象
				Users users= new Users();
				users.setUid(resultSet.getInt(1));
				users.setUname(resultSet.getString(3));
				users.setPassword(resultSet.getString(4));
				users.setProvince(resultSet.getString(5));
				users.setCity(resultSet.getString(6));
				users.setDistrict(resultSet.getString(7));
				users.setAddress(resultSet.getString(8));
				users.setUfid(resultSet.getString(9));
				users.setReputation(resultSet.getInt(10));
				//users.setAdopt(resultSet.absolute(11));
				users.setQq(resultSet.getString(12));
				users.setEmail(resultSet.getString(13));
				users.setTel(resultSet.getString(14));
				users.setHeadportrait(resultSet.getString(15));
				users.setUmark(resultSet.getString(16));
				users.setStatus(resultSet.getInt(17));
				// 步骤5-2：将封装好的对象添加到List集合中
					lstUsers.add(users);
			}
			// 返回结果
		    System.out.println(lstUsers.size());
			return lstUsers;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally{
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(conn);
		}
	}
	@Override
	public boolean checkDupByUname(String uname) {
		// TODO Auto-generated method stub
				Connection conn = this.connectionManager.openConnection();
				
				String strSQL = "select * from users where uname=?";
				Object[] params = new Object[]{uname};
				ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
				try {
					if(resultSet.next()){
						
						return true;
					}else{
						return false;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return  true;
				} finally{
					this.connectionManager.closeConnection(conn);
				}	
		
	}

	@Override
	public int updateReputation(int uid, int reputation) {
		// TODO Auto-generated method stub
		this.conn = this.connectionManager.openConnection();
		// 准备2：开启一个事务处理
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "update users set reputation=? where uid=?";
		Object[] params = new Object[] {reputation, uid };
		// 步骤4：创建dbutils对象并调用execOthers方法实现对数据库的添加操作
		int affectedRows = this.dbUtils.execOthers(conn, strSQL, params);
		// 步骤5：根据步骤5的执行结果完成相对应的事务处理
		if (affectedRows > 0) {
			TransactionManager.commit();// 事务提交
		} else {
			TransactionManager.rollback();// 事务回滚
		}
		return affectedRows;
		
	}
}
