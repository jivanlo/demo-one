package com.demo.concept.service;

import com.demo.concept.persistence.entity.UserEntity;


/**
 * Created by admin on 8/12/16.
 */
public interface UserService
{
	public UserEntity findByUserId(Integer userId);

	public Iterable<UserEntity> findAllUsers();

	public UserEntity saveUser(UserEntity user);

	public UserEntity updateUser(UserEntity user);

	public void deleteUser(Integer userId);
}
