package com.stu.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StudentDto {
	
	private int id;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String section;
	private String gender;
	private Double marks1;
	private Double marks2;
	private Double marks3;

}
