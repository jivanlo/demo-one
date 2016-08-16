package com.demo.concept.service.impl;

import com.demo.concept.persistence.entity.UserEntity;
import com.demo.concept.persistence.repository.UserRepository;
import com.demo.concept.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by admin on 8/12/16.
 */
@Component("userService")
public class UserServiceImpl implements UserService
{

	@Autowired
	private UserRepository userRepository;

	public UserEntity findByUserId(final Integer userId)
	{
		return this.userRepository.findOne(userId);
	}

	public Iterable<UserEntity> findAllUsers()
	{
		return this.userRepository.findAll();
	}

	public UserEntity saveUser(final UserEntity user)
	{
		return this.userRepository.save(user);
	}

	public UserEntity updateUser(final UserEntity user)
	{
		return this.userRepository.save(user);
	}

	public void deleteUser(final Integer userId)
	{
		this.userRepository.delete(userId);
	}
}
