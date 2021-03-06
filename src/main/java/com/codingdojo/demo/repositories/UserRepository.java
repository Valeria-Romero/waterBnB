package com.codingdojo.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.codingdojo.demo.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	@Query( "SELECT u FROM User u WHERE email = ?1" )
	List<User> selectUserByEmail( String email );
	
	@Modifying
	@Transactional
	@Query( value = "INSERT INTO users(firstname, lastname, email, password, typeofuser) " +
					"VALUES(?1, ?2, ?3, ?4, ?5)", nativeQuery = true )
	void insertUser( String firstname, String lastname, String email, String password, Long typeofuser );
}