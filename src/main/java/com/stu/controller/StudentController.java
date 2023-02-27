package com.stu.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stu.dto.StudentDto;
import com.stu.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService service;

	@PostMapping(value = "/save", consumes = "application/json")
	public String saveStudent(@RequestBody StudentDto dto) {
		return service.validateAndSaveStudent(dto);
	}

	@GetMapping("/alive")
	public String isAlive() {
		return "application is running";
	}

	@PutMapping(value = "/update", consumes = "application/json")
	public String updateStudent(@RequestBody StudentDto dto) {
		return service.updateStudent(dto);
	}

}
