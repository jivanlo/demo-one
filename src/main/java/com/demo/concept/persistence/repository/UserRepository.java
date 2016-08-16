package com.demo.concept.persistence.repository;

import com.demo.concept.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by admin on 8/12/16.
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer>
{
}
