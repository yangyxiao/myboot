package com.myboot.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myboot.dao.UserDao;
import com.myboot.entity.UserEntity;

@Service
@Transactional
public class UserService {
	private static final Logger LOGGER = Logger.getLogger(UserService.class);
	@Autowired
	private UserDao userDao;
	public Iterable<UserEntity> getUserList(){
		LOGGER.warn("查找用户列表");
		return userDao.findAll();
	}
	public UserEntity getUserByName(String name){
		LOGGER.warn("使用用户名查找用户");
		return userDao.findUserByName(name);
	}
}
