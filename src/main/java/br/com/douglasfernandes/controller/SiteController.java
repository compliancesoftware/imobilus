package br.com.douglasfernandes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional
public class SiteController {
	
	@RequestMapping(value={"/","home"})
	public String home(){
		return "index";
	}
	
}
