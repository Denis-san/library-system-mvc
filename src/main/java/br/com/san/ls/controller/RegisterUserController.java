package br.com.san.ls.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public ModelAndView processRegister(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bdResult) {
		ModelAndView mv = new ModelAndView("/pages/user/register-user");

		if (!bdResult.hasErrors()) {
			mv.setViewName("/pages/user/welcome_user");
		} else {
			for (FieldError error : bdResult.getFieldErrors()) {
				if (error.getDefaultMessage().contains("emails")) {
					mv.addObject("emailVerifyError", error.getDefaultMessage());
				}
				if (error.getDefaultMessage().contains("senhas")) {
					mv.addObject("passwordVerifyError", error.getDefaultMessage());
				}
			}
			mv.addObject("user", userDTO);
		}

		return mv;
	}

}
