package ru.t_systems.demail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.t_systems.demail.service.UserService;

@Controller
public class SecurityNavigation {

	@Autowired
	private UserService userService;
	
	// old
	@RequestMapping(value = "/user-login", method = RequestMethod.GET)
	public ModelAndView loginForm() {
		return new ModelAndView("login-form");
	}

	@RequestMapping(value = "/error-login", method = RequestMethod.GET)
	public ModelAndView invalidLogin() {
		ModelAndView modelAndView = new ModelAndView("login-form");
		modelAndView.addObject("error", true);
		return modelAndView;
	}

	@RequestMapping(value = "/success-login", method = RequestMethod.GET)
	public ModelAndView successLogin() {
		return new ModelAndView("success-login");
	}

	// old finish

	/*@RequestMapping(value = "/mail/user-profile", method = RequestMethod.GET)
	public ModelAndView userProfile() {
		return new ModelAndView("profile");
	}*/

	
}