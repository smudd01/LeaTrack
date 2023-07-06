package com.seersol.leatrac.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "lt_company")
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@NotBlank
	@Column(name="name")
	private String name;

	@OneToMany(mappedBy = "company")
	private List<Contact> employees = new LinkedList<>();

	@Formula("(select count(c.lead_id) from lt_leads c where c.company_id = id)")
	private int employeeCount;

	public int getEmployeeCount() {
		return employeeCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Contact> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Contact> employees) {
		this.employees = employees;
	}

}
