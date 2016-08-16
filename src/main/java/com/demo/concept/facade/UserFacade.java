package com.demo.concept.facade;

import com.demo.concept.persistence.entity.UserEntity;


/**
 * Created by admin on 8/13/16.
 */
public interface UserFacade
{
	public UserEntity findByUserId(Integer userId);

	public Iterable<UserEntity> findAllUsers();

	public UserEntity saveUser(UserEntity user);

	public UserEntity updateUser(UserEntity user);

	public void deleteUser(Integer userId);
}
