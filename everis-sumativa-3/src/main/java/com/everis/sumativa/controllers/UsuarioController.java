package com.everis.sumativa.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.everis.sumativa.models.Carrito;
import com.everis.sumativa.models.Usuario;
import com.everis.sumativa.services.CarritoService;
import com.everis.sumativa.services.UsuarioService;
import com.everis.sumativa.util.Validaciones;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	CarritoService carritoService;
	
	@RequestMapping("")
	public String inicioUsuario(Model model, HttpSession session) {

			List<Usuario> usuarios  = usuarioService.findAll();
			model.addAttribute("usuarios", usuarios);
			return "usuario.jsp";

	}
	
	
	@RequestMapping("/login")
	public String loginUsuario(Model model,
			@RequestParam("mail") String mail,
			@RequestParam("pass") String pass, 
			HttpSession session) {
		
		session.setAttribute("registrado", 0);
		if (usuarioService.autentication( mail,  pass)) {
			Usuario usuario= usuarioService.findByEmail(mail);
			
			session.setAttribute("registrado", 1);
			session.setAttribute("nombreUsuario", usuario.getNombre());
			session.setAttribute("usuarioId", usuario.getId());
			model.addAttribute("mensaje", true);
			session.setAttribute("rol", usuario.getRol());
		
			return "index.jsp";
		}else {
			model.addAttribute("mensaje", false);
			return "login.jsp";
		}
		
	}
	
	
	

	
	@RequestMapping("/insertar")
	public String insertar(@RequestParam("nombre") String nombre,
			@RequestParam("pass") String pass, 
			@RequestParam("correo") String correo, 
			Model model,
			HttpSession session) {
		
	
			
			boolean correoValido = false;
			Usuario usuario = new Usuario();
			usuario.setNombre(nombre);
			usuario.setPassword(pass);
			if (Validaciones.validarCorreo(correo)) {
				usuario.setCorreo(correo);
				usuario = usuarioService.saveUsuario(usuario);
				correoValido = true;
			}
			
			Carrito carrito = new Carrito();
			carrito.setUsuario(usuario);
			carrito = carritoService.save(carrito);
			if ((Integer)session.getAttribute("registrado")==0) {
				session.setAttribute("registrado", 1);
				session.setAttribute("nombreUsuario", usuario.getNombre());

			}
			
			model.addAttribute("mensaje", correoValido);
			
			return "redirect:/usuario";

		
		
	}
	
	
	@RequestMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id, Model model, HttpSession session) {
		if ((Integer)session.getAttribute("registrado")==1) {
			Optional<Usuario> usuario= usuarioService.findById(id);
			
			model.addAttribute("usuario",usuario.get());
			return "usuarioEdit.jsp";
		}else {
			return "login.jsp";
		}
	}
	
	
	@RequestMapping("/update")
	public String update(@ModelAttribute("usuario") Usuario user, HttpSession session) {
		if ((Integer)session.getAttribute("registrado")==1) {
			usuarioService.saveUsuario(user);
			return "redirect:/usuario";
		}else {
			return "login.jsp";
		}
	}

}
