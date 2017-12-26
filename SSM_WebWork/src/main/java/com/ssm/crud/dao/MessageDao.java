package com.ssm.crud.dao;

import java.util.List;

import com.ssm.crud.bean.Message;
import com.ssm.crud.bean.Message;

public interface MessageDao {
	
	public List<Message> getAll();
	
	public Message getMessageById(Integer id);
	
	public Integer insertByMessage(Message message);
	
	public Integer deleteById(Integer id);
	
	public Integer updateByMessage(Message message);
	
	public List<Message> getMessageByMessage(Message msg);

	public Integer deleteAll();
	
	
}
