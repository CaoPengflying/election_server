package com.zzc.election_server.model;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.math.BigDecimal;
import javax.persistence.*;
@Table(schema = "`zzc_election`", name = "tb_teacher")
public class Teacher {
	@Id
	@Column(name = "teacher_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer teacherId;//

	@Column(name = "teacher_name")
	private String teacherName;//

	@Column(name = "class_id")
	private Integer classId;//

	@Column(name = "teacher_no")
	private Integer teacherNo;//

	@Column(name = "sex")
	private String sex;//


	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setTeacherNo(Integer teacherNo) {
		this.teacherNo = teacherNo;
	}
	public Integer getTeacherNo() {
		return teacherNo;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSex() {
		return sex;
	}
}

