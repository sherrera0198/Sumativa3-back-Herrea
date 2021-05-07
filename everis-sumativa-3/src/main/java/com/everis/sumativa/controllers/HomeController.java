package com.everis.sumativa.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.everis.sumativa.services.UsuarioService;

@Controller
public class HomeController {

	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping("")
	public String Inicio(Model model, HttpSession session) {
		session.setAttribute("registrado", 0);
		model.addAttribute("mensaje", true);
		return"login.jsp";
	}
	
	@RequestMapping("/login")
	public String login(HttpSession session) {
		session.setAttribute("registrado", 0);
		return"login.jsp";
	}
	
	@RequestMapping("/cerrarSesion")
	public String logOutUsuario(Model model, HttpSession session) {

		session.setAttribute("registrado", 0);
		model.addAttribute("mensaje",true);
		return "login.jsp";
	}
	
	@RequestMapping("/index")
	public String Index(Model model, HttpSession session) {
		
		if (session.getAttribute("registrado")!=null) {
			if ((Integer)session.getAttribute("registrado")==1) {
				return"index.jsp";
			}else {
				return"login.jsp";
			}
		}
		return"login.jsp";
	}
	
	
}
