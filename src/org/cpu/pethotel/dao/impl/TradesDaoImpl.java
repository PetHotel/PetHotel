package org.cpu.pethotel.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cpu.pethotel.dao.ITradesDao;
import org.cpu.pethotel.db.ConnectionManager;
import org.cpu.pethotel.db.DBUtils;
import org.cpu.pethotel.db.TransactionManager;
import org.cpu.pethotel.po.Trades;

@SuppressWarnings("unused")
public class TradesDaoImpl implements ITradesDao {
	private ConnectionManager connectionManager;
	private Connection conn;
	private DBUtils dbUtils;
	public TradesDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
		this.connectionManager=new ConnectionManager();
		this.dbUtils=new DBUtils();
	}
	@Override
	public int insert(Trades trades) {
		this.conn=this.connectionManager.openConnection();
		
		TransactionManager.conn=this.conn;
        TransactionManager.beginTransaction();
        
        int uid=trades.getUid();
        int pid=trades.getPid();
        int Use_uid=trades.getUse_uid();
        String tBeginDate=trades.gettBeginDate();
        String tEndDate=trades.gettEndDate();
        String tStaus=trades.gettStatus();
        String tPostList=trades.gettPostList();
        String tmark=trades.getTmark();
        
        String strSQL="insert into trades values(null,?,?,?,?,?,?,?,?)";
        Object[] params=new Object[]{uid,pid,Use_uid,tBeginDate,tEndDate,tStaus,tPostList,tmark};
        
        int affectedrows=dbUtils.execOthers(conn, strSQL, params);
        
        if(affectedrows>0){
        	TransactionManager.commit();
        }
        else{
        	TransactionManager.rollback();
        }
		// TODO Auto-generated method stub
		return affectedrows;
	}

	@Override
	public List<Trades> SelectAll() {
		List<Trades> lsttrades=new ArrayList<Trades>();
		this.conn=connectionManager.openConnection();
		String strSQL="select * from trades order by tid";
		ResultSet resultSet=dbUtils.execQuery(conn, strSQL, new Object[]{});
		try {
			while(resultSet.next()){
				Trades trades=new Trades();
				trades.setTid(resultSet.getInt(1));
				trades.setUid(resultSet.getInt(2));
				trades.setPid(resultSet.getInt(3));
				trades.setUse_uid(resultSet.getInt(4));
				trades.settBeginDate(resultSet.getDate(5).toString());
				trades.settEndDate(resultSet.getDate(6).toString());
				trades.settStatus(resultSet.getString(7));
				trades.settPostList(resultSet.getString(8));
				trades.setTmark(resultSet.getString(9));
				lsttrades.add(trades);
			}
			return lsttrades;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			this.connectionManager.closeConnection(conn);
		}
	}

	@Override
	public int deleteById(int tid) {
		// TODO Auto-generated method stub
		this.conn=this.connectionManager.openConnection();
		TransactionManager.conn=this.conn;
        TransactionManager.beginTransaction();
        String strSQL="delete from trades where tid=?";
        Object[] params=new Object[]{tid};
        int affectedRows=dbUtils.execOthers(conn, strSQL, params);
        if (affectedRows > 0) {
			TransactionManager.commit(); // 事务提交
		} else {
			TransactionManager.rollback(); // 事务的回滚
		}
		return affectedRows;
	}

	@Override
	public Trades selectById(int tid) {
		// TODO Auto-generated method stub
		this.conn = this.connectionManager.openConnection();
		String strSQL="select * from trades where tid=?";
		Object[] params = new Object[]{tid};
		ResultSet resultSet=dbUtils.execQuery(conn, strSQL, params);
		try {
			if(resultSet.next()){
				Trades trades=new Trades();
				trades.setTid(resultSet.getInt(1));
				trades.setUid(resultSet.getInt(2));
				trades.setPid(resultSet.getInt(3));
				trades.setUse_uid(resultSet.getInt(4));
				trades.settBeginDate(resultSet.getDate(5).toString());
				trades.settEndDate(resultSet.getDate(6).toString());
				trades.settStatus(resultSet.getString(7));
				trades.settPostList(resultSet.getString(8));
				trades.setTmark(resultSet.getString(9));
				return trades;
			}else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			this.connectionManager.closeConnection(conn);
		}
		
	}

	@Override
	public int update(Trades trades) {
		// TODO Auto-generated method stub
		this.conn=this.connectionManager.openConnection();
		TransactionManager.conn=this.conn;
        TransactionManager.beginTransaction();
        String strSQL="update trades set uid=?,pid=?,Use_uid=?,tBeginDate=?,tEndDate=?,tStatus=?,tPostList=?,tmark=? where tid=?";
        Object[] params = new Object[]{trades.getUid(),trades.getPid(),trades.getUse_uid(),trades.gettBeginDate(),trades.gettEndDate(),trades.gettStatus(),trades.gettPostList(),trades.getTmark(),trades.getTid()};
		int affectedRows=dbUtils.execOthers(conn, strSQL, params);
		if (affectedRows > 0) {
			TransactionManager.commit(); // 事务提交
		} else {
			TransactionManager.rollback(); // 事务的回滚
		}
        return affectedRows;
	}
	
	@Override
	public int selectTid(int uid, int pid, int use_uid) {
		// TODO Auto-generated method stub
		this.conn = this.connectionManager.openConnection();
		
		String strSQL="select tid FROM trades where uid=? and pid=? and use_uid=? order by tid desc";
		Object[] params = new Object[]{uid, pid, use_uid};
		ResultSet resultSet=dbUtils.execQuery(conn, strSQL, params);
		
		try {
			if(resultSet.next()){
				int tid = resultSet.getInt(1);
				return tid;
			}else{
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally{
			this.connectionManager.closeConnection(conn);
		}
	}
	
	@Override
	public List<Trades> selectInTradeByUid(int uid,String tstatus) {
		// TODO Auto-generated method stub
		this.conn = this.connectionManager.openConnection();
		String strSQL="select * from trades where tstatus=? and( uid=? or Use_uid=?)";
		Object[] params = new Object[]{tstatus, uid, uid};
		ResultSet resultSet=dbUtils.execQuery(conn, strSQL, params);
		List<Trades> lstTrade = new ArrayList<Trades>();
		try {
		     while(resultSet.next()){
				Trades trades=new Trades();
				trades.setTid(resultSet.getInt(1));
				trades.setUid(resultSet.getInt(2));
				trades.setPid(resultSet.getInt(3));
				trades.setUse_uid(resultSet.getInt(4));
				trades.settBeginDate(resultSet.getDate(5).toString());
				trades.settEndDate(resultSet.getDate(6).toString());
				trades.settStatus(resultSet.getString(7));
				trades.settPostList(resultSet.getString(8));
				trades.setTmark(resultSet.getString(9));
				System.out.println(trades);
				lstTrade.add(trades);
			}
			
		   }  catch (SQLException e) {
			// TODO Auto-generated catch block
			    e.printStackTrace();
			    return null;
		}finally {
			this.connectionManager.closeConnection(conn);
		}
		
		return lstTrade;
	}
	@Override
	public List<Trades> selectByTstatusByTpolist(String tstatus, String tpostlist) {
		// TODO Auto-generated method stub
		this.conn = this.connectionManager.openConnection();
		
		String strSQL = "select * from trades where tstatus=? and tpostlist=?";
		Object[] params = new Object[]{tstatus, tpostlist};
		ResultSet resultSet = dbUtils.execQuery(conn, strSQL, params);
		
		List<Trades> lstTrade = new ArrayList<Trades>();
		try {
			while(resultSet.next()){
				Trades trades=new Trades();
				trades.setTid(resultSet.getInt(1));
				trades.setUse_uid(resultSet.getInt(2));
				trades.setPid(resultSet.getInt(3));
				trades.setUid(resultSet.getInt(4));
				trades.settBeginDate(resultSet.getDate(5).toString());
				trades.settEndDate(resultSet.getDate(6).toString());
				trades.settStatus(resultSet.getString(7));
				trades.settPostList(resultSet.getString(8));
				trades.setTmark(resultSet.getString(9));
				System.out.println(trades);
				lstTrade.add(trades);
			}

		}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("------------Exception-------");
				return null;
		}
		this.connectionManager.closeConnection(conn);
		
		conn = this.connectionManager.openConnection();
		TransactionManager.conn=this.conn;
        TransactionManager.beginTransaction();
		
		strSQL = "update trades set tstatus='Refused' where tstatus=? and tpostlist=?";
		params = new Object[]{tstatus, tpostlist};
		int affectedRows=dbUtils.execOthers(conn, strSQL, params);
		
		if (affectedRows > 0) {
				TransactionManager.commit(); // 事务提交
			}
		 else {
			TransactionManager.rollback(); // 事务的回滚
		
		}
		
		return lstTrade;
	}
	@Override
	public List<Trades> selectByUid(int uid) {
		// TODO Auto-generated method stub
		this.conn = connectionManager.openConnection();
		
		String strSQL = "select* from trades where (uid=? or use_uid=?) and tstatus!='Invalid' order by tid desc";
		Object[] params = new Object[]{uid,uid};
		ResultSet resultSet = dbUtils.execQuery(conn, strSQL, params);
		
		List<Trades> lstTrade = new ArrayList<Trades>();
		try {
		     while(resultSet.next()){
				Trades trades=new Trades();
				trades.setTid(resultSet.getInt(1));
				trades.setUid(resultSet.getInt(2));
				trades.setPid(resultSet.getInt(3));
				trades.setUse_uid(resultSet.getInt(4));
				trades.settBeginDate(resultSet.getDate(5).toString());
				trades.settEndDate(resultSet.getDate(6).toString());
				trades.settStatus(resultSet.getString(7));
				trades.settPostList(resultSet.getString(8));
				trades.setTmark(resultSet.getString(9));
				System.out.println(trades);
				lstTrade.add(trades);
			}
			
		   }  catch (SQLException e) {
			// TODO Auto-generated catch block
			    e.printStackTrace();
			    return null;
		}finally {
			this.connectionManager.closeConnection(conn);
		}
		
		return lstTrade;
		
	}



}
