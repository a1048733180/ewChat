package com.ewei.chat.serviceImpl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.ewei.chat.pojo.User;
import com.ewei.chat.service.UserService;
import com.ewei.chat.dao.UserDao;

/**
 * UserService接口实现类
 *
 */

@Service(value="userService")
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserDao userDao;

	@Override
	public User selectUserById(String userid) {
		
		return userDao.selectUserById(userid);
	}

	@Override
	public boolean insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public boolean update(User user) {
		return userDao.update(user);
	}
	
}
