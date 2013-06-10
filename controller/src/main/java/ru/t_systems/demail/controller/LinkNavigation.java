package ru.t_systems.demail.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.t_systems.demail.model.user.Account;
import ru.t_systems.demail.model.message.Message;
import ru.t_systems.demail.model.message.MessageStatuss;
import ru.t_systems.demail.model.user.Role;
import ru.t_systems.demail.model.user.User;
import ru.t_systems.demail.service.UserService;
import ru.t_systems.demail.service.message.MessageService;
import ru.t_systems.demail.service.message.MessageStatusService;

@Controller
public class LinkNavigation {

	@Autowired
	UserService userService;

	@Autowired
	MessageService messageService;

	@Autowired
	MessageStatusService messageStatusService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homePage() {
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView indexPage() {
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/mail/index", method = RequestMethod.GET)
	public ModelAndView moderatorPage() {
		return new ModelAndView("mail-main");
	}

	@RequestMapping(value = "/admin/first", method = RequestMethod.GET)
	public ModelAndView firstAdminPage() {
		return new ModelAndView("admin-first");
	}

	@RequestMapping(value = "/admin/second", method = RequestMethod.GET)
	public ModelAndView secondAdminPage() {
		return new ModelAndView("admin-second");
	}

	@RequestMapping(value = "/403.html", method = RequestMethod.GET)
	public ModelAndView page403() {
		return new ModelAndView("403");
	}

	@RequestMapping(value = "/404.html", method = RequestMethod.GET)
	public ModelAndView page404() {
		return new ModelAndView("404");
	}

	@RequestMapping(value = "/install.html", method = RequestMethod.GET)
	public ModelAndView install() {
		if (userService.getUser("admin") == null) {
			Role role = new Role();
			User user = new User();
			user.setLogin("admin");
			user.setPassword("111111");
			role.setRole("admin");
			user.setRole(role);
			
			Account account = new Account();
			account.setAccountName("admin@de-mail.de");
			
			Account account1 = new Account();
			account1.setAccountName("admin1@de-mail.de");
			
			List<Account> set = new ArrayList<Account>();
			set.add(account);
			set.add(account1);
			user.setAccounts(set);
			
			Message message = new Message();
			message.setBody("test");
			
			MessageStatuss messageStatuss = new MessageStatuss();
			messageStatuss.setAcounts(account);
			messageStatuss.setAcountsSender(account);
			
			MessageStatuss messageStatuss1 = new MessageStatuss();
			messageStatuss1.setAcounts(account1);
			messageStatuss1.setAcountsSender(account);
			
			List<MessageStatuss> list = new ArrayList<MessageStatuss>();
			
			list.add(messageStatuss);
			list.add(messageStatuss1);
			message.setStatus(list);
			
			userService.addUser(user);
			messageService.addMessage(message);
			

			return new ModelAndView("install");
		}
		return new ModelAndView("user-login");
	}

}
