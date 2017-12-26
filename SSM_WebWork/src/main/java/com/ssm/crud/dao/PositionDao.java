package com.ssm.crud.dao;

import java.util.List;

import com.ssm.crud.bean.Position;


public interface PositionDao {

	public List<Position> getAll();
	
	public Position getPositionById(Integer id);

	public Integer insertByPosition(Position position);
	
	public Integer deleteById(Integer id);
	
	public Integer updateByPosition(Position position);

	public Integer insertPositionGetId(Position position);
	
	public Integer deleteAll();
	
	public List<String> getAllPositionRequried();	
}
