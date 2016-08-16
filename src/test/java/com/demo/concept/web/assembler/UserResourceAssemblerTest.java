package com.demo.concept.web.assembler;

import com.demo.concept.persistence.entity.UserEntity;
import com.demo.concept.web.resources.UserResource;
import org.dozer.Mapper;
import org.dozer.Mapping;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Created by admin on 8/15/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserResourceAssemblerTest
{
	@Mapping
	private Mapper mapper;

	@Spy
	@InjectMocks
	private final UserResourceAssembler assembler = new UserResourceAssembler();

	private UserEntity userEntity;
	private UserResource userResource;

	@Before
	public void setup()
	{
		this.userEntity = new UserEntity();
		this.userEntity.setUserId(1);
		this.userEntity.setName("Peter");
		this.userEntity.setLastName("Morfi");
		this.userEntity.setEmail("peter@servermail.com");
		this.userEntity.setPhone("223456677");

	}

	@Test
	public void toResourceTest()
	{
		/*this.userResource = this.assembler.toResource(this.userEntity);

		assertThat(this.userResource.getUserId(), equalTo(1));
		assertThat(this.userResource.getUserId(), equalTo(this.userEntity.getUserId()));*/
	}
}
