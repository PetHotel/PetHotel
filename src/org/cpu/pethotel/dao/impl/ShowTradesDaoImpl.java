package org.cpu.pethotel.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cpu.pethotel.dao.IShowTradesDao;
import org.cpu.pethotel.db.ConnectionManager;
import org.cpu.pethotel.db.DBUtils;
import org.cpu.pethotel.po.Showtrades;



public class ShowTradesDaoImpl implements IShowTradesDao {

		private ConnectionManager connectionManager;
		private DBUtils dbUtils;
		
		
		public ShowTradesDaoImpl() {
			super();
			// TODO Auto-generated constructor stub
			this.connectionManager = new ConnectionManager();
			this.dbUtils = new DBUtils();
		}


		@Override
		public List<Showtrades> selectAll(int uid) {
			// TODO Auto-generated method stub
			String strSQL = "select * from showtrades where uid=? order by tid desc";
			Connection conn = this.connectionManager.openConnection();
			Object[] params=new Object[]{uid};
			ResultSet resultSet = this.dbUtils.execQuery(conn, strSQL, params);
			List<Showtrades> lstShowTrades = new ArrayList<Showtrades>();
			try {
				while(resultSet.next()){
					Showtrades showTrades=new Showtrades();
					showTrades.setTid(resultSet.getInt(1));
					showTrades.setUid(resultSet.getInt(2));
					showTrades.setUname(resultSet.getString(3));
					showTrades.setPname(resultSet.getString(4));
					showTrades.setPtype(resultSet.getString(5));
					showTrades.setPvariety(resultSet.getString(6));
					showTrades.setU1name(resultSet.getString(7));
					showTrades.settBeginDate(resultSet.getDate(8).toString());
					showTrades.settEndDate(resultSet.getDate(9).toString());
					showTrades.settStatus(resultSet.getString(10));

					System.out.println(showTrades.toString());
					lstShowTrades.add(showTrades);
				}
				return lstShowTrades;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} finally{
				this.connectionManager.closeConnection(conn);
			}
		}
}
