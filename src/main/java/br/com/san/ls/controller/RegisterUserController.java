package br.com.san.ls.controller;

import javax.servlet.http.HttpSession;
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
import br.com.san.ls.dto.UserLoginDTO;

@Controller
@RequestMapping("/user")
public class RegisterUserController {

	@GetMapping("/new")
	public ModelAndView showRegisterUserForm() {
		ModelAndView modelAndView = new ModelAndView("/pages/user/register-user");
		modelAndView.addObject("user", new UserDTO());
		return modelAndView;
	}
	
	@GetMapping("/home")
	public String showUseHomePage(HttpSession session) {
		
		if(session.getAttribute("user") == null) {
			return "redirect:/login";
		}
		
		return "/pages/user/user-home";
	}

	@PostMapping("/processRegister")
	public ModelAndView processRegister(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bdResult, HttpSession session) {
		ModelAndView mv = new ModelAndView("/pages/user/register-user");

		if (bdResult.hasErrors() == false) {
			UserLoginDTO userLoginDTO =  userDTO.getUserLoginDTO();
			session.setAttribute("user", userLoginDTO);
			session.setAttribute("userName", userDTO.getName());
			mv.setViewName("redirect:/user/home");
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
