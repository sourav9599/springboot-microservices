package com.mindtree.departmentservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.departmentservice.entity.Department;
import com.mindtree.departmentservice.repository.H2db;
@Service
public class DepartmentService {
    
	@Autowired
	private H2db repository;
   
	public Long saveDepartment(Department department) {
		repository.save(department);
		return department.getId();
	}

	public Optional<Department> findDepartmentById(Long id) {
		
		return repository.findById(id);
	}

}
