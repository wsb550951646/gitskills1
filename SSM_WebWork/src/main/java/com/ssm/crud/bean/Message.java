package com.ssm.crud.bean;

import org.springframework.format.annotation.DateTimeFormat;

public class Message {

	private Integer id;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private String time;
	private Company company;
	private Position position;
	private String psName;
	private String psMoney;
	private String cpName;
	private String cpAddress;
	

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Message(Integer id, String time, Company company, Position position) {
		super();
		this.id = id;
		this.time = time;
		this.company = company;
		this.position = position;
	}
	
	public Message(Integer id, String time, Company company, Position position, String psName, String money,
			String cpName, String address) {
		super();
		this.id = id;
		this.time = time;
		this.company = company;
		this.position = position;
		this.psName = psName;
		this.psMoney = money;
		this.cpName = cpName;
		this.cpAddress = address;
	}

	public String getPsName() {
		return psName;
	}
	
	public void setPsName(String psName) {
		this.psName = psName;
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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		return "Message [id=" + id + ", time=" + time + ", company=" + company + ", position=" + position + ", psName="
				+ psName + ", money=" + psMoney + ", cpName=" + cpName + ", address=" + cpAddress + "]";
	}

	public String getPsMoney() {
		return psMoney;
	}

	public void setPsMoney(String psMoney) {
		this.psMoney = psMoney;
	}

	public String getCpAddress() {
		return cpAddress;
	}

	public void setCpAddress(String cpAddress) {
		this.cpAddress = cpAddress;
	}
	

}
