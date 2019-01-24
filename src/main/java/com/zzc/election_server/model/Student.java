package com.zzc.election_server.model;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import javax.persistence.*;
@Table(schema = "`count_votes`", name = "tb_student")
public class Student {
	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer studentId;//

	@Column(name = "student_no")
	private String studentNo;//学号

	@Column(name = "student_name")
	private String studentName;//

	@Column(name = "student_password")
	private String studentPassword;//

	@Column(name = "sex")
	private Boolean sex;//

	@Column(name = "id_cord")
	private String idCord;//身份证号

	@Column(name = "head_url")
	private String headUrl;//

	@Column(name = "grade_id")
	private Integer gradeId;//

	@Column(name = "role")
	private Integer role;//

	@Column(name = "ef01")
	private String ef01;//

	@Column(name = "ef02")
	private String ef02;//

	@Column(name = "ef03")
	private String ef03;//


	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}
	public String getStudentPassword() {
		return studentPassword;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public Boolean getSex() {
		return sex;
	}
	public void setIdCord(String idCord) {
		this.idCord = idCord;
	}
	public String getIdCord() {
		return idCord;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setRole(Integer role) {
		this.role = role;
	}
	public Integer getRole() {
		return role;
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

