package com.ssm.crud.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.crud.bean.Company;
import com.ssm.crud.bean.Message;
import com.ssm.crud.bean.Position;
import com.ssm.crud.bean.ReportDateClothes;
import com.ssm.crud.service.CompanyService;
import com.ssm.crud.service.MessageService;
import com.ssm.crud.service.PositionService;
import com.ssm.crud.utils.JsoupUtils;
import com.ssm.crud.utils.WordSpilt;

@Controller
public class MessageContorller {
	
	@Autowired
	MessageService messageService;
	@Autowired
	CompanyService companyService;
	@Autowired
	PositionService positionService;
	
	@RequestMapping("/msgs")
	public String getMsgs(@RequestParam(value="pn",defaultValue="1")Integer pageNo,
			Map<String,Object> map){
		
		PageHelper.startPage(pageNo,5);
		List<Message> list = messageService.getAll();
		PageInfo page = new PageInfo(list);
		map.put("pageInfo", page);
		return "msglist";
	}
	
	//信息列表
	@ResponseBody
	@RequestMapping(value="/messagelist")
	public String getMsgsWithJason(@RequestParam(value="pageNo",defaultValue="1")
	Integer pageNo,@RequestParam(value="pageSize",defaultValue="5")Integer pageSize)
	{
		
		
		PageHelper.startPage(pageNo, pageSize);
		List<Message> list = messageService.getAll();
		PageInfo page = new PageInfo(list);
		//将返回的list数据编程jason字符
		String jsonPiList = JSON.toJSONString(list,
				SerializerFeature.DisableCircularReferenceDetect);
		
		System.out.println("{\"total\":" + page.getTotal() + ",\"rows\":" + jsonPiList
				+ "}");
		return "{\"total\":" + page.getTotal() + ",\"rows\":" + jsonPiList
				+ "}";
	}
	//删除求职信息
	@ResponseBody
	@RequestMapping(value="deleteMsg",method=RequestMethod.POST)
	public Map<String,Object> deleteMsgById(@RequestParam(value="id")Integer id){
		
		Map<String, Object> result = new HashMap<String, Object>();
		boolean flag = (messageService.deleteById(id)>0);
		result.put("success", flag);
		if(flag)
		result.put("message", "删除成功");
		
		else
		result.put("message", "删除失败");
		
		return result;
	}
	
	//更新求职信息
	@ResponseBody
	@RequestMapping(value="updateMsg", method=RequestMethod.POST)
	public String updateMsgByMsg(@RequestBody Message msg)
	{
		Integer cs=0;
		Integer ps=0;
		Integer ms=0;
		//company
		cs=companyService.updateByCompany(msg.getCompany());
		ps=positionService.updateByPosition(msg.getPosition());
		ms=messageService.updateByMessage(msg);
		if(cs>0&&ps>0&&ms>0)
		return "{ \"success\":\"ture\",\"message\":\"修改成功!\"}";
		
		else 
		return "{ \"success\":\"false\",\"message\":\"修改失败!\"}";
		
	}
	
	//获取所有公司
	@ResponseBody
	@RequestMapping(value="getAllWithCompany",method=RequestMethod.POST)
	public String getAllWithCompany(){
		
		List<Company> list = companyService.getAll();
		String jsonPiList = JSON.toJSONString(list,
				SerializerFeature.DisableCircularReferenceDetect);
		
		System.out.println("{\"rows\":" + jsonPiList+ "}");
		return "{\"rows\":" + jsonPiList+ "}";
	}
	
	//搜索求职信息
	@ResponseBody
	@RequestMapping(value="searchMessageListByMsg",method=RequestMethod.POST)
	public String getMsgsByMsg(@RequestParam(value="pageNo",defaultValue="1")
	Integer pageNo,@RequestParam(value="pageSize",defaultValue="5")Integer pageSize,
	Message msg)
	{
		
		List<Message> list = messageService.getMessageByMessage(msg);
		PageHelper.startPage(pageNo,pageSize);
		String jsonPiList = JSON.toJSONString(list,
				SerializerFeature.DisableCircularReferenceDetect);
		
		PageInfo page = new PageInfo(list);
		
		System.out.println("{\"total\":" + page.getTotal() + ",\"rows\":" + jsonPiList
				+ "}");
		return "{\"total\":" + page.getTotal() + ",\"rows\":" + jsonPiList
				+ "}";
		
	}
	
	//添加求职信息
	@ResponseBody
	@RequestMapping(value="addMessageByMsg",method=RequestMethod.POST)
	public String insertMessageByMsg(@RequestBody Message msg){
		
		Company company = companyService.getCompanyOneByName(msg.getCpName());
		msg.setCpAddress(company.getAddress());
		msg.setCompany(company);
		
		if(messageService.insertByMessage(msg))
		return  "{ \"success\":\"ture\",\"message\":\"添加成功!\"}";
	
		else
		return "{ \"success\":\"ture\",\"message\":\"添加失败!\"}";
		
	}
	
	//关键词信息爬取
	@ResponseBody
	@RequestMapping(value="searchKeyWord")
	public String insertMessageByJsoup(@RequestParam(value="keyWord",defaultValue="java")
	String keyWord){
		System.out.println(keyWord);
		//清空数据库
		messageService.deleteAll();
		JsoupUtils util = new JsoupUtils();
		String url = null;
		Document doc = null;
		String nextpage = null;
		int id  = 1;
		url = util.updateKeyWord(keyWord);
		Message message = new Message();
		Position position = new Position();
		Company company = new Company();
		message.setPosition(position);
		message.setCompany(company);
		
		String page = "0";
		while(!page.equals("1"))
		{
			
			int temp = Integer.parseInt(page)+1;
			 nextpage = String.valueOf(temp);
			url = url.replaceAll(page,nextpage);
			System.out.println("-------------------"+url);
			
		try {
			//获取doc和解析HTML页面。
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(30000).post();
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		//获取class = job-info的标签
		
		//Elements links1 = doc.select("div.job-info"); 
		Elements links1 = doc.getElementsByClass("job-info"); 
		for (Element e : links1) {
			//获取<a href />标签的内容
			Elements elements = e.select("a[href]");  
			for (Element element : elements) {
				//将href属性赋值给detailUrl
				String detailUrl = element.attr("href");
				//筛选detailUrl选取含有job字段的信息  结尾为shtml
				if(detailUrl.contains("job")&&detailUrl.endsWith("shtml"))
				{
				//获取text文本内容 --positionName
				System.out.println(element.text());
				message.getPosition().setPsName(element.text());
				message.setPsName(element.text());
				//获取positionUrln
				System.out.println(element.attr("href")); 
				message.getPosition().setPsUrl(element.attr("href"));
				//将detailUrl传入getDetailBydetailUrl中国
				message = util.getDetailBydetailUrl(detailUrl,message);
				message = util.InsertId(id, message);
				//插入数据
				messageService.InsertMessageFromJsoup(message);
				id++;
				System.out.println(id);
				}
			}
		}
	
		page = nextpage;
		System.out.println(page);
	}
	return  "{ \"success\":\"ture\",\"message\":\"爬取成功!\"}";
		
		
	
}
	//报表测试
	@ResponseBody
	@RequestMapping(value="getTestReport",method=RequestMethod.GET)
	public String TestReport(){
		ReportDateClothes bean = new ReportDateClothes();
		//List<String> categories = Arrays.asList("测试1","测试2","测试3","测试4","测试5");
		List<String> categories = new ArrayList<String>();
		for(int i=0;i<150;i++)
		{
			String str="测试";
			str = str+i;
			System.out.println(str);
			categories.add(str);
		}
		
		List<Integer> data =new ArrayList<Integer>();
		for(int i=0;i<150;i++)
		{
			data.add(i);
		}
		
		
		bean.setCategories(categories);
		bean.setData(data);
		
		String jsonPiList = JSON.toJSONString(bean,
				SerializerFeature.DisableCircularReferenceDetect);
		
		
		return jsonPiList;
	}
	
	//单个职位需求图形展示
	@ResponseBody
	@RequestMapping(value="getPictureData",method=RequestMethod.POST)
	public String getPictureData(@RequestParam(value="positionInfo")String positionInfo){
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		System.out.println(positionInfo);
		// example:过来看房价贵不贵？乒乓球拍卖完了
		
		String str = new String();
		str = positionInfo;
		WordSpilt wordSpilt = null;
		try {
			wordSpilt = new WordSpilt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<String> list = wordSpilt.resultWord(str);
		
		for (String temp :list)
		{
			/* 匹配机制
			 * 1.temp.matches("[a-zA-Z]+")
			 * 
			 */
			if(temp.length()>1)
			{
			Integer count = map.get(temp);
			map.put(temp, (count==null)? 1: count+1);
			}
			
		}
		
		for(Map.Entry<String, Integer> entry : map.entrySet())
		{
			System.out.println("Key-value : " + entry.getKey() + "- "
                    + entry.getValue());
		}
		
		
		String jsonPiList = JSON.toJSONString(map,
				SerializerFeature.DisableCircularReferenceDetect);
		
        System.out.println(jsonPiList);
		
		
		return jsonPiList;
	}
	
	//多个职位需求图形展示
	@ResponseBody
	@RequestMapping(value="getAllPictureData",method=RequestMethod.POST)
	public String getAllPictureData(){
		List<String> list = new ArrayList<String>();
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		//数据库中取出所有的数据
		list = positionService.getAllPositionRequried();
		String positionInfo = new String();
		for(String str : list)
		{
			positionInfo += str;
		}
		
		WordSpilt wordSpilt = null;
		try {
			wordSpilt = new WordSpilt();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<String> list2 = wordSpilt.resultWord(positionInfo);
		for (String temp :list2)
		{
			/* 匹配机制
			 * 1.temp.matches("[a-zA-Z]+")
			 * 2.temp.length()>1 消除标点
			 */
			if(temp.length()>1)
			{
			Integer count = map.get(temp);
			map.put(temp, (count==null)? 1: count+1);
			}
			
		}
		System.out.println(map);
		
		
		
		//对map对象里关键词进行筛选
		Set<Map.Entry<String,Integer>> es = map.entrySet();
	   //使用Interator遍历器 然后调用 es.interator()方法
		Iterator<Map.Entry<String,Integer>> it = es.iterator();
		while(it.hasNext())
		{
			Map.Entry<String,Integer> en = it.next();
			
			//清除小于6关键词
			
			if(en.getValue()<8)
			{
				it.remove();
			}
			
			
		}
			
		String jsonPiList = JSON.toJSONString(map,
				SerializerFeature.DisableCircularReferenceDetect);
		
        System.out.println(jsonPiList);
		
		
		return jsonPiList;
	}
	
	
	//多个技术需求图形展示
		@ResponseBody
		@RequestMapping(value="getAllPictureData2",method=RequestMethod.POST)
		public String getAllPictureData2(){
			List<String> list = new ArrayList<String>();
			Map<String,Integer> map = new HashMap<String,Integer>();
			
			//数据库中取出所有的数据
			list = positionService.getAllPositionRequried();
			String positionInfo = new String();
			for(String str : list)
			{
				positionInfo += str;
			}
			
			WordSpilt wordSpilt = null;
			try {
				wordSpilt = new WordSpilt();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			List<String> list2 = wordSpilt.resultWord(positionInfo);
			for (String temp :list2)
			{
				/* 匹配机制
				 * 1.temp.matches("[a-zA-Z]+")
				 * 
				 */
				if(temp.length()>1&&temp.matches("[a-zA-Z]+"))
				{
				Integer count = map.get(temp);
				map.put(temp, (count==null)? 1: count+1);
				}
				
			}
			System.out.println(map);
			
			
			
			//对map对象里关键词进行筛选
			Set<Map.Entry<String,Integer>> es = map.entrySet();
		   //使用Interator遍历器 然后调用 es.interator()方法
			Iterator<Map.Entry<String,Integer>> it = es.iterator();
			while(it.hasNext())
			{
				Map.Entry<String,Integer> en = it.next();
				
				//清除小于2关键词
				
				if(en.getValue()<2)
				{
					it.remove();
				}
				
				
			}
				
			String jsonPiList = JSON.toJSONString(map,
					SerializerFeature.DisableCircularReferenceDetect);
			
	        System.out.println(jsonPiList);
			
			
			return jsonPiList;
		}
	
		
	
//	@RequestMapping("/index")
//	public String index(){
//		return "index";
//	}
	

}
