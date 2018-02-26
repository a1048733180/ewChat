package com.ewei.chat.utils;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.ewei.chat.dao.LogDao;
import com.ewei.chat.pojo.Log;
import com.ewei.chat.service.LogService;

public class LogUtil {
	public static void insert(String userid, String LOG_TYPE_UPDATE,
		String LOG_DETAIL_UPDATE_PROFILEHEAD, LogService logService) {
		System.out.println(logService);
		Log log = new Log();
		log.setUserid(userid);
		log.setDetail(LOG_DETAIL_UPDATE_PROFILEHEAD);
		log.setType(LOG_TYPE_UPDATE);
		log.setIp(null);
		log.setTime(DateUtil.getTime());
		log.setId(UUID.randomUUID().toString().replaceAll("-",""));
		boolean i = logService.insert(log);
		System.out.println(i);
	}
}
