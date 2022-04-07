package com.codingdojo.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table( name = "users" )
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size( max = 100 )
	private String firstname;
	
	@NotNull
	@Size( max = 100 )
	private String lastname;

	@Size( max = 100 )
	private String email;
	
	@NotNull
	@Size( max = 255 )
	private String password;
	
	@NotNull
	@Column(name = "typeofuser", columnDefinition = "TINYINT")
	private Long typeofuser;
	
	@Transient
	private String passwordConfirmation;
	
	public User(){}
	
	public User( String firstname, String lastname, String password,  String email, Long typeofuser ) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.typeofuser = typeofuser;
	}
	

	public Long getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	
	public Long getTypeofuser() {
		return typeofuser;
	}

	public void setTypeofuser(Long typeofuser) {
		this.typeofuser = typeofuser;
	}
}