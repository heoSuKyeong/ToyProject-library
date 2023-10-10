package com.project.library.model.vo;

public class UserVo {

	private String userNo;
	private String name;
	private String tel;
	private String email;
	private String id;
	private String pw;
	int level;
	
	public UserVo() {
		// TODO Auto-generated constructor stub
	}

	public UserVo(String userNo, String name, String tel, String email, String id, String pw, int level) {
		super();
		this.userNo = userNo;
		this.name = name;
		this.tel = tel;
		this.email = email;
		this.id = id;
		this.pw = pw;
		this.level = level;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "UserVo [userNo=" + userNo + ", name=" + name + ", tel=" + tel + ", email=" + email + ", id=" + id
				+ ", pw=" + pw + ", level=" + level + "]";
	}
	
	
	
	
}
