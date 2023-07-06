package com.seersol.leatrac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seersol.leatrac.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
	
	List<Company> findAll();

}
