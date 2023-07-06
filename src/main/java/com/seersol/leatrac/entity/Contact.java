package com.seersol.leatrac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "lt_leads")
public class Contact {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "lead_id")
	private Integer id;

	@NotEmpty
	@Column(name="first_name")
	private String firstName = "";

	@NotEmpty
	@Column(name="last_name")
	private String lastName = "";

	@ManyToOne
	@JoinColumn(name = "company_id")
	@NotNull
	@JsonIgnoreProperties({ "employees" })
	private Company company;

	@NotEmpty
	@Column(name="city")
	private String city = "";

	@NotEmpty
	@Column(name="state")
	private String state = "";

	@NotNull
	@ManyToOne
	@JoinColumn(name="status_id")
	private Status status;

	@Email
	@NotEmpty
	@Column(name="email")
	private String email = "";

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
