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
	
	//��Ϣ�б�
	@ResponseBody
	@RequestMapping(value="/messagelist")
	public String getMsgsWithJason(@RequestParam(value="pageNo",defaultValue="1")
	Integer pageNo,@RequestParam(value="pageSize",defaultValue="5")Integer pageSize)
	{
		
		
		PageHelper.startPage(pageNo, pageSize);
		List<Message> list = messageService.getAll();
		PageInfo page = new PageInfo(list);
		//�����ص�list���ݱ��jason�ַ�
		String jsonPiList = JSON.toJSONString(list,
				SerializerFeature.DisableCircularReferenceDetect);
		
		System.out.println("{\"total\":" + page.getTotal() + ",\"rows\":" + jsonPiList
				+ "}");
		return "{\"total\":" + page.getTotal() + ",\"rows\":" + jsonPiList
				+ "}";
	}
	//ɾ����ְ��Ϣ
	@ResponseBody
	@RequestMapping(value="deleteMsg",method=RequestMethod.POST)
	public Map<String,Object> deleteMsgById(@RequestParam(value="id")Integer id){
		
		Map<String, Object> result = new HashMap<String, Object>();
		boolean flag = (messageService.deleteById(id)>0);
		result.put("success", flag);
		if(flag)
		result.put("message", "ɾ���ɹ�");
		
		else
		result.put("message", "ɾ��ʧ��");
		
		return result;
	}
	
	//������ְ��Ϣ
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
		return "{ \"success\":\"ture\",\"message\":\"�޸ĳɹ�!\"}";
		
		else 
		return "{ \"success\":\"false\",\"message\":\"�޸�ʧ��!\"}";
		
	}
	
	//��ȡ���й�˾
	@ResponseBody
	@RequestMapping(value="getAllWithCompany",method=RequestMethod.POST)
	public String getAllWithCompany(){
		
		List<Company> list = companyService.getAll();
		String jsonPiList = JSON.toJSONString(list,
				SerializerFeature.DisableCircularReferenceDetect);
		
		System.out.println("{\"rows\":" + jsonPiList+ "}");
		return "{\"rows\":" + jsonPiList+ "}";
	}
	
	//������ְ��Ϣ
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
	
	//�����ְ��Ϣ
	@ResponseBody
	@RequestMapping(value="addMessageByMsg",method=RequestMethod.POST)
	public String insertMessageByMsg(@RequestBody Message msg){
		
		Company company = companyService.getCompanyOneByName(msg.getCpName());
		msg.setCpAddress(company.getAddress());
		msg.setCompany(company);
		
		if(messageService.insertByMessage(msg))
		return  "{ \"success\":\"ture\",\"message\":\"��ӳɹ�!\"}";
	
		else
		return "{ \"success\":\"ture\",\"message\":\"���ʧ��!\"}";
		
	}
	
	//�ؼ�����Ϣ��ȡ
	@ResponseBody
	@RequestMapping(value="searchKeyWord")
	public String insertMessageByJsoup(@RequestParam(value="keyWord",defaultValue="java")
	String keyWord){
		System.out.println(keyWord);
		//������ݿ�
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
			//��ȡdoc�ͽ���HTMLҳ�档
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(30000).post();
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		//��ȡclass = job-info�ı�ǩ
		
		//Elements links1 = doc.select("div.job-info"); 
		Elements links1 = doc.getElementsByClass("job-info"); 
		for (Element e : links1) {
			//��ȡ<a href />��ǩ������
			Elements elements = e.select("a[href]");  
			for (Element element : elements) {
				//��href���Ը�ֵ��detailUrl
				String detailUrl = element.attr("href");
				//ɸѡdetailUrlѡȡ����job�ֶε���Ϣ  ��βΪshtml
				if(detailUrl.contains("job")&&detailUrl.endsWith("shtml"))
				{
				//��ȡtext�ı����� --positionName
				System.out.println(element.text());
				message.getPosition().setPsName(element.text());
				message.setPsName(element.text());
				//��ȡpositionUrln
				System.out.println(element.attr("href")); 
				message.getPosition().setPsUrl(element.attr("href"));
				//��detailUrl����getDetailBydetailUrl�й�
				message = util.getDetailBydetailUrl(detailUrl,message);
				message = util.InsertId(id, message);
				//��������
				messageService.InsertMessageFromJsoup(message);
				id++;
				System.out.println(id);
				}
			}
		}
	
		page = nextpage;
		System.out.println(page);
	}
	return  "{ \"success\":\"ture\",\"message\":\"��ȡ�ɹ�!\"}";
		
		
	
}
	//�������
	@ResponseBody
	@RequestMapping(value="getTestReport",method=RequestMethod.GET)
	public String TestReport(){
		ReportDateClothes bean = new ReportDateClothes();
		//List<String> categories = Arrays.asList("����1","����2","����3","����4","����5");
		List<String> categories = new ArrayList<String>();
		for(int i=0;i<150;i++)
		{
			String str="����";
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
	
	//����ְλ����ͼ��չʾ
	@ResponseBody
	@RequestMapping(value="getPictureData",method=RequestMethod.POST)
	public String getPictureData(@RequestParam(value="positionInfo")String positionInfo){
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		System.out.println(positionInfo);
		// example:���������۹󲻹�ƹ������������
		
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
			/* ƥ�����
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
	
	//���ְλ����ͼ��չʾ
	@ResponseBody
	@RequestMapping(value="getAllPictureData",method=RequestMethod.POST)
	public String getAllPictureData(){
		List<String> list = new ArrayList<String>();
		Map<String,Integer> map = new HashMap<String,Integer>();
		
		//���ݿ���ȡ�����е�����
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
			/* ƥ�����
			 * 1.temp.matches("[a-zA-Z]+")
			 * 2.temp.length()>1 �������
			 */
			if(temp.length()>1)
			{
			Integer count = map.get(temp);
			map.put(temp, (count==null)? 1: count+1);
			}
			
		}
		System.out.println(map);
		
		
		
		//��map������ؼ��ʽ���ɸѡ
		Set<Map.Entry<String,Integer>> es = map.entrySet();
	   //ʹ��Interator������ Ȼ����� es.interator()����
		Iterator<Map.Entry<String,Integer>> it = es.iterator();
		while(it.hasNext())
		{
			Map.Entry<String,Integer> en = it.next();
			
			//���С��6�ؼ���
			
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
	
	
	//�����������ͼ��չʾ
		@ResponseBody
		@RequestMapping(value="getAllPictureData2",method=RequestMethod.POST)
		public String getAllPictureData2(){
			List<String> list = new ArrayList<String>();
			Map<String,Integer> map = new HashMap<String,Integer>();
			
			//���ݿ���ȡ�����е�����
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
				/* ƥ�����
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
			
			
			
			//��map������ؼ��ʽ���ɸѡ
			Set<Map.Entry<String,Integer>> es = map.entrySet();
		   //ʹ��Interator������ Ȼ����� es.interator()����
			Iterator<Map.Entry<String,Integer>> it = es.iterator();
			while(it.hasNext())
			{
				Map.Entry<String,Integer> en = it.next();
				
				//���С��2�ؼ���
				
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
