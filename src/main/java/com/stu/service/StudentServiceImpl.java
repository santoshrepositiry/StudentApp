package com.stu.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stu.dto.StudentDto;
import com.stu.entity.Student;
import com.stu.repo.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository repo;

	@Override
	public String validateAndSaveStudent(StudentDto dto) {
		StringBuffer sb = new StringBuffer();
		boolean flag = true;
		String result = null;
		Double total = dto.getMarks1() + dto.getMarks2() + dto.getMarks3();
		Student student = new Student();
		if (dto.getFirstName().length() < 3) {
			flag = false;
			sb.append("first name should not be lessthan 3\n ");
		}
		if (dto.getLastName().length() < 3) {
			flag = false;
			sb.append("lastname should not be lessthat 3\n ");
		}
		if (validateAge(dto.getDob())) {
			flag = false;
			sb.append("age should not be lessthan 15 and grater than 20\n ");
		}
		if ((dto.getMarks1() < 0 && dto.getMarks1() > 100) && (dto.getMarks2() < 0 && dto.getMarks2() > 100)
				&& (dto.getMarks3() < 0 && dto.getMarks3() > 100)) {
			flag = false;
			sb.append("age must between 0-100\n ");
		}
		if (!(dto.getSection().equalsIgnoreCase("A") || dto.getSection().equalsIgnoreCase("B")
				|| dto.getSection().equalsIgnoreCase("C"))) {
			System.out.println("section is :: " + dto.getSection());
			System.out.println(dto.getSection().equalsIgnoreCase("A"));
			System.out.println(dto.getSection().equalsIgnoreCase("B"));
			System.out.println(dto.getSection().equalsIgnoreCase("C"));
			flag = false;
			sb.append("section must be A , B , C\n ");
		}
		if (!(dto.getGender().equalsIgnoreCase("male") || dto.getGender().equalsIgnoreCase("female"))) {
			flag = false;
			sb.append("invalid gender type\n ");
		}
		if ((dto.getMarks1() < 35.0) || (dto.getMarks2()< 35.0) || (dto.getMarks3() < 35.0)) {
			sb.append("mark should grater than 35\n ");
			flag = false;
		}
		if (!flag) {
			throw new IllegalArgumentException(sb.toString());
		}
		if (flag) {
			student.setFirstName(dto.getFirstName());
			student.setLastName(dto.getLastName());
			student.setDob(dto.getDob());
			student.setSection(dto.getSection());
			student.setGender(dto.getGender());
			student.setMarks1(dto.getMarks1());
			student.setMarks2(dto.getMarks2());
			student.setMarks3(dto.getMarks3());
			student.setTotal(total);
			student.setAverage(total / 3);
			if ((300 / 2) > total) {
				result = "bellow second";
			} else {
				result = "passed";
			}
			student.setResult(result);

			Student save = repo.save(student);

			return (save != null) ? "student saved"+student.toString() : "student not saved";
		} else {
			return "input date incorrect :: " + sb;
		}
	}

	private boolean validateAge(LocalDate date) {
		int years = Period.between(date, LocalDate.now()).getYears();
		return (years > 20 || years < 15) ? true : false;
	}

	public String updateStudent(StudentDto dto) {
		boolean flag=true;
		String result="not updated";
		StringBuffer sb=new StringBuffer();
		if ((dto.getMarks1() < 0 && dto.getMarks1() > 100) && (dto.getMarks2() < 0 && dto.getMarks2() > 100)
				&& (dto.getMarks3() < 0 && dto.getMarks3() > 100)) {
			flag = false;
			sb.append("age must between 0-100\n ");
		}
		if ((dto.getMarks1() < 35.0) || (dto.getMarks2()< 35.0) || (dto.getMarks3() < 35.0)) {
			sb.append("mark should grater than 35\n ");
			flag = false;
		}
		if (!flag) {
			throw new IllegalArgumentException(sb.toString());
		}
		if(flag) {
		Optional<Student> student = repo.findById(dto.getId());
		if(student.isPresent()) {
			Student stu = student.get();
			double mark=dto.getMarks1()+dto.getMarks2()+dto.getMarks3();
			stu.setMarks1(dto.getMarks1());
			stu.setMarks2(dto.getMarks2());
			stu.setMarks3(dto.getMarks3());
			stu.setAverage(mark/3);
			stu.setResult((150>mark)?"bellow second":"passed");
			Student save = repo.save(stu);
			if(save!=null) {
				result="updated"+stu.toString();
			}
		}
		}
		return result;
	}

}
