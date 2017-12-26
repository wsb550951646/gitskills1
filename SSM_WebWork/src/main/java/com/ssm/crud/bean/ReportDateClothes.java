package com.ssm.crud.bean;

import java.util.List;

public class ReportDateClothes {
	
	private List<String> categories;
	private List<Integer> data;
	
	public ReportDateClothes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public List<Integer> getData() {
		return data;
	}
	public void setData(List<Integer> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ReportDateClothes [categories=" + categories + ", data=" + data + "]";
	}
	
	

}
