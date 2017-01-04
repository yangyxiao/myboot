package myboot.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import myboot.dao.UserDao;
import myboot.entity.UserEntity;

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
}
