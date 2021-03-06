package com.myboot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.myboot.entity.UserEntity;

@Repository
public interface UserDao extends CrudRepository<UserEntity, String> {
	UserEntity findUserByName(String name);
}
