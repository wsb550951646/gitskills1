package com.ssm.crud.bean;

public class Company {

	private Integer id;
	private String address;
	private String cpInfo;
	private String cpUrl;
	private String cpName;
	
	public Company(Integer id, String address, String cpInfo, String cpUrl, String cpName) {
		super();
		this.id = id;
		this.address = address;
		this.cpInfo = cpInfo;
		this.cpUrl = cpUrl;
		this.cpName = cpName;
	}
	
	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getCpInfo() {
		return cpInfo;
	}

	public void setCpInfo(String cpInfo) {
		this.cpInfo = cpInfo;
	}

	public String getCpUrl() {
		return cpUrl;
	}

	public void setCpUrl(String cpUrl) {
		this.cpUrl = cpUrl;
	}


	@Override
	public String toString() {
		return "Company [id=" + id + ", address=" + address + ", cpInfo=" + cpInfo + ", cpUrl=" + cpUrl + ", cpName="
				+ cpName + "]";
	}

	

	
}
