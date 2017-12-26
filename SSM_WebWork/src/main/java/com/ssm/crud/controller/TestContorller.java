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
	 * 1.@requestParm 用来处理 contentpye = "application/x-www-form-urlencoded"
	 * 的请求
	 * 
	 * 2.在前端传递单个参数时 要用name标识
	 * 
	 * 即 name = "date"
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/testFomat",method = RequestMethod.POST)
	public Map<String,Object> testFomat(@RequestParam @DateTimeFormat(iso = ISO.DATE)Date date){
		
		System.out.println(date);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("msg", "成功");
		
		return map;
	}

}
