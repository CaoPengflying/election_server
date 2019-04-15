package com.zzc.election_server.model;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import javax.persistence.*;
import java.util.Date;

@Table(schema = "`count_votes`", name = "tb_activity")
public class Activity {
	@Id
	@Column(name = "activity_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer activityId;//

	@Column(name = "activity_name")
	private String activityName;//

	@Column(name = "start_time")
	private Date startTime;//

	@Column(name = "end_time")
	private Date endTime;//

	@Column(name = "activity_describe")
	private String activityDescribe;//活动描述

	@Column(name = "create_user")
	private Integer createUser;//活动创建人

	@Column(name = "join_grade")
	private Integer joinGrade;//参与班级

	@Column(name = "num")
	private Integer num;//获奖人数

	@Column(name = "votes")
	private Integer votes;//学生拥有的票数

	@Column(name = "ef01")
	private String ef01;//

	@Column(name = "ef02")
	private String ef02;//

	@Column(name = "ef03")
	private String ef03;//


	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setActivityDescribe(String activityDescribe) {
		this.activityDescribe = activityDescribe;
	}
	public String getActivityDescribe() {
		return activityDescribe;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setJoinGrade(Integer joinGrade) {
		this.joinGrade = joinGrade;
	}
	public Integer getJoinGrade() {
		return joinGrade;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getNum() {
		return num;
	}
	public void setVotes(Integer votes) {
		this.votes = votes;
	}
	public Integer getVotes() {
		return votes;
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

