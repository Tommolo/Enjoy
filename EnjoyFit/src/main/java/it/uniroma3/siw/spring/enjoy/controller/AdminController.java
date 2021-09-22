package it.uniroma3.siw.spring.enjoy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.enjoy.model.Cliente;
import it.uniroma3.siw.spring.enjoy.model.Credentials;
import it.uniroma3.siw.spring.enjoy.service.CredentialsService;

@Controller
public class AdminController {
	
	@Autowired
	CredentialsService credentialsService;
	
	@RequestMapping(value= {"/admin/areaAdmin"}, method=RequestMethod.GET)
	public String index(Model model)
	{
		//retrieve current user
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
				
		Credentials c = this.credentialsService.getCredentials(username);
		Cliente admin = c.getUser();
		
		model.addAttribute("admin", admin);
		return "admin/areaAdmin";
	}


}
