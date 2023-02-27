package com.stu.service;

import com.stu.dto.StudentDto;

public interface StudentService {
	
	public String validateAndSaveStudent(StudentDto dto);
	public String updateStudent(StudentDto dto);

}
