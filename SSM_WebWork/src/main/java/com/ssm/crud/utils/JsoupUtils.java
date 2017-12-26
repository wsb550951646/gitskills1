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

		//��˾����ϸ��Ϣ��ȡ
		public  Message getCompanyByDetailUrl(String detailUrl,Message message){
			Document doc = null;
			try {
				//��ȡdoc�ͽ���HTMLҳ�档
				doc = Jsoup.connect(detailUrl).userAgent("Mozilla/5.0").timeout(30000).post();
				 
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Elements links1 = doc.getElementsByClass("title-info"); 
			for (Element e : links1) {
				//��ȡ<a href />��ǩ������
				Elements elements = e.select("a[href]");  
				for (Element element : elements) {
					//��href���Ը�ֵ��detailUrl
					String CompanyUrl = element.attr("href");
					if(CompanyUrl.contains("company"))
					{
					//��ȡtext�ı�����---��˾�������Լ�url
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
		
		//н�����ϸ��Ϣ��ȡ
		public  String getMoneyByDetailUrl(String detailUrl){
			Document doc = null;
			String mondey = null;
			try {
				//��ȡdoc�ͽ���HTMLҳ�档
				doc = Jsoup.connect(detailUrl).userAgent("Mozilla/5.0").timeout(30000).post();
				 
			} catch (IOException e) {
				e.printStackTrace();
			}
			//��ȡclass = job-item-title
			Element links1 = doc.getElementsByClass("job-item-title").first(); 
			//��ȡн��
			String money = links1.text();
			//�ָ�н�� �� �ظ���ʱ��
			String [] arr = money.split("\\s+");
			System.out.println(arr[0]);
			money = arr[0];
			return money;
			
		
		}
		
		//��ַ����ϸ��Ϣ��ȡ
		public  String getAddressByDetailUrl(String detailUrl){
			Document doc = null;
			
			try {
				//��ȡdoc�ͽ���HTMLҳ�档
				doc = Jsoup.connect(detailUrl).userAgent("Mozilla/5.0").timeout(30000).post();
				 
			} catch (IOException e) {
				e.printStackTrace();
			}
			//��ȡclass = job-item-title
			Elements links1 = doc.getElementsByClass("basic-infor"); 
			//��ȡ<a href />��ǩ
			links1.select("a");
			//��ȡ��ַ
			String address = links1.text();
			
			//�ָ��ַ �� �������ڵ�ʱ��
			String [] arr = address.split("\\s+");
			System.out.println(arr[0]);
			return arr[0];
		
		}
		
		//����������Ϣ��ȡ
		public  String getDateByDetailUrl(String detailUrl){
			Document doc = null;
			try {
				//��ȡdoc�ͽ���HTMLҳ�档
				doc = Jsoup.connect(detailUrl).userAgent("Mozilla/5.0").timeout(30000).post();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			//��ȡclass = job-item-title
			Elements links1 = doc.select("time");
			//��ȡtitle����ֵ
			String date = links1.attr("title");
			System.out.println(date);
			return date;
		}
		
		//��˾��������ϸ��Ϣ��ȡ
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
			//��ȡ��˾�����Ϣ
			String description = links1.text();
			System.out.println(description);
			return description;
		}
		
		//ְλ��������ϸ��Ϣ��ȡ
		public  String getRequiredByDetailUrl(String detailUrl){
			Document doc = null;
			try {
				//��ȡdoc�ͽ���HTMLҳ�档
				doc = Jsoup.connect(detailUrl).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").timeout(30000).post();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			/*
			 * <div class="content content-word">
			 * ��ΪҪѡȡ����Ƭ�� ���Բ��ɳ��֡� �� ��ѡ������content-word����
			 */		
			Elements links1 = doc.getElementsByClass("content-word"); 
			//��ȡְλ������Ϣ
			String description = links1.text();
			System.out.println(description);
			return description;
		}
		
		//��ȡ��ϸ��ַ��ȡ��Ϣ
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
		//�ؼ��ʸ���
		public String updateKeyWord(String keyWord){
			String urlWithKey = null;
			
			//ת�� ������ת��ΪUTF-8
			try {
				keyWord = URLEncoder.encode(keyWord, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				System.out.println("ת��ʧ��");
				e.printStackTrace();
			}
			
			String url = "https://www.liepin.com/zhaopin/?key=java&d_sfrom=search_fp_nvbar&searchttps://www.liepin.com/zhaopin/?key=java&d_sfrom=search_fp_nvbar&searchField=1hField=1";
			urlWithKey  = url.replaceAll("java", keyWord);
			return urlWithKey;
		}
		//Message����ID�����
		public Message InsertId(int id,Message message){
			message.setId(id);
			message.getCompany().setId(id);
			message.getPosition().setId(id);
			
			return message;
		}

}
			

