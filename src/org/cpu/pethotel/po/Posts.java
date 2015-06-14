package org.cpu.pethotel.po;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings({ "serial", "unused" })
public class Posts implements Serializable {
	private int poid;
	private int uid;
	private int retyeId;
	private String pofilelist;
	private String potype;
	private int appreciate;
	private String pettype;
	private String pobegintime;
	private String poendtime;
	private String pocontext;
	private String potime;
	private String pomark;
	public int getPoid() {
		return poid;
	}
	public void setPoid(int poid) {
		this.poid = poid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getRetyeId() {
		return retyeId;
	}
	public void setRetyeId(int retyeId) {
		this.retyeId = retyeId;
	}
	public String getPofilelist() {
		return pofilelist;
	}
	public void setPofilelist(String pofilelist) {
		this.pofilelist = pofilelist;
	}
	public String getPotype() {
		return potype;
	}
	public void setPotype(String potype) {
		this.potype = potype;
	}
	public int getAppreciate() {
		return appreciate;
	}
	public void setAppreciate(int appreciate) {
		this.appreciate = appreciate;
	}
	public String getPettype() {
		return pettype;
	}
	public void setPettype(String pettype) {
		this.pettype = pettype;
	}
	public String getPobegintime() {
		return pobegintime;
	}
	public void setPobegintime(String pobegintime) {
		this.pobegintime = pobegintime;
	}
	public String getPoendtime() {
		return poendtime;
	}
	public void setPoendtime(String poendtime) {
		this.poendtime = poendtime;
	}
	public String getPocontext() {
		return pocontext;
	}
	public void setPocontext(String pocontext) {
		this.pocontext = pocontext;
	}
	public String getPotime() {
		return potime;
	}
	public void setPotime(String potime) {
		this.potime = potime;
	}
	public String getPomark() {
		return pomark;
	}
	public void setPomark(String pomark) {
		this.pomark = pomark;
	}
	@Override
	public String toString() {
		return "Posts [poid=" + poid + ", uid=" + uid + ", retyeId=" + retyeId
				+ ", pofilelist=" + pofilelist + ", potype=" + potype
				+ ", appreciate=" + appreciate + ", pettype=" + pettype
				+ ", pobegintime=" + pobegintime + ", poendtime=" + poendtime
				+ ", pocontext=" + pocontext + ", potime=" + potime
				+ ", pomark=" + pomark + "]";
	}
	
	
	
	
}
