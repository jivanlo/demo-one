package com.demo.concept.facade;

import com.demo.concept.facade.impl.UserFacadeImpl;
import com.demo.concept.persistence.entity.UserEntity;
import com.demo.concept.service.UserService;
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
public class UserFacadeImplTest
{

	@Mock
	private UserService userService;

	@Spy
	@InjectMocks
	private final UserFacadeImpl userFacade = new UserFacadeImpl();

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
		when(this.userService.findByUserId(userId)).thenReturn(this.userEntity);

		final UserEntity userEntityBack = this.userFacade.findByUserId(userId);

		assertThat(userEntityBack.getUserId(), equalTo(1));
		assertThat(userEntityBack.getUserId(), equalTo(this.userEntity.getUserId()));
		assertThat(userEntityBack.getName(), equalTo(this.userEntity.getName()));
	}

	@Test
	public void findUserByIdNotFoundTest()
	{
		final Integer userId = 1;
		this.userEntity.setUserId(1);
		when(this.userService.findByUserId(userId)).thenReturn(null);

		final UserEntity userEntityBack = this.userFacade.findByUserId(userId);

		assertThat(userEntityBack, is(nullValue()));
	}

	@Test
	public void findAllUsersTest()
	{
		final List<UserEntity> usersList = new ArrayList<>();
		usersList.add(this.userEntity);
		usersList.add(this.userEntity);
		usersList.add(this.userEntity);

		when(this.userService.findAllUsers()).thenReturn(usersList);

		final Iterable<UserEntity> iterableBack = this.userFacade.findAllUsers();

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
		when(this.userService.saveUser(this.userEntity)).thenReturn(userEntitySave);

		final UserEntity userEntity = this.userFacade.saveUser(this.userEntity);

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

		when(this.userService.saveUser(this.userEntity)).thenReturn(userEntityUpdate);

		final UserEntity userEntity = this.userFacade.saveUser(this.userEntity);

		assertThat(userEntity.getUserId(), equalTo(1));
		assertThat(userEntity.getUserId(), equalTo(this.userEntity.getUserId()));
		assertThat(userEntity.getName(), equalTo(this.userEntity.getName()));
		assertThat(userEntity.getEmail(), equalTo("peter@otherservermail.com"));
	}

	@Test
	public void deleteUserTest()
	{
		final Integer userId = 1;
		doNothing().when(this.userService).deleteUser(userId);

		this.userFacade.deleteUser(userId);

		verify(this.userService, times(1)).deleteUser(userId);
	}
}
