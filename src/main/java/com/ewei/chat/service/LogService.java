package com.ewei.chat.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ewei.chat.pojo.Log;

public interface LogService {
	public List<Log> selectAll(int page,int pageSize);
	public List<Log> selectLogByUserid(String userid,int page,int pageSize);
	public int selectCount(int pageSize);
	public int selectCountByUserid(String userid,int pageSize);
	public boolean insert(Log log);
	public boolean delete(String id);
	public boolean deleteByUserid(String userid);
	public boolean deleteAll();

}
