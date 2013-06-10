package ru.t_systems.demail.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.t_systems.demail.model.user.Country;
import ru.t_systems.demail.model.user.User;
import ru.t_systems.demail.service.CountryService;
import ru.t_systems.demail.service.RoleService;
import ru.t_systems.demail.service.UserService;

@Controller
@RequestMapping("/registrationform.html")
public class RegistrationController {
	@Autowired
	private CountryEditor countryEditor;

	@Autowired
	private RegistrationValidation registrationValidation;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private CountryService countryService;

	@Autowired
	@Qualifier("org.springframework.security.authenticationManager")
	protected AuthenticationManager authenticationManager;

	public void setRegistrationValidation(
			RegistrationValidation registrationValidation) {
		this.registrationValidation = registrationValidation;
	}

	// Display the form on the get request
	@RequestMapping(method = RequestMethod.GET)
	public String showRegistration(Map<String, Registration> model) {
		Registration registration = new Registration();
		model.put("registration", registration);
		return "registrationform";
	}

	// Process the form.
	@RequestMapping(method = RequestMethod.POST)
	public String processRegistration(@Valid Registration registration,
			BindingResult result, HttpServletRequest request) {
		// set custom Validation by user
		registrationValidation.validate(registration, result);

		if (result.hasErrors()) {
			return "registrationform";
		} else {
			User user = new User();

			user.setLogin(registration.getLogin());
			user.setPassword(registration.getPassword());
			user.setRole(roleService.getRole(2));
			user.setEmail(registration.getEmail());
			if (registration.getCountry() != null
					&& !registration.getCountry().equals("NONE")) {
				user.setCountry((registration.getCountry()));
			}
			userService.addUser(user);

			// Auto login after registration
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					user.getLogin(), user.getPassword());
			request.getSession();
			token.setDetails(new WebAuthenticationDetails(request));
			Authentication authenticatedUser = authenticationManager
					.authenticate(token);

			SecurityContextHolder.getContext().setAuthentication(
					authenticatedUser);

			return "mail-main";
		}

	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Country.class, this.countryEditor);
	}

	@ModelAttribute("countryList")
	public List<Country> populateCountryList() {

		return countryService.getAllCountry();
	}
}
