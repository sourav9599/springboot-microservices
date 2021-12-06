package com.mindtree.userservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mindtree.userservice.VO.Department;
import com.mindtree.userservice.VO.ResponseTemplateVO;
import com.mindtree.userservice.entity.User;
import com.mindtree.userservice.repository.H2user;

@Service
public class UserService {
	
	@Autowired
	private H2user repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Long saveUser(User user) {
		repository.save(user);
		return user.getUserId();
	}
	public ResponseTemplateVO getUser(Long id) {
		ResponseTemplateVO response = new ResponseTemplateVO();
		User user = repository.findById(id).get();
		response.setUser(user);
		response.setDepartment(restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+ user.getId(), Department.class));
		return response;
	}

}
