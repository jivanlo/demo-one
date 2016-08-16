package com.demo.concept.facade.impl;

import com.demo.concept.facade.UserFacade;
import com.demo.concept.persistence.entity.UserEntity;
import com.demo.concept.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by admin on 8/13/16.
 */
@Component("userFacadeImpl")
public class UserFacadeImpl implements UserFacade
{
	@Autowired
	private UserService userService;

	@Override
	public UserEntity findByUserId(final Integer userId)
	{
		return this.userService.findByUserId(userId);
	}

	@Override
	public Iterable<UserEntity> findAllUsers()
	{
		return this.userService.findAllUsers();
	}

	@Override
	public UserEntity saveUser(final UserEntity user)
	{
		return this.userService.saveUser(user);
	}

	@Override
	public UserEntity updateUser(final UserEntity user)
	{
		return this.userService.updateUser(user);
	}

	@Override
	public void deleteUser(final Integer userId)
	{
		this.userService.deleteUser(userId);
	}
}
