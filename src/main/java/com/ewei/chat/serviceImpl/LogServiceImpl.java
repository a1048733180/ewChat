package com.ewei.chat.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ewei.chat.dao.LogDao;
import com.ewei.chat.pojo.Log;
import com.ewei.chat.service.LogService;


@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private LogDao logDao ;

	@Override
	public List<Log> selectAll(int page, int pageSize) {
		return null;
	}

	@Override
	public List<Log> selectLogByUserid(String userid, int page, int pageSize) {
		int start = 0;
		if(page!=1) {
			start = (page-1)*pageSize;
		}
		return logDao.selectLogByUserid(userid, start, pageSize);
	}

	@Override
	public int selectCount(int pageSize) {
		return 0;
	}

	@Override
	public int selectCountByUserid(String userid, int pageSize) {
		int pageCount = logDao.selectCountByUserid(userid);
		return pageCount%pageSize==0?pageCount/pageSize:pageCount/pageSize+1;
	}

	@Override
	public boolean insert(Log log) {
		return false;
	}

	@Override
	public boolean delete(String id) {
		return false;
	}

	@Override
	public boolean deleteByUserid(String userid) {
		return false;
	}

	@Override
	public boolean deleteAll() {
		return false;
	}
}
