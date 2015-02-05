package com.carfinance.module.log.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
 * @author jiangyin
 */
@Service
public class LogService {
	
	static Logger logger = LoggerFactory.getLogger(LogService.class);
	
//	@Autowired
//	private LogDao logDao;
//	
//	public int insertLog(long userid , String opUrl) {
//		return logDao.insertLog(userid , opUrl , 0);
//	}
}
