package br.com.san.ls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.san.ls.dto.UserLoginDTO;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping({"/", ""})
	public ModelAndView showLoginForm() {
		ModelAndView mv = new ModelAndView("/pages/user/login");
		mv.addObject("login", new UserLoginDTO());
		return mv;
	}

	@PostMapping("/processLogin")
	public ModelAndView processLogin(@ModelAttribute("login") UserLoginDTO login) {
		
		// TODO login logic here 
		ModelAndView mv = new ModelAndView("/pages/user/user-home");
		
		return mv;
	}
}
