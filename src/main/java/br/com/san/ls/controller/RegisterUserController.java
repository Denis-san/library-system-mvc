package br.com.san.ls.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.san.ls.dto.UserDTO;

@Controller
@RequestMapping("/user")
public class RegisterUserController {
	
	@GetMapping("/new")
	public ModelAndView showRegisterUserForm() {
		ModelAndView modelAndView = new ModelAndView("/pages/user/register-user");
		modelAndView.addObject("user", new UserDTO());
		return modelAndView;
	}
	
	@PostMapping("/processRegister")
	public String processRegister(UserDTO userDTO) {
		
		
		return "/pages/user/email-confirmation";
	}
	
	
}
