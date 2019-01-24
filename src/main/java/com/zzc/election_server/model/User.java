package com.zzc.election_server.model;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import javax.persistence.*;
@Table(schema = "`count_votes`", name = "activity_user")
public class User {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;//

	@Column(name = "student_id")
	private Integer studentId;//

	@Column(name = "vote")
	private Integer vote;//

	@Column(name = "status")
	private Integer status;//

	@Column(name = "activity_id")
	private Integer activityId;//

	@Column(name = "ef01")
	private String ef01;//

	@Column(name = "ef02")
	private String ef02;//

	@Column(name = "ef03")
	private String ef03;//


	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setVote(Integer vote) {
		this.vote = vote;
	}
	public Integer getVote() {
		return vote;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStatus() {
		return status;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setEf01(String ef01) {
		this.ef01 = ef01;
	}
	public String getEf01() {
		return ef01;
	}
	public void setEf02(String ef02) {
		this.ef02 = ef02;
	}
	public String getEf02() {
		return ef02;
	}
	public void setEf03(String ef03) {
		this.ef03 = ef03;
	}
	public String getEf03() {
		return ef03;
	}
}

