package org.cpu.pethotel.po;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class UnUseUserInfo implements Serializable {
	private int unuseid;
	private int uid;
	private String userRealName;
	private String id;
	private Date date;
	private String ucmark;
	
	public UnUseUserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UnUseUserInfo(int unuserid, int uid, String userRealName, String id,
			Date date, String ucmark) {
		super();
		this.unuseid = unuserid;
		this.uid = uid;
		this.userRealName = userRealName;
		this.id = id;
		this.date = date;
		this.ucmark = ucmark;
	}

	public int getUnuserid() {
		return unuseid;
	}

	public void setUnuserid(int unuserid) {
		this.unuseid = unuserid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUcmark() {
		return ucmark;
	}

	public void setUcmark(String ucmark) {
		this.ucmark = ucmark;
	}

	@Override
	public String toString() {
		return "UnUseUserInfo [unuserid=" + unuseid + ", uid=" + uid
				+ ", userRealName=" + userRealName + ", id=" + id + ", date="
				+ date + ", ucmark=" + ucmark + "]";
	}
	
	
}
