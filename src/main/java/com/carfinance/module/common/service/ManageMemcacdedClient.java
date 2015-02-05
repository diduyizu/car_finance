package com.carfinance.module.common.service;

import java.net.SocketAddress;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Future;
import net.spy.memcached.MemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ManageMemcacdedClient {
	
	final Logger logger = LoggerFactory.getLogger(ManageMemcacdedClient.class);
	private MemcachedClient	memcachedClient;
	
	public MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}
	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
	@Autowired
	public ManageMemcacdedClient(@Qualifier("memcachedClient")MemcachedClient memcachedClient){
		try{
			logger.info("初始化[car_finance_MemcacdedClient]对象......");
			this.memcachedClient=memcachedClient;
			init();
		}catch(Exception e){
			e.printStackTrace();
			logger.info("初始化缓存对象[car_finance_MemcacdedClient]失败!");
		}
	}
	
	public void memcachedStat(){
		try{
			Map<SocketAddress,Map<String,String>> map=this.memcachedClient.getStats();
			for(SocketAddress sa:map.keySet()){
				logger.info("*******************************");
				logger.info("****缓存实例："+sa.toString());
				Map<String,String> _map=map.get(sa);
				for(String str:_map.keySet()){
					logger.info("********"+str+"="+_map.get(str));
				}
				logger.info("*******************************");
			}
		}catch(Exception e){
			e.printStackTrace();
			return;
		}
	}
	
	public void init(){
		logger.info("开始刷新缓存数据......");
		try{
			this.memcachedClient.flush();
		}catch(Exception e){
			logger.info("缓存数据刷新异常结束！");
			e.printStackTrace();
			return;
		}
		logger.info("缓存数据刷新正常结束！");
	}
	
	public Object get(String key){
		logger.debug("进入GET方法");
		try{
			return this.memcachedClient.get(key);
		}catch(Exception e){
			logger.info("GET方法异常,返回NULL");
			e.printStackTrace();
			return null;
		}
	}
	
	public Future<Boolean> add(String key, int exp, Object o){
		logger.debug("进入ADD方法");
		try{
			return this.memcachedClient.add(key,exp,o);
		}catch(Exception e){
			logger.info("ADD方法异常,返回NULL");
			e.printStackTrace();
			return null;
		}
	}
	
	public Future<Boolean> replace(String key, int exp, Object o){
		logger.debug("进入REPLACE方法");
		try{
			return this.memcachedClient.replace(key,exp,o);
		}catch(Exception e){
			logger.info("REPLACE方法异常,返回NULL");
			e.printStackTrace();
			return null;
		}
	}
	
	public Future<Boolean> set(String key, int exp, Object o){
		logger.debug("进入SET方法");
		try{
			return this.memcachedClient.set(key,exp,o);
		}catch(Exception e){
			logger.info("SET方法异常,返回NULL");
			e.printStackTrace();
			return null;
		}
	}
	
	public Future<Boolean> delete(String key){
		logger.debug("进入DELETE方法");
		try{
			return this.memcachedClient.delete(key);
		}catch(Exception e){
			logger.info("DELETE方法异常,返回NULL");
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,Object> getBulk(Collection<String> keys){
		logger.debug("进入GETBULK方法");
		try{
			return this.memcachedClient.getBulk(keys);
		}catch(Exception e){
			logger.info("GETBULK方法异常,返回NULL");
			e.printStackTrace();
			return null;
		}
	}
	
	public Map<String,Object> getBulk(String... keys){
		logger.debug("进入GETBULK方法");
		try{
			return this.memcachedClient.getBulk(keys);
		}catch(Exception e){
			logger.info("GETBULK方法异常,返回NULL");
			e.printStackTrace();
			return null;
		}
	}
}
