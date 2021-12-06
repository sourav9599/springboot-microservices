package com.mindtree.departmentservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.departmentservice.entity.Department;
import com.mindtree.departmentservice.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService service;
	
	@PostMapping("/")
    public Long saveDepartment(@RequestBody Department department) {
    	return service.saveDepartment(department);
    }
	
	@GetMapping("/{id}")
	public Optional<Department> getDepartment(@PathVariable Long id) {
		return service.findDepartmentById(id);
	}

}
