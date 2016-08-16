package com.demo.concept.persistence.entity;

import lombok.Data;
import org.dozer.Mapping;

import javax.persistence.*;


/**
 * Created by admin on 8/12/16.
 */
@Data
@Entity
@Table(name = "user", schema = "demo")
public class UserEntity implements java.io.Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer userId;

	@Column(name = "NAME", nullable = false)
	@Mapping("name")
	private String name;

	@Column(name = "LASTNAME", nullable = false)
	@Mapping("lastName")
	private String lastName;

	@Column(name = "EMAIL")
	@Mapping("email")
	private String email;

	@Column(name = "PHONE", nullable = false)
	@Mapping("phone")
	private String phone;

}
