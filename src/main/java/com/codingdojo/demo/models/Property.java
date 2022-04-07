package com.codingdojo.demo.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="properties")
public class Property {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size( max = 255 )
	private String address;
	
	@NotNull
	@Size( max = 45 )
	private String poolsize;
	
	@NotNull
	private Long cost;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="reviews",
		joinColumns=@JoinColumn(name="property_id"),
		inverseJoinColumns=@JoinColumn(name="user_id")
	)
	private List<User> users;
}
