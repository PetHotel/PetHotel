package org.cpu.pethotel.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Pets implements Serializable {
	private int pid;
	private int uid;
	private String pnickname;
	private String ptype;
	private String pvariety;
	private String pbirthday;
	private String pgender;
	private String pfid;
	private String pmark;
	
	public Pets() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Pets(int pid, int uid, String pnickname, String ptype,
			String pvariety, String pbirthday, String pgender, String pfid,
			String pmark) {
		super();
		this.pid = pid;
		this.uid = uid;
		this.pnickname = pnickname;
		this.ptype = ptype;
		this.pvariety = pvariety;
		this.pbirthday = pbirthday;
		this.pgender = pgender;
		this.pfid = pfid;
		this.pmark = pmark;
	}


	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getPnickname() {
		return pnickname;
	}

	public void setPnickname(String pnickname) {
		this.pnickname = pnickname;
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

	public String getPbirthday() {
		return pbirthday;
	}

	public void setPbirthday(String pbirthday) {
		this.pbirthday = pbirthday;
	}

	public String getPgender() {
		return pgender;
	}

	public void setPgender(String pgender) {
		this.pgender = pgender;
	}

	public String getPfid() {
		return pfid;
	}

	public void setPfid(String pfid) {
		this.pfid = pfid;
	}

	public String getPmark() {
		return pmark;
	}

	public void setPmark(String pmark) {
		this.pmark = pmark;
	}


	@Override
	public String toString() {
		return "Pets [pid=" + pid + ", uid=" + uid + ", pnickname=" + pnickname
				+ ", ptype=" + ptype + ", pvariety=" + pvariety
				+ ", pbirthday=" + pbirthday + ", pgender=" + pgender
				+ ", pfid=" + pfid + ", pmark=" + pmark + "]";
	}
	

}