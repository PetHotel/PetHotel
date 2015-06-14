package org.cpu.pethotel.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Chats implements Serializable {
	private int cid;
	private int uid;
	private int Use_uid;
	private String context;
	private String cfid;
	private String uchmark;
	public Chats() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Chats(int cid, int uid, int use_uid, String context, String cfid,
			String uchmark) {
		super();
		this.cid = cid;
		this.uid = uid;
		Use_uid = use_uid;
		this.context = context;
		this.cfid = cfid;
		this.uchmark = uchmark;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
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
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getCfid() {
		return cfid;
	}
	public void setCfid(String cfid) {
		this.cfid = cfid;
	}
	public String getUchmark() {
		return uchmark;
	}
	public void setUchmark(String uchmark) {
		this.uchmark = uchmark;
	}
	@Override
	public String toString() {
		return "Chats [cid=" + cid + ", uid=" + uid + ", Use_uid=" + Use_uid
				+ ", context=" + context + ", cfid=" + cfid + ", uchmark="
				+ uchmark + "]";
	}
	
	
	
}
