package org.cpu.pethotel.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Friends implements Serializable {
	private int fsid;
	private int uid;
	private int Use_uid;
	public Friends() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Friends(int fsid, int uid, int use_uid) {
		super();
		this.fsid = fsid;
		this.uid = uid;
		Use_uid = use_uid;
	}
	public int getFsid() {
		return fsid;
	}
	public void setFsid(int fsid) {
		this.fsid = fsid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getUse_uid() {
		return Use_uid;
	}
	public void setUse_uid(int use_uid) {
		Use_uid = use_uid;
	}
	@Override
	public String toString() {
		return "Friends [fsid=" + fsid + ", uid=" + uid + ", Use_uid="
				+ Use_uid + "]";
	}
	
	
}
