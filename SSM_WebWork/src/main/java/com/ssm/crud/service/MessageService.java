package com.ssm.crud.service;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.ssm.crud.bean.Message;
import com.ssm.crud.dao.CompanyDao;
import com.ssm.crud.dao.MessageDao;
import com.ssm.crud.dao.PositionDao;

@Service
@Transactional (propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
public class MessageService {
	@Autowired
	MessageDao messageDao;
	@Autowired
	PositionDao positionDao;
	@Autowired
	CompanyDao companyDao;

	public List<Message> getAll() {
		
		return 	messageDao.getAll();
	
	}
	public Message getMessageById(Integer id){
		return messageDao.getMessageById(id);
	}
	
	public int deleteById(Integer id){
	
		return messageDao.deleteById(id);
	}
	
	public int updateByMessage(Message msg){
		return messageDao.updateByMessage(msg);
	}
	
	public boolean insertByMessage(Message msg){
		
		
		try {
			positionDao.insertPositionGetId(msg.getPosition());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("插入position信息异常");
			//手动捕捉时会进行数据回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		
		try {
		
			messageDao.insertByMessage(msg);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("插入message信息异常");
			//手动捕捉时会进行数据回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		
		return true;
		
	}
	
	public List<Message> getMessageByMessage(Message msg){
		return messageDao.getMessageByMessage(msg);
	}
	
	public void deleteAll(){
		try {
			messageDao.deleteAll();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("删除message信息异常");
			//手动捕捉时会进行数据回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
		}
		
		try {
			companyDao.deleteAll();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("删除company信息异常");
			//手动捕捉时会进行数据回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
		}
		
		try {
			positionDao.deleteAll();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("删除position信息异常");
			//手动捕捉时会进行数据回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			
		}
		
		
	
	}
	
	public boolean InsertMessageFromJsoup(Message msg){
		

		try {
			positionDao.insertPositionGetId(msg.getPosition());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("插入position信息异常");
			//手动捕捉时会进行数据回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		
		try {
			
			companyDao.insertByCompany(msg.getCompany());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("插入company信息异常");
			//手动捕捉时会进行数据回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		
		
		try {
		
			messageDao.insertByMessage(msg);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("插入message信息异常");
			//手动捕捉时会进行数据回滚
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return false;
		}
		
		
		return true;
	}
	
}
		
		
	
	


