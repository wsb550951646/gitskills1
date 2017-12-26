package com.ssm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.crud.bean.Position;
import com.ssm.crud.dao.PositionDao;

@Service
public class PositionService {

	@Autowired
	PositionDao positionDao;
	
	public Integer updateByPosition(Position position)
	{
		return positionDao.updateByPosition(position);
		
	}
	
	public Integer deleteById(Integer id)
	{
		return positionDao.deleteById(id);
		
	}
	
	public Integer InsertByPs(Position position){
		return positionDao.insertByPosition(position);
	}
	
	public List<Position> getAll()
	{
		return positionDao.getAll();
	}
	
	public Position getPositionById(Integer id){
		
		return positionDao.getPositionById(id);
	}
	
	public Integer insertPositionGetId(Position position){
		
		return positionDao.insertPositionGetId(position);
	}
	
	public List<String> getAllPositionRequried(){
		
		return positionDao.getAllPositionRequried();
	}
	
	
	
}
