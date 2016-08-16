package com.demo.concept.service;

import com.demo.concept.persistence.entity.UserEntity;
import com.demo.concept.persistence.repository.UserRepository;
import com.demo.concept.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.*;



/**
 * Created by admin on 8/15/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest
{
	@Mock
	private UserRepository userRepository;

	@Spy
	@InjectMocks
	private final UserServiceImpl userService = new UserServiceImpl();

	private UserEntity userEntity;

	@Before
	public void setup()
	{
		this.userEntity = new UserEntity();
		this.userEntity.setName("peter");
		this.userEntity.setLastName("Morris");
		this.userEntity.setEmail("peter@mailserver.com");
		this.userEntity.setPhone("12346567");
	}

	@Test
	public void findUserByIdTest()
	{
		final Integer userId = 1;
		this.userEntity.setUserId(1);
		when(this.userRepository.findOne(userId)).thenReturn(this.userEntity);

		final UserEntity userEntityBack = this.userService.findByUserId(userId);

		assertThat(userEntityBack.getUserId(), equalTo(1));
		assertThat(userEntityBack.getUserId(), equalTo(this.userEntity.getUserId()));
		assertThat(userEntityBack.getName(), equalTo(this.userEntity.getName()));
	}

	@Test
	public void findUserByIdNotFoundTest()
	{
		final Integer userId = 1;
		this.userEntity.setUserId(1);
		when(this.userRepository.findOne(userId)).thenReturn(null);

		final UserEntity userEntityBack = this.userService.findByUserId(userId);

		assertThat(userEntityBack, is(nullValue()));
	}

	@Test
	public void findAllUsersTest()
	{
		final List<UserEntity> usersList = new ArrayList<>();
		usersList.add(this.userEntity);
		usersList.add(this.userEntity);
		usersList.add(this.userEntity);

		when(this.userRepository.findAll()).thenReturn(usersList);

		final Iterable<UserEntity> iterableBack = this.userService.findAllUsers();

		for (final UserEntity userEntity : iterableBack)
		{
			assertThat(userEntity.getUserId(), equalTo(this.userEntity.getUserId()));
			assertThat(userEntity.getName(), equalTo(this.userEntity.getName()));
		}
	}

	@Test
	public void saveUserTest()
	{
		final UserEntity userEntitySave = this.userEntity;
		userEntitySave.setUserId(1);
		when(this.userRepository.save(this.userEntity)).thenReturn(userEntitySave);

		final UserEntity userEntity = this.userService.saveUser(this.userEntity);

		assertThat(userEntity.getUserId(), equalTo(1));
		assertThat(userEntity.getUserId(), equalTo(this.userEntity.getUserId()));
		assertThat(userEntity.getName(), equalTo(this.userEntity.getName()));
	}

	@Test
	public void updateUserTest()
	{
		final UserEntity userEntityUpdate = this.userEntity;
		userEntityUpdate.setEmail("peter@otherservermail.com");
		userEntityUpdate.setUserId(1);

		when(this.userRepository.save(this.userEntity)).thenReturn(userEntityUpdate);

		final UserEntity userEntity = this.userService.saveUser(this.userEntity);

		assertThat(userEntity.getUserId(), equalTo(1));
		assertThat(userEntity.getUserId(), equalTo(this.userEntity.getUserId()));
		assertThat(userEntity.getName(), equalTo(this.userEntity.getName()));
		assertThat(userEntity.getEmail(), equalTo("peter@otherservermail.com"));
	}

	@Test
	public void deleteUserTest()
	{
		final Integer userId = 1;
		doNothing().when(this.userRepository).delete(userId);

		this.userService.deleteUser(userId);

		verify(this.userRepository, times(1)).delete(userId);
	}
}
