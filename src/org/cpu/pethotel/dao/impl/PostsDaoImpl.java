package org.cpu.pethotel.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.cpu.pethotel.dao.IPostsDao;
import org.cpu.pethotel.db.ConnectionManager;
import org.cpu.pethotel.db.DBUtils;
import org.cpu.pethotel.db.TransactionManager;
import org.cpu.pethotel.po.Posts;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PostsDaoImpl implements IPostsDao {
	  private ConnectionManager connectionManager;
	  private DBUtils dbUtils;
	  public PostsDaoImpl() {
		super();
		connectionManager=new ConnectionManager();
		dbUtils = new DBUtils();
	}
	@Override
	public int insert(Posts posts) {
		// TODO Auto-generated method stub
		Connection conn=this.connectionManager.openConnection();
		TransactionManager.conn=conn;
		TransactionManager.beginTransaction();
		String strSQL="insert into posts values(null,?,?,?,?,?,?,?,?,?,now(),?)";
		int uid=posts.getUid();
		int retypeid = posts.getRetyeId();
		String pofilelist = posts.getPofilelist();
		String potype = posts.getPotype();
		int appreciate = posts.getAppreciate();
		String pettype=posts.getPettype();
		String pobegintime=posts.getPobegintime();
		String poendtime=posts.getPoendtime();
		String pocontext =  posts.getPocontext();
		String pomark=posts.getPomark();
		
		Object[] params= new Object[]{uid,retypeid,pofilelist,potype,appreciate,
				                  pettype,pobegintime,poendtime, pocontext,pomark};
		int affectedRows = this.dbUtils.execOthers(conn, strSQL, params);
		if(affectedRows > 0){
			TransactionManager.commit();
		
		}else{
			
			TransactionManager.rollback();
		
		}	
		return affectedRows;
	}
	
	@Override
	public int deleteByPoid(int poid) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Posts selectByPoid(int poid) {
		// TODO Auto-generated method stub
		Connection conn=this.connectionManager.openConnection();
		String strSQL="select * from posts where poid=?";
		Object[] params=new Object[]{poid};
		ResultSet resultSet=this.dbUtils.execQuery(conn, strSQL, params);
		Posts posts=new Posts();
		try {
			if(resultSet.next()){
				
				@SuppressWarnings("unused")
				int p=resultSet.getInt(1);
				int uid=resultSet.getInt(2);
				int retyeId = resultSet.getInt(3);
				String pofilelist = resultSet.getString(4);
				String potye=resultSet.getString(5);
				int appreciate = resultSet.getInt(6);
				String pettype=resultSet.getString(7);
				String pobegintime=resultSet.getDate(8).toString();
				String poendtime=resultSet.getDate(9).toString();
				String pocontext=resultSet.getString(10);
				String potime=resultSet.getObject(11).toString();
				String pomark=resultSet.getString(12);
				
				posts.setPoid(poid);
				posts.setUid(uid);
				posts.setRetyeId(retyeId);
				posts.setPofilelist(pofilelist);
				posts.setPotype(potye);
				posts.setAppreciate(appreciate);
				posts.setPettype(pettype);
				posts.setPobegintime(pobegintime);
				posts.setPoendtime(poendtime);
				posts.setPocontext(pocontext);
				posts.setPotime(potime);
				posts.setPomark(pomark);
				
				return posts;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally{
			this.connectionManager.closeConnection(conn);
			
		}
		return null;
	}
	@Override
	public List<Posts> selectByPotype(String potype,int uid,int pos,int pageSize) {
		// TODO Auto-generated method stub
		/*String strSQL = "select distinct p.poid,p.uid,p.retypeid,p.pofilelist,"
				+ "p.potype,p.appreciate,p.pettype,p.pobegintime,p.poendtime,p.pocontext,p.potime,p.pomark"
                   +" from posts as p,users as u"
                      +" where (p.uid=u.uid and potype=?)"
                           +" or ( p.potype=? and"
                            +" p.uid in (select Use_uid from friends where uid=?) )"
                            +" or (p.potype=? and p.uid=?)"
                             +" order by p.potime desc limit ?,?";*/
		String strSQL="select distinct p.poid,p.uid,p.retypeid,p.pofilelist,p.potype,p.appreciate,p.pettype,p.pobegintime,p.poendtime,p.pocontext,p.potime,p.pomark from posts as p,users as u where (p.uid=u.uid and potype=?) or (p.potype=? and p.uid in (select Use_uid from friends where uid=?) ) or (p.potype=? and p.uid=?)  order by p.potime desc limit ?,?";
		Connection conn = this.connectionManager.openConnection();
		String spotype="secretSharePosts";
		if(potype.contains("Send")){
			spotype= new String("secretSenderPosts");
		}
		if(potype.contains("Receive")){
			spotype=new String("secretReceivePosts");
		}
		if(potype.contains("publicSharePosts")){
			spotype=new String("secretSharePosts");
		}
		
		Object[] params= new Object[]{potype,spotype,uid,spotype,uid,pos,pageSize};
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL,params);
		List<Posts> lstPosts = new ArrayList<Posts>();
		try {
			while(resultSet.next()){
				Posts post = new Posts();
				post.setPoid(resultSet.getInt(1));
				post.setUid(resultSet.getInt(2));
				post.setRetyeId(resultSet.getInt(3));
				post.setPofilelist(resultSet.getString(4));
				post.setPotype(resultSet.getString(5));
				post.setAppreciate(resultSet.getInt(6));
				post.setPettype(resultSet.getString(7));
				post.setPobegintime(resultSet.getString(8));
				post.setPoendtime(resultSet.getString(9));
				post.setPocontext (resultSet.getString(10));
				post.setPotime(resultSet.getObject(11).toString());
				post.setPomark(resultSet.getString(12));
				lstPosts.add(post);
			}
			return lstPosts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally{
			this.connectionManager.closeConnection(conn);
		}
	}
	@Override
	public List<Posts> selectByRetypeId(int retypeid) {
		// TODO Auto-generated method stub
		String strSQL = "select * from posts where retypeid=? order by potime asc";
		Connection conn = this.connectionManager.openConnection();
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, new Object[]{retypeid });
		List<Posts> lstPosts = new ArrayList<Posts>();
		try {
			while(resultSet.next()){
				Posts post = new Posts();
				post.setPoid(resultSet.getInt(1));
				post.setUid(resultSet.getInt(2));
				post.setRetyeId(resultSet.getInt(3));
				post.setPofilelist(resultSet.getString(4));
				post.setPotype(resultSet.getString(5));
				post.setAppreciate(resultSet.getInt(6));
				post.setPettype(resultSet.getString(7));
				post.setPobegintime(resultSet.getString(8));
				post.setPoendtime(resultSet.getString(9));
				post.setPocontext (resultSet.getString(10));
				post.setPotime(resultSet.getObject(11).toString());
				post.setPomark(resultSet.getString(12));
				lstPosts.add(post);
			}
			return lstPosts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally{
			this.connectionManager.closeConnection(conn);
		}
	}

	@Override
	   public List<Posts> selectApplyPostsByUid(int uid) {
		// TODO Auto-generated method stub
		String strSQL="select * from posts where ( potype='tradeApply' or potype='tradeRefused' or potype='tradeConfirm') and uid!=? and retypeid in (select (-tid) from trades where (uid=? or Use_uid=?) and (tstatus='Apply' or tstatus='Refused' or tstatus='Deal') ) order by potime desc";
		Connection conn=this.connectionManager.openConnection();
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, new Object[]{uid,uid,uid });
		List<Posts> lstPosts = new ArrayList<Posts>();
		try {
			while(resultSet.next()){
				Posts post = new Posts();
				post.setPoid(resultSet.getInt(1));
				post.setUid(resultSet.getInt(2));
				post.setRetyeId(resultSet.getInt(3));
				post.setPofilelist(resultSet.getString(4));
				post.setPotype(resultSet.getString(5));
				post.setAppreciate(resultSet.getInt(6));
				post.setPettype(resultSet.getString(7));
				post.setPobegintime(resultSet.getString(8));
				post.setPoendtime(resultSet.getString(9));
				post.setPocontext (resultSet.getString(10));
				post.setPotime(resultSet.getObject(11).toString());
				post.setPomark(resultSet.getString(12));
				lstPosts.add(post);
			}
			return lstPosts;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally{
			this.connectionManager.closeConnection(conn);
		}
		
	}
		
		public int updateByTid(int retypeid, String potype) {
			// TODO Auto-generated method stub
			Connection conn=this.connectionManager.openConnection();
			TransactionManager.conn=conn;
			TransactionManager.beginTransaction();
			String strSQL = "update posts set potype='invalid' where retypeid=? and potype=?";
			Object[] params = new Object[]{(-retypeid), potype};
			int affectedRows=dbUtils.execOthers(conn, strSQL, params);
		    if (affectedRows > 0) {
				TransactionManager.commit(); // 事务提交
			} else {
				TransactionManager.rollback(); // 事务的回滚
			}
			return affectedRows;
		}
		
		@Override
		public List<Posts> selectTradePostsByUidByType(int uid) {
			// TODO Auto-generated method stub
			Connection conn=this.connectionManager.openConnection();
			String strSQL="select * from posts where (potype='tradeDeal' or potype like 'tradeDeal%') and uid!=? and (-retypeid) in(select tid from trades where (tstatus='Deal' or tstatus='DealEnd' or tstatus like 'DealEnd%') and (uid=? or Use_uid=?)) order by potime desc ";
			List<Posts> lstPosts = new ArrayList<Posts>();
			Object[] params = new Object[]{uid,uid,uid}; 
			ResultSet resultSet = dbUtils.execQuery(conn, strSQL, params);
			try {
				while(resultSet.next()){
					Posts post = new Posts();
					post.setPoid(resultSet.getInt(1));
					post.setUid(resultSet.getInt(2));
					post.setRetyeId(resultSet.getInt(3));
					post.setPofilelist(resultSet.getString(4));
					post.setPotype(resultSet.getString(5));
					post.setAppreciate(resultSet.getInt(6));
					post.setPettype(resultSet.getString(7));
					post.setPobegintime(resultSet.getString(8));
					post.setPoendtime(resultSet.getString(9));
					post.setPocontext (resultSet.getString(10));
					post.setPotime(resultSet.getObject(11).toString());
					post.setPomark(resultSet.getString(12));
					lstPosts.add(post);
					
				}
				return lstPosts;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}finally{
				this.connectionManager.closeConnection(conn);
			}
			
			
	
   }
		@Override
		public int updateByPoid(int poid) {
			// TODO Auto-generated method stub
			Connection conn = this.connectionManager.openConnection();
			TransactionManager.conn = conn;
			TransactionManager.beginTransaction();
			String strSQL = "update posts set potype='invalid' where poid=?";
			Object[] params = new Object[]{poid};
			int affactedRows = this.dbUtils.execOthers(conn, strSQL, params);
			if (affactedRows > 0){
				TransactionManager.commit();
			}else{
				TransactionManager.rollback();
			}
			
			return affactedRows;
		}
		@Override
		public List<Posts> selectAllOwnPostsByUid(int uid,int pos,int pageSize) {
			// TODO Auto-generated method stub
			String strSQL = "select * from posts where uid=? and(potype like '%Sender%' or potype like '%Share%' or potype like '%Receive%' or potype like '%Deal%')  order by potime desc limit ?,? ";
			Connection conn = this.connectionManager.openConnection();
			List<Posts> lstPosts= new ArrayList<Posts>();
			ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, new Object[]{uid,pos,pageSize});
			try {
				while(resultSet.next()){
					Posts post = new Posts();
					post.setPoid(resultSet.getInt(1));
					post.setUid(resultSet.getInt(2));
					post.setRetyeId(resultSet.getInt(3));
					post.setPofilelist(resultSet.getString(4));
					post.setPotype(resultSet.getString(5));
					post.setAppreciate(resultSet.getInt(6));
					post.setPettype(resultSet.getString(7));
					post.setPobegintime(resultSet.getString(8));
					post.setPoendtime(resultSet.getString(9));
					post.setPocontext (resultSet.getString(10));
					post.setPotime(resultSet.getObject(11).toString());
					post.setPomark(resultSet.getString(12));
					lstPosts.add(post);
					
				}
				return lstPosts;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}finally{
				this.connectionManager.closeConnection(conn);
			}
			
			
		}
		
		@Override
		public List<Posts> selectPosts(String potype, String pettype,
				String province, String city, String district, String pobegintime,
				String poendtime,int pos,int pagesize) {
			// TODO Auto-generated method stub
			Connection conn = this.connectionManager.openConnection();
			String strSQL = null;
			Object[] params = null;
			if (pettype.equals("all") && pobegintime.isEmpty()
					&& poendtime.isEmpty()) {
				strSQL = "select p.poid,p.uid,p.retypeid,p.pofilelist,p.potype,p.appreciate,p.pettype,p.pobegintime,p.poendtime,p.pocontext,p.potime,p.pomark from posts as p,users as u "
						+ "where p.potype=? and u.province=? and u.city=? "
						+ "and u.district=? and u.uid=p.uid limit ?,?";
				params = new Object[] { potype, province, city, district,pos,pagesize };
			} else if (!pettype.equals("all") && pobegintime.isEmpty()
					&& poendtime.isEmpty()) {
				strSQL = "select p.poid,p.uid,p.retypeid,p.pofilelist,p.potype,p.appreciate,p.pettype,p.pobegintime,p.poendtime,p.pocontext,p.potime,p.pomark from posts as p,users as u "
						+ "where p.potype=? and p.pettype like '%"+pettype+"%'"
						+ "and u.province=? and u.city=? and u.district=? and u.uid=p.uid limit ?,?";
				params = new Object[] { potype, province, city, district,pos,pagesize };
			} else if (pettype.equals("all") && !pobegintime.isEmpty()
					&& poendtime.isEmpty()) {
				strSQL = "select p.poid,p.uid,p.retypeid,p.pofilelist,p.potype,p.appreciate,p.pettype,p.pobegintime,p.poendtime,p.pocontext,p.potime,p.pomark from posts as p,users as u "
						+ "where p.potype=? and u.province=? and u.city=? and u.district=? and "
						+ "p.pobegintime>=? and u.uid=p.uid limit ?,?";
				params = new Object[] { potype, province, city, district,
						pobegintime ,pos,pagesize};
			} else if (pettype.equals("all") && pobegintime.isEmpty()
					&& !poendtime.isEmpty()) {
				strSQL = "select p.poid,p.uid,p.retypeid,p.pofilelist,p.potype,p.appreciate,p.pettype,p.pobegintime,p.poendtime,p.pocontext,p.potime,p.pomark from posts as p,users as u "
						+ "where p.potype=? and u.province=? and u.city=? and u.district=? and "
						+ "p.poendtime<=? and u.uid=p.uid limit ?,?";
				params = new Object[] { potype, province, city, district, poendtime ,pos,pagesize};
			} else if (!pettype.equals("all") && !pobegintime.isEmpty()
					&& poendtime.isEmpty()) {
				strSQL = "select p.poid,p.uid,p.retypeid,p.pofilelist,p.potype,p.appreciate,p.pettype,p.pobegintime,p.poendtime,p.pocontext,p.potime,p.pomark from posts as p,users as u "
						+ "where p.potype=? and p.pettype like '%"+pettype+"%'"
						+ "and u.province=? and u.city=? and u.district=? and p.pobegintime>=? and u.uid=p.uid limit ?,?";
				params = new Object[] { potype,  province, city, district,
						pobegintime ,pos,pagesize};
			} else if (!pettype.equals("all") && pobegintime.isEmpty()
					&& !poendtime.isEmpty()) {
				strSQL = "select p.poid,p.uid,p.retypeid,p.pofilelist,p.potype,p.appreciate,p.pettype,p.pobegintime,p.poendtime,p.pocontext,p.potime,p.pomark from posts as p,users as u "
						+ "where p.potype=? and p.pettype like '%"+pettype+"%'"
						+ "and u.province=? and u.city=? and u.district=? and p.poendtime<=? and u.uid=p.uid limit ?,?";
				params = new Object[] { potype, province, city, district,
						poendtime ,pos,pagesize};
			} else if (pettype.equals("all") && !pobegintime.isEmpty()
					&& !poendtime.isEmpty()) {
				strSQL = "select p.poid,p.uid,p.retypeid,p.pofilelist,p.potype,p.appreciate,p.pettype,p.pobegintime,p.poendtime,p.pocontext,p.potime,p.pomark from posts as p,users as u "
						+ "where p.potype=? and u.province=? and u.city=? "
						+ "and u.district=? and p.pobegintime>=? and p.poendtime<=? and u.uid=p.uid limit ?,?";
				params = new Object[] { potype, province, city, district,
						pobegintime, poendtime ,pos,pagesize};
			} else if (!pettype.equals("all") && !pobegintime.isEmpty()
					&& !poendtime.isEmpty()) {
				strSQL = "select p.poid,p.uid,p.retypeid,p.pofilelist,p.potype,p.appreciate,p.pettype,p.pobegintime,p.poendtime,p.pocontext,p.potime,p.pomark from posts as p,users as u "
						+ "where p.potype=? and p.pettype like '%"+pettype+"%'"
						+ "and u.province=? and u.city=? and u.district=? and p.pobegintime>=? and p.poendtime<=? and u.uid=p.uid limit ?,?";
				params = new Object[] { potype, province, city, district,
						pobegintime, poendtime ,pos,pagesize};
			}
			List<Posts> lstPosts = new ArrayList<Posts>();
			ResultSet resultSet = dbUtils.execQuery(conn, strSQL, params);
			try {
				while (resultSet.next()) {
					Posts post = new Posts();
					post.setPoid(resultSet.getInt(1));
					post.setUid(resultSet.getInt(2));
					post.setRetyeId(resultSet.getInt(3));
					post.setPofilelist(resultSet.getString(4));
					post.setPotype(resultSet.getString(5));
					post.setAppreciate(resultSet.getInt(6));
					post.setPettype(resultSet.getString(7));
					post.setPobegintime(resultSet.getString(8));
					post.setPoendtime(resultSet.getString(9));
					post.setPocontext(resultSet.getString(10));
					post.setPotime(resultSet.getObject(11).toString());
					post.setPomark(resultSet.getString(12));
					lstPosts.add(post);
				}
				return lstPosts;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} finally {
				this.connectionManager.closeConnection(conn);
			}
		}

		@Override
		public List<Posts> selectFriendPostsByUid(int uid) {
			// TODO Auto-generated method stub
			String strSQL="select * from posts where (potype not like 'trade%' and potype!='invalid' and potype!='replyPosts')and posts.uid in (select use_uid from friends where uid=?)";
			Connection conn=this.connectionManager.openConnection();
			ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, new Object[]{uid });
			List<Posts> lstPosts = new ArrayList<Posts>();
			try {
				while(resultSet.next()){
					Posts post = new Posts();
					post.setPoid(resultSet.getInt(1));
					post.setUid(resultSet.getInt(2));
					post.setRetyeId(resultSet.getInt(3));
					post.setPofilelist(resultSet.getString(4));
					post.setPotype(resultSet.getString(5));
					post.setAppreciate(resultSet.getInt(6));
					post.setPettype(resultSet.getString(7));
					post.setPobegintime(resultSet.getString(8));
					post.setPoendtime(resultSet.getString(9));
					post.setPocontext (resultSet.getString(10));
					post.setPotime(resultSet.getObject(11).toString());
					post.setPomark(resultSet.getString(12));
					lstPosts.add(post);
				}
				return lstPosts;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} finally{
				this.connectionManager.closeConnection(conn);
			}
			
		}
		@Override
		public boolean updateAppByPoid(int poid, int app) {
			// TODO Auto-generated method stub
			Connection conn = this.connectionManager.openConnection();
			String strSQL="update posts set appreciate=? where poid=?";
			Object[] params = new Object[]{app,poid};
			int affectedRows = this.dbUtils.execOthers(conn, strSQL, params);
			if(affectedRows>0){
				return true;
			}
			return false;
		}


}
