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
		
		//Company c  = new Company(3,"����", "����", "��");
//		//companyDao.insertByCompany(c);
//		Company company  = new Company(2,"����", "����", "��");
//		companyDao.updateByCompany(company);
//		
//		companyDao.deleteById(3);
		
		Position p = new Position(3, "��", "��", "��", "��");
		//positionDao.insertByPosition(p); 
		//Message message  = new Message(2, "g", c, p);
		//positionDao.updateByPosition(p);
		//positionDao.deleteById(2);
		//messageDao.insertByMessage(message);
		
		//�����company.id Ϊ3 �򱨴�
		//messageDao.updateByMessage(message);
		List<Message> list = messageDao.getAll();
		
		System.out.println(list);
		
		
	
	}
		
	@Test
	public void testDeleteWithCase() { 	
	
		
		//Company c  = new Company(3,"����", "����", "��");

		Position p = new Position(null, "��", "��", "��", "��");
		//positionDao.insertByPosition(p); 
		//Message message  = new Message(2, "g", c, p);
		//messageDao.deleteById(2);
		//positionDao.deleteById(2);
		
		positionDao.insertByPosition(p);
	
	}
	
	@Test
	public void testinsertPositionGetId(){
		Position p = new Position(null, "��", "��", "��", "��");
		System.out.println("����ǰ����Ϊ��"+p.getId());  
		//�޷���ȡID
		//positionDao.insertByPosition(p);
		// getId ���Ի�ȡID
		//int x = positionDao.insertPositionGetId(p);
		System.out.println("���������Ϊ��"+p.getId());  
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
