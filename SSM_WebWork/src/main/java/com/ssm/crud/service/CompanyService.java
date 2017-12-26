package com.ssm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.crud.bean.Company;
import com.ssm.crud.dao.CompanyDao;

@Service
public class CompanyService {

	@Autowired
	CompanyDao companyDao;
	
	public Integer InsertByCompany(Company company){
		
		return companyDao.insertByCompany(company);
	}
	
	public Integer deleteById(Integer id){
		
		return companyDao.deleteById(id);
	}
	
	public Integer updateByCompany(Company company){
		return companyDao.updateByCompany(company);
	}
	
	public Company getCompanyById(Integer id){
		return companyDao.getCompanyById(id);
	}
	
	public List<Company> getAll(){
		
		return companyDao.getAll();
	}
	
	public Company getCompanyOneByName(String psName){
		
		return companyDao.getCompanyOneByName(psName);
	}
	
	



	
	
}
