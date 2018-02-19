package com.ewei.chat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.ewei.chat.pojo.Log;
public interface LogDao {
	public List<Log> selectAll(@Param("offset") int offset,@Param("limit") int limit);
	public List<Log> selectLogByUserid(@Param("userid") String userid,@Param("offset") int offset,@Param("limit") int limit);
	public int selectCount();
	public int selectCountByUserid(@Param("userid") String userid);
	public boolean insert(Log log);
	public boolean delete(String id);
	public boolean deleteByUserid(@Param("userid")String userid);
	public boolean deleteAll();
}
