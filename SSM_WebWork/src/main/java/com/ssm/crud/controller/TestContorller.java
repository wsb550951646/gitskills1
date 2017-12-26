package com.ssm.crud.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestContorller {
	
	/*
	 * 
	 * 
	 * 
	 * 1.@requestParm �������� contentpye = "application/x-www-form-urlencoded"
	 * ������
	 * 
	 * 2.��ǰ�˴��ݵ�������ʱ Ҫ��name��ʶ
	 * 
	 * �� name = "date"
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/testFomat",method = RequestMethod.POST)
	public Map<String,Object> testFomat(@RequestParam @DateTimeFormat(iso = ISO.DATE)Date date){
		
		System.out.println(date);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("msg", "�ɹ�");
		
		return map;
	}

}
