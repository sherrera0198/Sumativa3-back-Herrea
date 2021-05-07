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

import com.everis.sumativa.models.Carrito;
import com.everis.sumativa.models.Producto;
import com.everis.sumativa.models.Usuario;
import com.everis.sumativa.services.CarritoService;
import com.everis.sumativa.services.ProductoService;
import com.everis.sumativa.services.UsuarioService;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

	@Autowired
	CarritoService carritoService;
	@Autowired
	ProductoService productoService;
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping("")
	public String inicioCarrito(Model model, HttpSession session) {
		if ((Integer)session.getAttribute("registrado")==1) {
			List<Carrito> carritos = carritoService.findAll();
			List<Usuario> usuarios = usuarioService.findAll();
			List<Producto> productos = productoService.findAll();
			/*
			Long usuarioId= (Long) session.getAttribute("usuarioId");
			
			
			Carrito carrito= new Carrito();
			Optional<Usuario> usuario = usuarioService.findById(usuarioId);
			for(Carrito car : carritos) {
				if (car.getUsuario().getId()==usuario.get().getId()) {
					carrito = car;
				}
			}
			
			System.out.println(usuario.get().getNombre());
			*/
			model.addAttribute("productos",productos);
			model.addAttribute("usuarios",usuarios);
			model.addAttribute("carritos",carritos);
		
			
			return "carrito.jsp";
		}else {
			return "login.jsp";
		}
		
	}
	
	
	@RequestMapping("/editar/{id}")
	public String editarCarrito(@PathVariable("id") Long id, Model model, HttpSession session) {
		if ((Integer)session.getAttribute("registrado")==1) {
			Optional<Carrito> carrito= carritoService.findById(id);
			
			List<Producto> listaProductos =  productoService.findAll();
			
			model.addAttribute("carrito", carrito);
			model.addAttribute("listaProductos", listaProductos);
		return "carroEdit.jsp";
		}else {
			return "login.jsp";
		}
	}
	
	@RequestMapping("/update")
	public String updateCarrito(@ModelAttribute("carrito") Carrito carrito , HttpSession session ) {
		if ((Integer)session.getAttribute("registrado")==1) {
			carritoService.save(carrito);
			return "redirect:/carrito";
		}else {
			return "login.jsp";
		}
	}

	@RequestMapping("/agregarProducto/{idProducto}")
	public String agregaProductoCarrito(@PathVariable("idProducto") Long idProducto, 
			HttpSession session ) {
		if ((Integer)session.getAttribute("registrado")==1) {
			
			Optional<Producto> producto = productoService.finByID(idProducto);
			Optional<Usuario> usuario =  usuarioService.findById((Long) session.getAttribute("usuarioId"));
			Optional<Carrito> carrito = carritoService.findById(usuario.get().getCarrito().getId());
			List<Producto> lista_productos= carrito.get().getProductos();
			lista_productos.add(producto.get());
			carrito.get().setProductos(lista_productos);
			carritoService.save(carrito.get());
			
			return "redirect:/carrito";
		}else {
			return "login.jsp";
		}
	}
	
	
	@RequestMapping("/eliminarProducto/{idProducto}")
	public String eliminarProductoCarrito(@PathVariable("idProducto") Long idProducto, 
			HttpSession session ) {
		if ((Integer)session.getAttribute("registrado")==1) {
			
			Optional<Producto> producto = productoService.finByID(idProducto);
			Optional<Usuario> usuario =  usuarioService.findById((Long) session.getAttribute("usuarioId"));
			Optional<Carrito> carrito = carritoService.findById(usuario.get().getCarrito().getId());
			List<Producto> lista_productos= carrito.get().getProductos();
			lista_productos.remove(producto.get());
			carrito.get().setProductos(lista_productos);
			carritoService.save(carrito.get());
			
			return "redirect:/carrito";
		}else {
			return "login.jsp";
		}
	}
	
}
