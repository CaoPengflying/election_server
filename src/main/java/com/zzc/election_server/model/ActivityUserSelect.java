package com.zzc.election_server.model;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import javax.persistence.*;
@Table(schema = "`count_votes`", name = "activity_user_select")
public class ActivityUserSelect {
	@Id
	@Column(name = "activity_user_select_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer activityUserSelectId;//

	@Column(name = "user_id")
	private Integer userId;//参选人

	@Column(name = "student_id")
	private Integer studentId;//投票人


	public void setActivityUserSelectId(Integer activityUserSelectId) {
		this.activityUserSelectId = activityUserSelectId;
	}
	public Integer getActivityUserSelectId() {
		return activityUserSelectId;
	}
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
}

