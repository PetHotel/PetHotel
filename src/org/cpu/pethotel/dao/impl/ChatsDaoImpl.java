package org.cpu.pethotel.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cpu.pethotel.dao.IChatsDao;
import org.cpu.pethotel.db.ConnectionManager;
import org.cpu.pethotel.db.DBUtils;
import org.cpu.pethotel.po.Chats;
import org.cpu.pethotel.db.TransactionManager;

public class ChatsDaoImpl implements IChatsDao {
	private ConnectionManager connectionManager;
	private Connection conn;
	private DBUtils dbUtils;
	

	public ChatsDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager=new ConnectionManager();
		this.dbUtils=new DBUtils();
	}

	@Override
	public int insert(Chats chats) {
		// TODO Auto-generated method stub
		this.conn=this.connectionManager.openConnection();
		TransactionManager.conn=this.conn;
        TransactionManager.beginTransaction();
        int uid=chats.getUid();
        int Use_uid=chats.getUse_uid();
        String context=chats.getContext();
        String cfid=chats.getCfid();
        String uchmark=chats.getUchmark();
        String strSQL="insert into chats values(null,?,?,?,?,?)";
        Object[] params=new Object[]{uid,Use_uid,context,cfid,uchmark};
        int affectedrows=dbUtils.execOthers(conn, strSQL, params);
        if(affectedrows>0){
        	TransactionManager.commit();
        }
        else{
        	TransactionManager.rollback();
        }
		return 0;
	}

	@Override
	public int deleteById(int cid) {
		// TODO Auto-generated method stub
		this.conn=this.connectionManager.openConnection();
		TransactionManager.conn=this.conn;
        TransactionManager.beginTransaction();
        String strSQL="delete from chats where cid=?";
        Object[] params=new Object[]{cid};
        int affectedrows=dbUtils.execOthers(conn, strSQL, params);
        if (affectedrows > 0) {
			TransactionManager.commit(); // 事务提交
		} else {
			TransactionManager.rollback(); // 事务的回滚
		}
		return 0;
	}

	@Override
	public Chats selectById(int cid) {
		// TODO Auto-generated method stub
		this.connectionManager=new ConnectionManager();
		String strSQL="select * from chats where cid=?";
		Object[] params=new Object[]{cid};
		ResultSet resultSet=dbUtils.execQuery(conn, strSQL, params);
		try {
			if(resultSet.next()){
				Chats chats = new Chats();
				chats.setCid(resultSet.getInt(1));
				chats.setUid(resultSet.getInt(2));
				chats.setUse_uid(resultSet.getInt(3));
				chats.setContext(resultSet.getString(4));
				chats.setCfid(resultSet.getString(5));
				chats.setUchmark(resultSet.getString(6));
				return chats;
			}
			else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally {
			this.connectionManager.closeConnection(conn);
		}
	}
	@Override
	public int update(Chats chats) {
		// TODO Auto-generated method stub
		this.conn=this.connectionManager.openConnection();
		TransactionManager.conn=this.conn;
        TransactionManager.beginTransaction();
        String strSQL="update chats set uid=?,Use_uid=?,context=?,cfid=?,uchmark=?";
        Object[] params = new Object[]{chats.getUid(),chats.getUse_uid(),chats.getContext(),chats.getCfid(),chats.getUchmark()};
		int affectedrows=this.dbUtils.execOthers(conn, strSQL, params);
		if (affectedrows > 0) {
			TransactionManager.commit(); // 事务提交
		} else {
			TransactionManager.rollback(); // 事务的回滚
		}
        return 0;
	}

	@Override
	public List<Chats> SelectAll() {
		// TODO Auto-generated method stub
		List<Chats> lstchats=new ArrayList<Chats>();
		this.conn=connectionManager.openConnection();
		String strSQL="select * from chats order by cid";
		ResultSet resultSet=dbUtils.execQuery(conn, strSQL, new Object[] {});
		try {
			while(resultSet.next()){
				Chats chats=new Chats();
				chats.setCid(resultSet.getInt(1));
				chats.setUid(resultSet.getInt(2));
				chats.setUse_uid(resultSet.getInt(3));
				chats.setContext(resultSet.getString(4));
				chats.setCfid(resultSet.getString(5));
				chats.setUchmark(resultSet.getString(6));
				lstchats.add(chats);
			}
			return lstchats;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			// 步骤6：关闭数据库连接
			this.connectionManager.closeConnection(conn);
		}
	}

}
