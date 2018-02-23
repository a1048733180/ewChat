package com.ewei.chat.dao;

import com.ewei.chat.pojo.User;
import org.springframework.stereotype.Service;

/**
 *	mapper接口
 */
@Service(value="userDao")
public interface UserDao {
	User selectUserById(String userid); //根据用户id查询用户
	
	boolean insert(User user); //添加用户

	boolean update(User user);
	
}
