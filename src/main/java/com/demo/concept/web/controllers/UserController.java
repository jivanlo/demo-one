package com.demo.concept.web.controllers;

import com.demo.concept.constants.Constants;
import com.demo.concept.facade.UserFacade;
import com.demo.concept.persistence.entity.UserEntity;
import com.demo.concept.web.assembler.UserResourceAssembler;
import com.demo.concept.web.resources.UserResource;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * Created by admin on 8/13/16.
 */
//@Slf4j
@Controller
@ExposesResourceFor(UserEntity.class)
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = Constants.USER_RESOURCE_LINK, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController
{
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserController.class);

	@Autowired
	@NonNull
	private UserResourceAssembler userAssembler;

	@Autowired
	@NonNull
	private UserFacade userFacade;

	@RequestMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE, method = GET)
	public HttpEntity<UserResource> findById(@PathVariable final Integer userId)
	{
		log.info("Searching User with Id '" + userId + "'");
		final UserEntity userEntity = this.userFacade.findByUserId(userId);
		if (userEntity == null)
		{
			log.warn("the User with id {} was not found", id);
			return new ResponseEntity<>(new UserResource(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(this.userAssembler.toResource(userEntity), HttpStatus.OK);
	}

	@RequestMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE, method = GET)
	public HttpEntity<List<UserResource>> findAllUsers()
	{
		log.info("Searching All Users '");
		final Iterable<UserEntity> listUserEntity = this.userFacade.findAllUsers();
		if (listUserEntity == null)
		{
			log.warn("the User with id {} was not found", id);
			return new ResponseEntity<>(new ArrayList<UserResource>(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(this.userAssembler.toResourceList(listUserEntity), HttpStatus.OK);
	}

	@RequestMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, method = POST)
	public HttpEntity<String> saveUser(@Valid @RequestBody final UserResource userResource)
	{
		log.info("Saving User data for '" + userResource.getUserId() + "' and name " +
				userResource.getName());

		final HttpHeaders httpHeaders = new HttpHeaders();
		final UserEntity userEntity = this.userFacade.saveUser(this.userAssembler.toEntity(userResource));

		if (userEntity.getUserId() != null)
		{
			return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<>(httpHeaders, HttpStatus.METHOD_FAILURE);
		}
	}

	@RequestMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, method = POST)
	public HttpEntity<String> updateUser(@Valid @RequestBody final UserResource userResource)
	{
		log.info("Updating User data for '" + userResource.getUserId() + "' and name " +
				userResource.getName());

		final HttpHeaders httpHeaders = new HttpHeaders();
		final UserEntity userEntity = this.userFacade.updateUser(this.userAssembler.toEntity(userResource));

		if (userEntity.getUserId() != null)
		{
			return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/delete/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, method = GET)
	public HttpEntity<String> delete(@PathVariable final Integer userId)
	{
		log.info("Deleting User data for '" + userId);

		final HttpHeaders httpHeaders = new HttpHeaders();
		this.userFacade.deleteUser(userId);
		final UserEntity userEntity = this.userFacade.findByUserId(userId);
		if (userEntity == null)
		{
			return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(httpHeaders, HttpStatus.NOT_FOUND);
		}
	}

}
