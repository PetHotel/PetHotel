package org.cpu.pethotel.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Users implements Serializable {
	private int uid;
	private String uname;
	private String password;
	private String province;
	private String city;
	private String district;
	private String address;
	private String ufid;
	private int reputation;
	private boolean isAdopt;
	private String qq;
	private String email;
	private String tel;
	private String headportrait;
	private String umark;
	private int status;
	
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(int uid, String uname, String password, String province,
			String city, String district, String address, String ufid,
			int reputation, boolean isAdopt, String qq, String email,
			String tel, String headportrait, String umark, int status) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.password = password;
		this.province = province;
		this.city = city;
		this.district = district;
		this.address = address;
		this.ufid = ufid;
		this.reputation = reputation;
		this.isAdopt = isAdopt;
		this.qq = qq;
		this.email = email;
		this.tel = tel;
		this.headportrait = headportrait;
		this.umark = umark;
		this.status = status;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUfid() {
		return ufid;
	}

	public void setUfid(String ufid) {
		this.ufid = ufid;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public boolean isAdopt() {
		return isAdopt;
	}

	public void setAdopt(boolean isAdopt) {
		this.isAdopt = isAdopt;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getHeadportrait() {
		return headportrait;
	}

	public void setHeadportrait(String headportrait) {
		this.headportrait = headportrait;
	}

	public String getUmark() {
		return umark;
	}

	public void setUmark(String umark) {
		this.umark = umark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Users [uid=" + uid + ", uname=" + uname + ", password="
				+ password + ", province=" + province + ", city=" + city
				+ ", district=" + district + ", address=" + address + ", ufid="
				+ ufid + ", reputation=" + reputation + ", isAdopt=" + isAdopt
				+ ", qq=" + qq + ", email=" + email + ", tel=" + tel
				+ ", headportrait=" + headportrait + ", umark=" + umark
				+ ", status=" + status + "]";
	}

	

	

}
