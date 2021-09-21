package it.uniroma3.siw.spring.enjoy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class mainController 
{
	
	@RequestMapping(value= {"/", "home"}, method=RequestMethod.GET)
	public String index(Model model)
	{
		return "home.html";
	}

}
