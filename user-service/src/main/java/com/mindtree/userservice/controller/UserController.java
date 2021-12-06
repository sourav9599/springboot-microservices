package com.mindtree.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.userservice.VO.ResponseTemplateVO;
import com.mindtree.userservice.entity.User;
import com.mindtree.userservice.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService service;
	
	@PostMapping("/")
	public Long saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}
	
	@GetMapping("/{id}")
	public ResponseTemplateVO getUser(@PathVariable Long id)
	{
		return service.getUser(id);
	}
}
