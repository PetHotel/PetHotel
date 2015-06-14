package org.cpu.pethotel.po;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Resources implements Serializable {
	private int rid;
	private String rtype;
	private String raddress;
	private Timestamp updateTime;
	
	public Resources() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Resources(int rid, String rtype, String raddress,
			Timestamp updateTime) {
		super();
		this.rid = rid;
		this.rtype = rtype;
		this.raddress = raddress;
		this.updateTime = updateTime;
	}
	
	public int getRid() {
		return rid;
	}
	
	public void setRid(int rid) {
		this.rid = rid;
	}
	
	public String getRtype() {
		return rtype;
	}
	
	public void setRtype(String rtype) {
		this.rtype = rtype;
	}
	
	public String getRaddress() {
		return raddress;
	}
	
	public void setRaddress(String raddress) {
		this.raddress = raddress;
	}
	
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		return "Resources [rid=" + rid + ", rtype=" + rtype + ", raddress="
				+ raddress + ", updateTime=" + updateTime + "]";
	}
	
	
}
