package org.cpu.pethotel.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.cpu.pethotel.dao.IResourcesDao;
import org.cpu.pethotel.db.ConnectionManager;
import org.cpu.pethotel.db.DBUtils;
import org.cpu.pethotel.db.TransactionManager;
import org.cpu.pethotel.po.Resources;

public class ResourcesDaoImpl implements IResourcesDao {

	private ConnectionManager connectionManager;
	private Connection conn;
	private DBUtils dbUtils;

	public ResourcesDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager = new ConnectionManager();
		this.dbUtils = new DBUtils();
	}

	@Override
	public int insert(Resources resource) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启一个事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();

		// 步骤3：拆分对向属性
		String rtype = resource.getRtype();
		String raddress = resource.getRaddress();
		Timestamp rupdatetime = resource.getUpdateTime();
		// 步骤4：设置添加SQL语句模板
		String strSQL = "insert into resources values(null,?,?,?)";
		Object[] params = new Object[] { rtype, raddress, rupdatetime };
		// 步骤5：使用dbutils方法实现添加操作
		int affectedRows = this.dbUtils.execOthers(conn, strSQL, params);
		// 步骤6：提交事务
		if (affectedRows > 0) {
			// 提交事务
			TransactionManager.commit();
		} else {
			// 回滚事务
			TransactionManager.rollback();
		}
		return affectedRows;
	}

	@Override
	public List<Resources> selectAll() {
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Resources> lstResources = new ArrayList<Resources>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from Resources order by rid";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,
				new Object[] {});
		// 步骤5：将resultSet结果集转换成List数据结构
		try {
			while (resultSet.next()) {
				// 步骤5-1：创建一个Customers对象
				Resources resource = new Resources();
				resource.setRid(resultSet.getInt(1));
				resource.setRtype(resultSet.getString(2));
				resource.setRaddress(resultSet.getString(3));
				resource.setUpdateTime(resultSet.getTimestamp(4));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstResources.add(resource);
			}
			// 返回结果
			return lstResources;
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
	public int deleteById(int rid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "delete from resources where rid=?";
		Object[] params = new Object[] { rid };
		// 步骤4：调用dbutils中的方法完成对数据库的删除操作
		int affectedRwos = this.dbUtils.execOthers(conn, strSQL, params);
		// 步骤5：根据步骤4的操作结果提交或回滚事务
		if (affectedRwos > 0) {
			TransactionManager.commit(); // 事务提交
		} else {
			TransactionManager.rollback(); // 事务的回滚
		}
		// 步骤6：返回影响行数
		return affectedRwos;
	}

	public Resources selectById(int rid) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：创建SQL语句模板
		String strSQL = "select * from resources where rid=?";
		Object[] params = new Object[] { rid };
		// 步骤4：调用dbutils中的方法完成对数据库的查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
		// 步骤5：获取结果集合并封装成一个对象
		try {
			if (resultSet.next()) {
				// 步骤6：创建一个Customers对象
				Resources resource = new Resources();
				resource.setRid(resultSet.getInt(1));
				resource.setRtype(resultSet.getString(2));
				resource.setRaddress(resultSet.getString(3));
				resource.setUpdateTime(resultSet.getTimestamp(4));
				// 步骤7：返回对象
				return resource;
			} else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			this.connectionManager.closeConnection(conn);
		}

	}

	@Override
	public int update(Resources resource) {
		// TODO Auto-generated method stub
		// 步骤1：获取一个数据库连接对象
		this.conn = this.connectionManager.openConnection();
		// 步骤2：开启事务
		TransactionManager.conn = this.conn;
		TransactionManager.beginTransaction();
		// 步骤3：创建SQL语句模板
		String strSQL = "update resources set rtype=?, raddress=?, rupdatetime=? where rid=?";
		Object[] params = new Object[] { resource.getRtype(), resource.getRaddress(), resource.getUpdateTime() };
		// 步骤4：调用dbutils中的方法完成对数据库的删除操作
		int affectedRwos = this.dbUtils.execOthers(conn, strSQL, params);
		// 步骤5：根据步骤4的操作结果提交或回滚事务
		if (affectedRwos > 0) {
			TransactionManager.commit(); // 事务提交
		} else {
			TransactionManager.rollback(); // 事务的回滚
		}
		// 步骤6：返回影响行数
		return affectedRwos;
	}

}
