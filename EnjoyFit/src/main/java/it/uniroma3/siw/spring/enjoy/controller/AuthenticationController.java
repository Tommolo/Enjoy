package it.uniroma3.siw.spring.enjoy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.enjoy.controller.validator.ClienteValidator;
import it.uniroma3.siw.spring.enjoy.controller.validator.CredentialsValidator;
import it.uniroma3.siw.spring.enjoy.model.Cliente;
import it.uniroma3.siw.spring.enjoy.model.Credentials;
import it.uniroma3.siw.spring.enjoy.service.CredentialsService;

@Controller
public class AuthenticationController {
	
	@Autowired
	private CredentialsService credentialsService;
	
	@Autowired
	private CredentialsValidator credentialValidator;
	
	@Autowired
	private ClienteValidator clienteValidator;
	
	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(this.getClass());	//per le stampe di log
	
	@RequestMapping(value = "/register", method = RequestMethod.GET) 
	public String showRegisterForm (Model model) {
		model.addAttribute("user", new Cliente());
		model.addAttribute("credentials", new Credentials());
		return "authentication/registrationForm";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String showLoginForm (Model model) 
	{
		
		return "authentication/loginForm";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET) 
	public String logout(Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/default", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {
        
    	//UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	//Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
    	
        return "home";
    }
	
	
	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") Cliente user,
                 BindingResult userBindingResult,
                 @ModelAttribute("credentials") Credentials credentials,
                 BindingResult credentialsBindingResult,
                 Model model) {
		
		this.clienteValidator.validate(user, userBindingResult);
        this.credentialValidator.validate(credentials, credentialsBindingResult);
        
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
        	credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            return "authentication/registrationSuccesful";
        }
		
        return "authentication/registrationForm";
       
    }

}
