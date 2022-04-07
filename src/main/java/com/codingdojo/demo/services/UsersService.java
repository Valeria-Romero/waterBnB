package com.codingdojo.demo.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.codingdojo.demo.models.User;
import com.codingdojo.demo.repositories.PropertyRepository;
import com.codingdojo.demo.repositories.ReviewsRepository;
import com.codingdojo.demo.repositories.UserRepository;

@Service
public class UsersService {
	
	private final UserRepository userRepository;
	private final PropertyRepository propertyRepository;
	private final ReviewsRepository reviewsRepository;
	
	public UsersService( UserRepository userRepository, PropertyRepository propertyRepository, ReviewsRepository reviewsRepository) {
		this.userRepository = userRepository;
		this.propertyRepository = propertyRepository;
		this.reviewsRepository = reviewsRepository;
	}
	
	public List<User> getUserByEmail( String email ){
		return userRepository.selectUserByEmail(email);
	}
	
	public void registerUser( String firstname, String lastname, String email, String password, Long typeofuser ) {
		String encryptPassword = BCrypt.hashpw(password, BCrypt.gensalt());
		System.out.println( encryptPassword );
		userRepository.insertUser(firstname, lastname, email, encryptPassword, typeofuser);
	}
	
	public boolean validateUser( User currentUser, String password ) {
		return BCrypt.checkpw( password, currentUser.getPassword() );
	}
	
	
}