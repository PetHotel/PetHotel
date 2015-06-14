package org.cpu.pethotel.po;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings({ "serial", "unused" })
public class Trades implements Serializable {
	private int tid;
	private int uid;
	private int pid;
	private int Use_uid;
	private String tBeginDate;
	private String tEndDate;
	private String tStatus;
	private String tPostList;
	private String tmark;
	
	public Trades() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Trades(int tid, int uid, int pid, int use_uid, String tBeginDate,
			String tEndDate, String tStaus, String tPostList, String tmark) {
		super();
		this.tid = tid;
		this.uid = uid;
		this.pid = pid;
		Use_uid = use_uid;
		this.tBeginDate = tBeginDate;
		this.tEndDate = tEndDate;
		this.tStatus = tStaus;
		this.tPostList = tPostList;
		this.tmark = tmark;
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

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getUse_uid() {
		return Use_uid;
	}

	public void setUse_uid(int use_uid) {
		Use_uid = use_uid;
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

	public String gettPostList() {
		return tPostList;
	}

	public void settPostList(String tPostList) {
		this.tPostList = tPostList;
	}

	public String getTmark() {
		return tmark;
	}

	public void setTmark(String tmark) {
		this.tmark = tmark;
	}

	@Override
	public String toString() {
		return "Trades [tid=" + tid + ", uid=" + uid + ", pid=" + pid
				+ ", Use_uid=" + Use_uid + ", tBeginDate=" + tBeginDate
				+ ", tEndDate=" + tEndDate + ", tStatus=" + tStatus
				+ ", tPostList=" + tPostList + ", tmark=" + tmark + "]";
	}
	
	

}