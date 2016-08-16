package com.demo.concept.web.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dozer.Mapping;
import org.springframework.hateoas.ResourceSupport;


/**
 * Created by admin on 8/13/16.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResource extends ResourceSupport
{

	@Mapping("userId")
	private Integer userId;

	@Mapping("name")
	private String name;

	@Mapping("lastName")
	private String lastName;

	@Mapping("phone")
	private String phone;

	@Mapping("email")
	private String email;

}
