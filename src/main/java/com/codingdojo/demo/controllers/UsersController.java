package com.codingdojo.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.demo.models.User;
import com.codingdojo.demo.services.UsersService;


@Controller
public class UsersController {
	
	private final UsersService usersService;
	
	public UsersController( UsersService usersService ) {
		this.usersService = usersService;
	}
	
	@RequestMapping( value = "/guest/signin", method = RequestMethod.GET )
	public String index() {
		return "index.jsp";
	}
	
	
	@RequestMapping( value = "/registerUser", method = RequestMethod.POST )
	public String registerUser( @RequestParam(value="firstname") String firstname,
								@RequestParam(value="lastname") String lastname,
								@RequestParam(value="email") String email,
								@RequestParam(value="password") String password,
								@RequestParam(value="passwordConfirmation") String passwordConfirmation,
								@RequestParam(value="typeofuser") Long typeofuser,
								RedirectAttributes redirectAttributes,
								HttpSession session) {
		
		List<User> user = usersService.getUserByEmail(email);
		
		if( user.size() > 0 ) {
			redirectAttributes.addFlashAttribute("errorMessage", "That user email already exists!");
			return "redirect:/guest/signin";
		}
		else {
			if( ! password.equals( passwordConfirmation ) ) {
				redirectAttributes.addFlashAttribute("errorMessage", "Password and password confirmation do not match");
				return "redirect:/guest/signin";
			}
			
			if(firstname.isEmpty() && lastname.isEmpty() && email.isEmpty() && password.isEmpty() && passwordConfirmation.isEmpty()) {
				redirectAttributes.addFlashAttribute("emptyspace", "You leaved an empty space");
				return "redirect:/guest/signin";
			}
			else {
				usersService.registerUser(firstname, lastname, email, password, typeofuser );
				session.setAttribute( "firstName", firstname );
				session.setAttribute( "lastName", lastname );
				return "redirect:/";
			}
		}
	}
	
	@RequestMapping( value = "/validateUser", method = RequestMethod.POST )
	public String login( @RequestParam(value="userEmail") String email,
						 @RequestParam(value="userPassword") String password,
						 RedirectAttributes redirectAttributes,
						 HttpSession session) {
		
		List<User> user = usersService.getUserByEmail(email);
		if( user.size() == 0 ) {
			redirectAttributes.addFlashAttribute("loginErrorMessage", "Wrong credentials");
			return "redirect:/guest/signin";
		}
		else {
			User currentUser = user.get(0);
			
			if( usersService.validateUser(currentUser, password) && currentUser.getTypeofuser() == 1 ) {
				session.setAttribute( "firstName", currentUser.getFirstname() );
				session.setAttribute( "lastName", currentUser.getLastname() );
				return "redirect:/dashboard";
			}
			if( usersService.validateUser(currentUser, password) && currentUser.getTypeofuser() == 0 ) {
				session.setAttribute( "firstName", currentUser.getFirstname() );
				session.setAttribute( "lastName", currentUser.getLastname() );
				return "redirect:/";
			}
			else {
				redirectAttributes.addFlashAttribute("loginErrorMessage", "Wrong password");
				return "redirect:/guest/signin";
			}
		}
	}
	
	@RequestMapping( value = "/dashboard", method = RequestMethod.GET )
	public String dashboard(HttpSession session, Model model) {
		
		String currentUser = (String) session.getAttribute("firstName");
		if(currentUser == null) {
			return "redirect:/guest/signin";
		}
		model.addAttribute("currentUser",currentUser);
		return"dashboard.jsp";
	}
	
	@RequestMapping( value = "/", method = RequestMethod.GET )
	public String home() {
			return "home.jsp";
	}

	
	@RequestMapping( value = "/logout", method = RequestMethod.GET )
	public String logout( HttpSession session ) {
		session.removeAttribute("firstName");
		session.removeAttribute("lastName");
		return "redirect:/guest/signin";
	}
}