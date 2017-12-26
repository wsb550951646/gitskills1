package com.ssm.crud.dao;

import java.util.List;

import com.ssm.crud.bean.Company;

public interface CompanyDao {
	
	public List<Company> getAll();
	
	public Company getCompanyById(Integer id);
	
	public Integer insertByCompany(Company company);
	
	public Integer deleteById(Integer id);
	
	public Integer updateByCompany(Company company);

	public Company getCompanyOneByName(String psName);
	
	public Integer deleteAll();

}
