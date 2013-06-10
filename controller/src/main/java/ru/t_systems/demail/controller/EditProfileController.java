package ru.t_systems.demail.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.t_systems.demail.model.user.Account;
import ru.t_systems.demail.model.message.Message;
import ru.t_systems.demail.model.message.MessageStatuss;
import ru.t_systems.demail.model.user.Country;
import ru.t_systems.demail.model.user.User;
import ru.t_systems.demail.service.CountryService;
import ru.t_systems.demail.service.RoleService;
import ru.t_systems.demail.service.UserService;
import ru.t_systems.demail.service.message.MessageService;
import ru.t_systems.demail.service.message.MessageStatusService;

@Controller
@RequestMapping("/mail/profile.html")
public class EditProfileController {
	@Autowired
	private CountryEditor countryEditor;

	@Autowired
	private EditProfileValidation editProfileValidation;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private MessageStatusService messageStatuss;
	
	@Autowired
	private MessageService messageService;

	@Autowired
	@Qualifier("org.springframework.security.authenticationManager")
	protected AuthenticationManager authenticationManager;

	// Display the form on the get request
	@RequestMapping(method = RequestMethod.GET)
	public String showEditProfile(Map<String, EditProfile> model) {
		User user = userService.getUser(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		EditProfile editProfile = new EditProfile(user);
		model.put("profile", editProfile);
		List<Account> st = user.getAccounts();
		System.out.println("Account n = "+st.size());
		List<MessageStatuss> mst = new ArrayList<MessageStatuss>();
		for(Account ac :st){
		mst.addAll(ac.getStatus());
		}
		System.out.println("Status n = "+mst.size());
		List<Message> mes = new ArrayList<Message>();
		for(MessageStatuss mm: mst){
			mes.add(mm.getMessage());
		}
		System.out.println("Message n = "+mes.size());
		for(Message mesage: mes){
			System.out.println("Message body = "+mesage.getBody());
		}
		return "profileform";
	}

	// Process the form.
	@RequestMapping(method = RequestMethod.POST)
	public String processEditProfile(
			@ModelAttribute("profile") @Valid EditProfile editProfile,
			BindingResult result) {

		editProfileValidation.validate(editProfile, result);
		
		if (result.hasErrors()) {
			return "profileform";
		} else {
			
			User user = userService.getUser(SecurityContextHolder.getContext()
					.getAuthentication().getName());

			
			
			userService.update(editProfile.updateUser(user));
			
			return "profileform";
		}

	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Country.class, countryEditor);
	}

	@ModelAttribute("countryList")
	public List<Country> populateCountryList() {

		return countryService.getAllCountry();
	}

	public int test() {
		int a = 0;
		
//		User user = userService.getUser(SecurityContextHolder.getContext()
//				.getAuthentication().getName());
		
		
//		a = messageStatuss.getMessageStatusByAccount(
//				userService.getUser(
//						SecurityContextHolder.getContext().getAuthentication()
//								.getName()).getId()).size();
//		System.out.println("---COUNT = " + a);
//	List<Message> mes = messageService.getMessageByStatus(messageStatuss.getMessageStatusByAccount(
//				userService.getUser(
//						SecurityContextHolder.getContext().getAuthentication()
//								.getName()).getId()));
//		System.out.println("----MESS COUNT = "+mes.size());
		return a;
	}

}
