package myboot.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import myboot.entity.UserEntity;

@Repository
public interface UserDao extends CrudRepository<UserEntity, String> {
	UserEntity findUserByName(String name);
}
