package org.cpu.pethotel.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Showtrades implements Serializable{
	
//	Showtrades showTrades=new Showtrades();
//	showTrades.setTid(resultSet.getInt(1));
//	showTrades.setUid(resultSet.getInt(2));
//	showTrades.setUname(resultSet.getString(3));
//	showTrades.setPname(resultSet.getString(4));
//	showTrades.setPtype(resultSet.getString(5));
//	showTrades.setPvariety(resultSet.getString(6));
//	showTrades.setU1name(resultSet.getString(7));
//	showTrades.settBeginDate(resultSet.getDate(8));
//	showTrades.settEndDate(resultSet.getDate(9));
//	showTrades.settStaus(resultSet.getString(10));

	private int tid;
	private int uid;
	private String uname;
	private String pname;
	private String ptype;
	private String pvariety;
	private String u1name;
	private String tBeginDate;
	private String tEndDate;
	private String tStatus;
	
	public Showtrades() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Showtrades(int tid, int uid, String uname, String pname,
			String ptype, String pvariety, String u1name, String tBeginDate,
			String tEndDate, String tStatus) {
		super();
		this.tid = tid;
		this.uid = uid;
		this.uname = uname;
		this.pname = pname;
		this.ptype = ptype;
		this.pvariety = pvariety;
		this.u1name = u1name;
		this.tBeginDate = tBeginDate;
		this.tEndDate = tEndDate;
		this.tStatus = tStatus;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getPvariety() {
		return pvariety;
	}

	public void setPvariety(String pvariety) {
		this.pvariety = pvariety;
	}

	public String getU1name() {
		return u1name;
	}

	public void setU1name(String u1name) {
		this.u1name = u1name;
	}

	public String gettBeginDate() {
		return tBeginDate;
	}

	public void settBeginDate(String tBeginDate) {
		this.tBeginDate = tBeginDate;
	}

	public String gettEndDate() {
		return tEndDate;
	}

	public void settEndDate(String tEndDate) {
		this.tEndDate = tEndDate;
	}

	public String gettStatus() {
		return tStatus;
	}

	public void settStatus(String tStatus) {
		this.tStatus = tStatus;
	}

	@Override
	public String toString() {
		return "Showtrades [tid=" + tid + ", uid=" + uid + ", uname=" + uname
				+ ", pname=" + pname + ", ptype=" + ptype + ", pvariety="
				+ pvariety + ", u1name=" + u1name + ", tBeginDate="
				+ tBeginDate + ", tEndDate=" + tEndDate + ", tStatus="
				+ tStatus + "]";
	}
	
	

}