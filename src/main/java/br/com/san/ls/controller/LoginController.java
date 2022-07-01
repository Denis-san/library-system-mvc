package br.com.san.ls.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.san.ls.dto.UserLoginDTO;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("/")
	public ModelAndView showLoginForm() {
		ModelAndView mv = new ModelAndView("/pages/user/login");
		mv.addObject("login", new UserLoginDTO());
		return mv;
	}
	
	@PostMapping("/processLogin")
	public ModelAndView processLogin(@Valid @ModelAttribute("login")UserLoginDTO login, BindingResult bdResult) {
		ModelAndView mv = new ModelAndView("/pages/user/login");
		
		if(!bdResult.hasErrors()) {
			mv.setViewName("/pages/user/home-user");
		}else {
			
		}
		
		return mv;
	}
}
