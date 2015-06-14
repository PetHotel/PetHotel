package org.cpu.pethotel.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cpu.pethotel.dao.IUnuseuserinfoDao;
import org.cpu.pethotel.db.ConnectionManager;
import org.cpu.pethotel.db.DBUtils;
import org.cpu.pethotel.db.TransactionManager;
import org.cpu.pethotel.po.UnUseUserInfo;

public class UnuseuserinfoDaoImpl implements IUnuseuserinfoDao {
	//步骤1：声明数据库连接管理器、数据库操作对象
	private ConnectionManager connectionManager;
	private DBUtils dbUtils;
	private Connection conn;
	public UnuseuserinfoDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
	}

	@Override
	public int insert(UnUseUserInfo unUseUserInfo) {
		// TODO Auto-generated method stub
		//准备1：获取一个数据库的连接对象
		this.conn = this.connectionManager.openConnection();
		//准备2：开启一个事务处理
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();
		//步骤3：拆分对象的属性
		int uid = unUseUserInfo.getUid();
		String userRealName = unUseUserInfo.getUserRealName();
		String id = unUseUserInfo.getId();
		Date date = unUseUserInfo.getDate();
		String ucmark = unUseUserInfo.getUcmark();
		//步骤4：创建SQL语句模板
		String strSQL = "insert into unuseuserinfo values(null,?,?,?,?,?)";
		Object[] params = new Object[]{uid, userRealName, id,date,ucmark};
		//步骤5：创建dbutils对象并调用execOthers方法实现对数据库的添加操作
		int affecteRows = this.dbUtils.execOthers(conn, strSQL, params);
		//步骤6：根据步骤5的执行结果完成相对应的事务处理
		if(affecteRows > 0){
			TransactionManager.commit();//事务提交
		}else{
			TransactionManager.rollback();//事务回滚
		}
		return affecteRows;
	}

	@Override
	public List<UnUseUserInfo> selectAll() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<UnUseUserInfo> lstUnUseUserInfo = new ArrayList<UnUseUserInfo>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from unuseuserinfo order by unuseid";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, new Object[]{});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while(resultSet.next()){
				// 步骤5-1：创建一个对象
				UnUseUserInfo unUseUserInfo= new UnUseUserInfo();
				unUseUserInfo.setUnuserid(resultSet.getInt(1));
				unUseUserInfo.setUid(resultSet.getInt(2));
				unUseUserInfo.setUserRealName(resultSet.getString(3));
				unUseUserInfo.setId(resultSet.getString(4));
				unUseUserInfo.setDate(resultSet.getDate(5));
				unUseUserInfo.setUcmark(resultSet.getString(6));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstUnUseUserInfo.add(unUseUserInfo);
			}
			// 返回结果
			return lstUnUseUserInfo;
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
	public int deleteById(int unuseid) {
		// TODO Auto-generated method stub
		//准备1：获取一个数据库的连接对象
		this.conn = this.connectionManager.openConnection();
		//准备2：开启一个事务处理
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();
		//步骤3：创建SQL语句模板
		String strSQL = "delete from unuseuserinfo where unuseid=?";
		Object[] params = new Object[]{unuseid};
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
	public UnUseUserInfo selectById(int unuseid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from unuseuserinfo where unuseid=?";
		Object[] params = new Object[]{unuseid};
		//步骤4：创建dbutils对象并调用execOthers方法实现对数据库的添加操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		//步骤5：根据步骤5的执行结果完成相对应的事务处理
		try {
			if(resultSet.next()){
				UnUseUserInfo unUseUserInfo= new UnUseUserInfo();
				unUseUserInfo.setUnuserid(resultSet.getInt(1));
				unUseUserInfo.setUid(resultSet.getInt(2));
				unUseUserInfo.setUserRealName(resultSet.getString(3));
				unUseUserInfo.setId(resultSet.getString(4));
				unUseUserInfo.setDate(resultSet.getDate(5));
				unUseUserInfo.setUcmark(resultSet.getString(6));
				return unUseUserInfo;
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
	public int update(UnUseUserInfo unUseUserInfo) {
		// TODO Auto-generated method stub
		//准备1：获取一个数据库的连接对象
		this.conn = this.connectionManager.openConnection();
		//准备2：开启一个事务处理
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();
		//步骤3：创建SQL语句模板
		String strSQL = "update unuseuserinfo set userRealName=?, id=?,ucmark=?";
		Object[] params = new Object[]{unUseUserInfo.getUserRealName(),unUseUserInfo.getId(),unUseUserInfo.getUcmark()};
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

}
