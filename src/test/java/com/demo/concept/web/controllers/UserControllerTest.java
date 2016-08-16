package com.demo.concept.web.controllers;

import com.demo.concept.facade.UserFacade;
import com.demo.concept.persistence.entity.UserEntity;
import com.demo.concept.web.assembler.UserResourceAssembler;
import com.demo.concept.web.resources.UserResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


/**
 * Created by admin on 8/15/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest
{
	@Mock
	private UserResourceAssembler userAssembler;

	@Mock
	private UserFacade userFacade;

	@Spy
	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;
	private UserEntity userEntity;
	private UserResource userResource;
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Before
	public void setup()
	{
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();

		this.userEntity = new UserEntity();
		this.userEntity.setUserId(1);
		this.userEntity.setName("Peter");
		this.userEntity.setLastName("Morris");
		this.userEntity.setEmail("peter@mailserver.com");
		this.userEntity.setPhone("12346567");

		this.userResource = new UserResource();
		this.userResource.setUserId(1);
		this.userResource.setName("Peter");
		this.userResource.setLastName("Morris");
		this.userResource.setEmail("peter@mailserver.com");
		this.userResource.setPhone("12346567");
	}

	@Test
	public void findByIdTest() throws JsonProcessingException, Exception
	{
		when(this.userFacade.findByUserId(any(Integer.class))).thenReturn(this.userEntity);
		when(this.userAssembler.toResource(this.userEntity)).thenReturn(this.userResource);

		final MvcResult result = this.mockMvc.perform(get("/users/1")).andReturn();

		final String responseJSON = result.getResponse().getContentAsString();

		assertThat(responseJSON, notNullValue());

		final UserResource response = this.objectMapper.readValue(responseJSON, UserResource.class);

		assertThat(response.getUserId(), equalTo(this.userEntity.getUserId()));
		assertThat(response.getName(), equalTo(this.userEntity.getName()));
		assertThat(response.getLastName(), equalTo(this.userEntity.getLastName()));
		assertThat(response.getEmail(), equalTo(this.userEntity.getEmail()));
		assertThat(response.getPhone(), equalTo(this.userEntity.getPhone()));

	}

	@Test
	public void findByIdNotFoundTest() throws JsonProcessingException, Exception
	{
		when(this.userFacade.findByUserId(any(Integer.class))).thenReturn(new UserEntity());
		when(this.userAssembler.toResource(this.userEntity)).thenReturn(new UserResource());

		final MvcResult result = this.mockMvc.perform(get("/users/1")).andReturn();

		final String responseJSON = result.getResponse().getContentAsString();

		assertThat(responseJSON, notNullValue());

	}
}
