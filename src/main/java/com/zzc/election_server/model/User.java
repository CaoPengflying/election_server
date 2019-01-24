package com.zzc.election_server.model;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

@Table(schema = "`zzc_election`", name = "tb_student")
public class User {
	@Id
	@Column(name = "userId")
	private Integer userid;//

	@Column(name = "username")
	private String username;//

	@Column(name = "password")
	private String password;//


	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
}

