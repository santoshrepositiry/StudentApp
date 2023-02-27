package com.stu.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

@Entity
@Table(name = "STUDENT_INFO")
@Data
public class Student implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "FIRST_NAME", length = 20)
	@Type(type = "string")
	private String firstName;
	@Column(name = "LAST_NAME", length = 20)
	@Type(type = "string")
	private String lastName;
	@Column(name = "DOB", length = 20)
	private LocalDate dob;
	@Column(name = "SECTION", length = 20)
	@Type(type = "string")
	private String section;
	@Column(name = "GEN", length = 6)
	@Type(type = "string")
	private String gender;
	@Column(name = "M1", length = 6)
	private double marks1;
	@Column(name = "M2", length = 6)
	private double marks2;
	@Column(name = "M3", length = 6)
	private double marks3;
	@Column(name = "TOTAL", length = 6)
	private double total;
	@Column(name = "AVG", length = 6)
	private double average;
	@Column(name = "RESULT", length = 20)
	@Type(type = "string")
	private String result;

}
