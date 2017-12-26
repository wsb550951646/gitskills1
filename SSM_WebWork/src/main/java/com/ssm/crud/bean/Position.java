package com.ssm.crud.bean;

public class Position {


	private Integer id;
	private String money;
	private String psName;
	private String required;
	private String psUrl;
	
	public Position(Integer id, String money, String psName, String required, String psUrl) {
		super();
		this.id = id;
		this.money = money;
		this.psName = psName;
		this.required = required;
		this.psUrl = psUrl;
	}

	
	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getPsName() {
		return psName;
	}
	
	public void setPsName(String psName) {
		this.psName = psName;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getRequired() {
		return required;
	}
	public void setRequired(String required) {
		this.required = required;
	}
	
	public String getPsUrl() {
		return psUrl;
	}

	public void setPsUrl(String psUrl) {
		this.psUrl = psUrl;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", money=" + money + ", psName=" + psName + ", required=" + required + ", psUrl="
				+ psUrl + "]";
	}
	

}
