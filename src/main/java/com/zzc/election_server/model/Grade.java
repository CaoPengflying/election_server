package com.zzc.election_server.model;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import javax.persistence.*;
@Table(schema = "`count_votes`", name = "tb_grade")
public class Grade {
	@Id
	@Column(name = "grade_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer gradeId;//

	@Column(name = "grade_no")
	private String gradeNo;//班级编号

	@Column(name = "grade_name")
	private String gradeName;//

	@Column(name = "ef01")
	private String ef01;//

	@Column(name = "ef02")
	private String ef02;//

	@Column(name = "ef03")
	private String ef03;//


	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeNo(String gradeNo) {
		this.gradeNo = gradeNo;
	}
	public String getGradeNo() {
		return gradeNo;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getGradeName() {
		return gradeName;
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

