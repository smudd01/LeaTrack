/**
 * 
 */
package com.seersol.leatrac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author Ravi.sangubotla
 *
 */
@Entity
@Table(name="lt_users")
public class AppUser {

	@Id
	@Column(name = "table_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(name = "user_name")
	String userName;

	@Column(name = "password")
	String password;

	@Column(name = "email")
	String email;

	@NotNull
	@ManyToOne
	@JoinColumn(name="status")
	private UserStatus status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
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
		return "AppUser [id=" + id + ", userName=" + userName + ", email=" + email + ", status=" + status.getName() + "]";
	}

}
