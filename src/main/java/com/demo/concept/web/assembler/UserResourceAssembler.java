package com.demo.concept.web.assembler;

import com.demo.concept.persistence.entity.UserEntity;
import com.demo.concept.web.controllers.UserController;
import com.demo.concept.web.resources.UserResource;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


/**
 * Created by admin on 8/13/16.
 */
@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<UserEntity, UserResource>
{

	private final Mapper mapper = new DozerBeanMapper();

	public UserResourceAssembler()
	{
		super(UserController.class, UserResource.class);
	}

	@Override
	public UserResource toResource(final UserEntity userEntity)
	{
		final UserResource userResource = this.createResourceWithId(userEntity.getUserId(), userEntity);
		this.mapper.map(userEntity, userResource);
		return userResource;
	}

	public UserEntity toEntity(final UserResource userResource)
	{
		final UserEntity userEntity = new UserEntity();
		this.mapper.map(userResource, userEntity);
		return userEntity;
	}

	public List<UserResource> toResourceList(final Iterable<UserEntity> users)
	{
		UserResource userResource = null;
		final List<UserResource> listResources = new ArrayList<>();
		for (final UserEntity user : users)
		{
			userResource = this.mapper.map(user, UserResource.class);
			userResource.add(linkTo(methodOn(UserController.class).findById(user.getUserId())).withSelfRel());
			listResources.add(userResource);
		}
		return listResources;
	}

	public List<UserEntity> toEntityList(final List<UserResource> users)
	{
		UserEntity userEntity = null;
		final List<UserEntity> listEntityes = new ArrayList<>();
		for (final UserResource user : users)
		{
			userEntity = this.mapper.map(user, UserEntity.class);
			listEntityes.add(userEntity);
		}
		return listEntityes;
	}
}
