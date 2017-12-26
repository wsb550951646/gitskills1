package com.ssm.crud.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.htmlunit.webdriver.MockMvcHtmlUnitDriverBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.ssm.crud.utils.WordSpilt;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml",
		"file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class TestMvc {
	
	@Autowired
	WebApplicationContext context;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		mockMvc  = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void test() throws Exception {
		//ģ�������õ�����ֵ
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/msgs").param("pn", "1"))
				.andReturn();
		
		//����ɹ���
		MockHttpServletRequest request = result.getRequest();
		PageInfo pi = (PageInfo)request.getAttribute("pageInfo");
		
		
		System.out.println("��ǰҳ��"+pi.getPageNum());
		System.out.println("��ҳ��"+pi.getPages());
		System.out.println("ҳ���С"+pi.getPageSize());
		System.out.println("�ܼ�¼��"+pi.getTotal());
		System.out.println("��ҳ����Ҫ������ʾ��ҳ��");
		int[] nums = pi.getNavigatepageNums();
		for(int i : nums){
			System.out.println(""+i);
		}
		
	}
	
	@Test
	public void testMsgList() throws Exception {
		//ģ�������õ�����ֵ
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/msglist").param("pageNo", "1"))
				.andReturn();
		
		//����ɹ���
		MockHttpServletRequest request = result.getRequest();
		System.out.println(request.toString());
		
		
	}
	
	@Test
	public void testMsgList1() {
		
		String str  ="1";
		if(str.toString()=="1".toString())
		{
			System.out.println("yse");
		}
		int x = Integer.parseInt(str);
		x = x+1;
		str = String.valueOf(x);
		System.out.println(str);
		
		if(str.equals("2"))
		{
			System.out.println("yse2");
		}
		/*
		while(str!="2")
		{
			System.out.println(123);
			
		}
		*/
		
	}
	
	@Test
	public void testWordSpilt(){
		
			Map<String,Integer> map = new HashMap<String,Integer>();
			// example:���������۹󲻹�ƹ������������
			Scanner input = new Scanner(System.in);
			String str = input.nextLine();
			WordSpilt wordSpilt = null;
			try {
				wordSpilt = new WordSpilt();
			} catch (IOException e) {
				e.printStackTrace();
			}
			List<String> list = wordSpilt.resultWord(str);
			
			/*
			String jsonPiList = JSON.toJSONString(list,
					SerializerFeature.DisableCircularReferenceDetect);
			System.out.println(jsonPiList);
			for (String string : list) {
				
				System.out.print(string + "/");
			}
			 */
			
			for (String temp :list)
			{
				if(temp.matches("[a-zA-Z]+"))
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

	}
	
	//List���÷���Map���÷�
	
	@Test
	public void testListCount(){
		List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("b");
        list.add("c");
        list.add("a");
        list.add("a");
        list.add("a");
        
        System.out.println("ʹ��Map��������ֵĴ���");
        
        Map<String,Integer> map = new HashMap<String,Integer>();
        	
        //get���� ��ȡ��map�ļ�ֵ
        for(String temp:list)
        {
        	Integer count = (Integer) map.get(temp);
        	map.put(temp, (count==null)? 1: count+1);
        	
        }
        System.out.println(map);
        
        for(Map.Entry<String,Integer> entry: map.entrySet())
        {
        	System.out.println("Key-value : " + entry.getKey() + "- "
                    + entry.getValue());
        	
        }
        
        //�����ֱ����ķ�ʽ
        
        //����һ��Set ����map.entrySet()�������ص�Set����
        Set<Map.Entry<String,Integer>> es = map.entrySet();
        //ʹ��Interator������ Ȼ����� es.interator()����
        Iterator<Map.Entry<String,Integer>> it = es.iterator();
        
        System.out.println("--------�ڶ���");
        while(it.hasNext())
        {
        	Map.Entry<String,Integer> en = it.next();
        	System.out.println("Key-value : " + en.getKey() + "- "
                    + en.getValue());
        }
        
        
        String jsonPiList = JSON.toJSONString(map,
				SerializerFeature.DisableCircularReferenceDetect);
		
        System.out.println(jsonPiList);

		
		
	}
	
	@Test
	public void Stringleng(){
		
		String a ="a";
		
		if(a.length()==1)
		{
			System.out.println("YES");
		}
	}
		
}
		

