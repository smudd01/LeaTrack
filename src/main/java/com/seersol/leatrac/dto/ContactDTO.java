/**
 * 
 */
package com.seersol.leatrac.dto;

import lombok.Data;
import lombok.Getter;

/**
 * @author Ravi.sangubotla
 *
 */
@Data
@Getter
public class ContactDTO {

	private String firstName = "";

	private String lastName = "";

	private String company;

	private String city = "";

	private String state = "";

	private String status;

	private String email = "";

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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "ContactDTO [firstName=" + firstName + ", lastName=" + lastName + ", company=" + company + ", city="
				+ city + ", state=" + state + ", status=" + status + ", email=" + email + "]";
	}

}
