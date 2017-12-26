package com.ssm.crud.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssm.crud.bean.Company;
import com.ssm.crud.bean.Message;
import com.ssm.crud.bean.Position;
import com.ssm.crud.dao.CompanyDao;
import com.ssm.crud.dao.MessageDao;
import com.ssm.crud.dao.PositionDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestSSM {
	
	@Autowired
	CompanyDao companyDao;
	@Autowired
	PositionDao positionDao;
	@Autowired
	MessageDao messageDao;

	@Test
	public void test() { 	
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//		CompanyDao bean = ioc.getBean(CompanyDao.class);
		System.out.println(messageDao.getAll());
	}
	
	@Test
	public void testCRUD() { 	
//		
		
		//Company c  = new Company(3,"深圳", "测试", "无");
//		//companyDao.insertByCompany(c);
//		Company company  = new Company(2,"深圳", "测试", "无");
//		companyDao.updateByCompany(company);
//		
//		companyDao.deleteById(3);
		
		Position p = new Position(3, "无", "无", "无", "无");
		//positionDao.insertByPosition(p); 
		//Message message  = new Message(2, "g", c, p);
		//positionDao.updateByPosition(p);
		//positionDao.deleteById(2);
		//messageDao.insertByMessage(message);
		
		//如果增company.id 为3 则报错
		//messageDao.updateByMessage(message);
		List<Message> list = messageDao.getAll();
		
		System.out.println(list);
		
		
	
	}
		
	@Test
	public void testDeleteWithCase() { 	
	
		
		//Company c  = new Company(3,"深圳", "测试", "无");

		Position p = new Position(null, "无", "无", "无", "无");
		//positionDao.insertByPosition(p); 
		//Message message  = new Message(2, "g", c, p);
		//messageDao.deleteById(2);
		//positionDao.deleteById(2);
		
		positionDao.insertByPosition(p);
	
	}
	
	@Test
	public void testinsertPositionGetId(){
		Position p = new Position(null, "无", "无", "无", "无");
		System.out.println("插入前主键为："+p.getId());  
		//无法获取ID
		//positionDao.insertByPosition(p);
		// getId 可以获取ID
		//int x = positionDao.insertPositionGetId(p);
		System.out.println("插入后主键为："+p.getId());  
	}
	
	@Test
	public void getAllColumn(){
		List<String> list  = new ArrayList<String>();
		
		list = positionDao.getAllPositionRequried();
		
		for(String str:list)
		{
			System.out.println(str);
		}
		
	}

}
