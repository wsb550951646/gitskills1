package com.ssm.crud.utils;
import com.ssm.crud.bean.Message;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


	    public class JsoupUtils {

		//公司的详细信息爬取
		public  Message getCompanyByDetailUrl(String detailUrl,Message message){
			Document doc = null;
			try {
				//获取doc和解析HTML页面。
				doc = Jsoup.connect(detailUrl).userAgent("Mozilla/5.0").timeout(30000).post();
				 
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Elements links1 = doc.getElementsByClass("title-info"); 
			for (Element e : links1) {
				//获取<a href />标签的内容
				Elements elements = e.select("a[href]");  
				for (Element element : elements) {
					//将href属性赋值给detailUrl
					String CompanyUrl = element.attr("href");
					if(CompanyUrl.contains("company"))
					{
					//获取text文本内容---公司的名字以及url
					String company = element.text();
					String companyUrl = element.attr("href");
					
					System.out.println(company);
					message.getCompany().setCpName(company);
					message.setCpName(company);
					System.out.println(companyUrl);
					message.getCompany().setCpUrl(companyUrl);

					}
				} 
			}
			return message;
			
		}
		
		//薪酬的详细信息爬取
		public  String getMoneyByDetailUrl(String detailUrl){
			Document doc = null;
			String mondey = null;
			try {
				//获取doc和解析HTML页面。
				doc = Jsoup.connect(detailUrl).userAgent("Mozilla/5.0").timeout(30000).post();
				 
			} catch (IOException e) {
				e.printStackTrace();
			}
			//获取class = job-item-title
			Element links1 = doc.getElementsByClass("job-item-title").first(); 
			//获取薪酬
			String money = links1.text();
			//分割薪酬 和 回复的时间
			String [] arr = money.split("\\s+");
			System.out.println(arr[0]);
			money = arr[0];
			return money;
			
		
		}
		
		//地址的详细信息爬取
		public  String getAddressByDetailUrl(String detailUrl){
			Document doc = null;
			
			try {
				//获取doc和解析HTML页面。
				doc = Jsoup.connect(detailUrl).userAgent("Mozilla/5.0").timeout(30000).post();
				 
			} catch (IOException e) {
				e.printStackTrace();
			}
			//获取class = job-item-title
			Elements links1 = doc.getElementsByClass("basic-infor"); 
			//获取<a href />标签
			links1.select("a");
			//获取地址
			String address = links1.text();
			
			//分割地址 和 发布日期的时间
			String [] arr = address.split("\\s+");
			System.out.println(arr[0]);
			return arr[0];
		
		}
		
		//发布日期信息爬取
		public  String getDateByDetailUrl(String detailUrl){
			Document doc = null;
			try {
				//获取doc和解析HTML页面。
				doc = Jsoup.connect(detailUrl).userAgent("Mozilla/5.0").timeout(30000).post();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			//获取class = job-item-title
			Elements links1 = doc.select("time");
			//获取title属性值
			String date = links1.attr("title");
			System.out.println(date);
			return date;
		}
		
		//公司描述的详细信息爬取
		public  String getCompanyInfoByDetailUrl(String detailUrl)
		{
			Document doc = null;
			
			try {
				doc = Jsoup.connect(detailUrl).userAgent("Mozilla/5.0").timeout(30000).post();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Elements links1 = doc.getElementsByClass("info-word");
			//获取公司简介信息
			String description = links1.text();
			System.out.println(description);
			return description;
		}
		
		//职位描述的详细信息爬取
		public  String getRequiredByDetailUrl(String detailUrl){
			Document doc = null;
			try {
				//获取doc和解析HTML页面。
				doc = Jsoup.connect(detailUrl).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").timeout(30000).post();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			/*
			 * <div class="content content-word">
			 * 因为要选取的是片段 所以不可出现“ ” 即选择后面的content-word即可
			 */		
			Elements links1 = doc.getElementsByClass("content-word"); 
			//获取职位描述信息
			String description = links1.text();
			System.out.println(description);
			return description;
		}
		
		//获取详细地址爬取信息
		public  Message getDetailBydetailUrl(String detailUrl,Message message){
			String positionMoney=null;
			String companyAddress =null;
			String time =null;
			String positionRequired =null;
			String companyInfo =null;
			message = getCompanyByDetailUrl(detailUrl,message);
			positionMoney=getMoneyByDetailUrl(detailUrl);
			companyAddress=getAddressByDetailUrl(detailUrl);
			time=getDateByDetailUrl(detailUrl);
			positionRequired=getRequiredByDetailUrl(detailUrl);
			companyInfo=getCompanyInfoByDetailUrl(detailUrl);
			
			message.getPosition().setMoney(positionMoney);
			message.setPsMoney(positionMoney);
			
			message.getCompany().setAddress(companyAddress);
			message.setCpAddress(companyAddress);
			message.setTime(time);
			message.getPosition().setRequired(positionRequired);
			message.getCompany().setCpInfo(companyInfo);
			System.out.println("");
			return message;
		}
		//关键词更改
		public String updateKeyWord(String keyWord){
			String urlWithKey = null;
			
			//转码 将中文转化为UTF-8
			try {
				keyWord = URLEncoder.encode(keyWord, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				System.out.println("转码失败");
				e.printStackTrace();
			}
			
			String url = "https://www.liepin.com/zhaopin/?key=java&d_sfrom=search_fp_nvbar&searchttps://www.liepin.com/zhaopin/?key=java&d_sfrom=search_fp_nvbar&searchField=1hField=1";
			urlWithKey  = url.replaceAll("java", keyWord);
			return urlWithKey;
		}
		//Message主键ID的填充
		public Message InsertId(int id,Message message){
			message.setId(id);
			message.getCompany().setId(id);
			message.getPosition().setId(id);
			
			return message;
		}

}
			

