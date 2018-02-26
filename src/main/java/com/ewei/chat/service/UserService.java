package com.ewei.chat.service;

import com.ewei.chat.pojo.User;

/**
 * UserService接口
 * 
 */
public interface UserService {
	User selectUserById(String userid) ;
	boolean insert(User user);
	boolean update(User user);
}
