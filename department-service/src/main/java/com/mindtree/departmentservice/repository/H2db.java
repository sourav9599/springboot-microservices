package com.mindtree.departmentservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.mindtree.departmentservice.entity.Department;

@Repository
public interface H2db extends JpaRepository<Department, Long>{


}
