package org.cpu.pethotel.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cpu.pethotel.dao.IPetsDao;
import org.cpu.pethotel.po.Pets;
import org.cpu.pethotel.db.ConnectionManager;
import org.cpu.pethotel.db.TransactionManager;
import org.cpu.pethotel.db.DBUtils;


public class PetsDaoImpl implements IPetsDao {
	  private ConnectionManager connectionManager;
	  private DBUtils dbUtils;
	  private Connection conn;
	  public PetsDaoImpl() {
		super();
		connectionManager=new ConnectionManager();
		dbUtils = new DBUtils();
	}

	@Override
	public int insert(Pets pets) {
		// TODO Auto-generated method stub
		this.conn=this.connectionManager.openConnection();
		TransactionManager.conn=conn;
		TransactionManager.beginTransaction();
		int uid = pets.getUid();
		String pnickname=pets.getPnickname();
		String ptype=pets.getPtype();
		String pvariety=pets.getPvariety();
		String pbirthday=pets.getPbirthday();
		String pgender=pets.getPgender();
		String pfid=pets.getPfid();
		String pmark=pets.getPmark();
		
		String strSQL="insert into pets values(null,?,?,?,?,?,?,?,?)";
		Object[] params = new Object[]{uid,pnickname,ptype, pvariety,pbirthday,pgender,pfid,pmark};
		int affectedRows=this.dbUtils.execOthers(conn, strSQL, params);
		
		if(affectedRows > 0){
			TransactionManager.commit();
		
		}else{
			
			TransactionManager.rollback();
		
		}	
		return affectedRows;
	}
	@Override
	public int delete(int pid) {
		// TODO Auto-generated method stub
		this.conn=this.connectionManager.openConnection();
		TransactionManager.conn=conn;
		TransactionManager.beginTransaction();
		String strSQL="delete from pets where pid=?";
		Object[] params = new Object[]{pid};
		int affectedRows=this.dbUtils.execOthers(conn, strSQL, params);
		
		if(affectedRows > 0){
			TransactionManager.commit();
		
		}else{
			
			TransactionManager.rollback();
		
		}	
		return affectedRows;
		
		
	}
    
	  // 按宠物编号查询
	@Override
	public Pets selectByPid(int pid) {            
		// TODO Auto-generated method stub
		this.conn= this.connectionManager.openConnection();
		String strSQL="select * from pets where pid=?";
		Object[] params = new Object[]{pid};
		ResultSet resultSet=dbUtils.execQuery(conn, strSQL, params);
		Pets pets = new Pets();
		int pc=0;
		String pgender="";
		try {
			if(resultSet.next()){
				pets.setPid(resultSet.getInt(1));
				pets.setUid(resultSet.getInt(2));
				pets.setPnickname(resultSet.getString(3));
				pets.setPtype(resultSet.getString(4));
				pets.setPvariety(resultSet.getString(5));
				String pbirthday=resultSet.getDate(6).toString();
				pbirthday=pbirthday.substring(0,pbirthday.lastIndexOf("-"));
				pets.setPbirthday(pbirthday);
				pc=resultSet.getInt(7);
				pets.setPfid(resultSet.getString(8));
				pets.setPmark(resultSet.getString(9));
				
				if(pc==1){
					pgender="公";
				}
				if(pc==2){
					pgender="母";
				}if(pc==3){
					pgender="其他";
				}
				pets.setPgender(pgender);
				return pets;
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
    //按主人编号查询
	@Override
	public List<Pets> selectAllByUid(int uid) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// 步骤1：创建一个空的集合准备存放查询的结果
		List<Pets> lstPets = new ArrayList<Pets>();
		// 步骤2：获取一个数据库的连接对象
		this.conn = connectionManager.openConnection();
		// 步骤3：创建查询语句的模板
		String strSQL = "select * from pets where uid=?";
		// 步骤4：使用dbutils方法实现查询操作
		ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, new Object[]{uid});
		// 步骤5：将resultSet结果集转换成List数据结构
		int pc=0;
		String pgender="";
		try {
			while(resultSet.next()){
				// 步骤5-1：创建一个对象
				Pets pets= new Pets();
				pets.setPid(resultSet.getInt(1));
				pets.setUid(resultSet.getInt(2));
				pets.setPnickname(resultSet.getString(3));
				pets.setPtype(resultSet.getString(4));
				pets.setPvariety(resultSet.getString(5));
				String pbirthday=resultSet.getDate(6).toString();
				pbirthday=pbirthday.substring(0,pbirthday.lastIndexOf("-"));
				pets.setPbirthday(pbirthday);
				
				pc=resultSet.getInt(7);
				if(pc==1){
					pgender="公";
				}
				if(pc==2){
					pgender="母";
				}if(pc==3){
					pgender="其他";
				}
				pets.setPgender(pgender);
				pets.setPfid(resultSet.getString(8));
				pets.setPmark(resultSet.getString(9));
				// 步骤5-2：将封装好的对象添加到List集合中
				lstPets.add(pets);
			}
			// 返回结果
			return lstPets;
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
	public int update(Pets pets) {
		// TODO Auto-generated method stub
		//准备1：获取一个数据库的连接对象
		this.conn = this.connectionManager.openConnection();
		//准备2：开启一个事务处理
		TransactionManager.conn = conn;
		TransactionManager.beginTransaction();
		//步骤3：创建SQL语句模板
		String strSQL = "update pets set uid=?, pnickname=?,ptype=?,pvariety=?,pbirthday=?,pgender=?,pfid=?,pmark=? where pid=?";
		Object[] params = new Object[]{pets.getUid(),pets.getPnickname(),pets.getPtype(),pets.getPvariety(),pets.getPbirthday(),pets.getPgender(),pets.getPfid(),pets.getPmark(),pets.getPid()};
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
	public static void main(String[] args){
		 PetsDaoImpl  petsDao=new PetsDaoImpl();
		 System.out.println(petsDao.selectByPid(3));
	}
}
