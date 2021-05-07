package com.everis.sumativa.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.everis.sumativa.models.Categoria;
import com.everis.sumativa.models.Producto;
import com.everis.sumativa.services.CategoriaService;
import com.everis.sumativa.services.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	ProductoService productoService;
	@Autowired
	CategoriaService categoriaService;
	
	private static final int CANT_PRODUCTOS = 4;
	
	@RequestMapping("")
	public String inicioProducto(Model model, HttpSession session) {
		if ((Integer)session.getAttribute("registrado")==1) {
			Page<Producto> productos= productoService.productosPaginados(0, CANT_PRODUCTOS);
			int totalPagina = productos.getTotalPages();
			
			List<Categoria> categorias  = categoriaService.findAll();
			model.addAttribute("totalPagina",totalPagina );
			model.addAttribute("productos", productos);
			model.addAttribute("categorias", categorias);
			return "producto.jsp";
		}else {
			return "login.jsp";
		}
		
		}
	
	@RequestMapping("/insertar")
	public String insertar(@RequestParam("nombre") String nombre,
			@RequestParam("descripcion") String descripcion, 
			@RequestParam("precio") float precio, 
			@RequestParam("categoria") List<Categoria> categoria,
			Model model, HttpSession session) {
		
		
		if ((Integer)session.getAttribute("registrado")==1) {
			Producto producto = new Producto();
			producto.setNombre(nombre);
			producto.setDescripcion(descripcion);
			producto.setPrecio(precio);
			producto.setCategorias(categoria);
			
			producto= productoService.saveProducto(producto);
			
			
			return "redirect:/producto";
		}else {
			return "login.jsp";
		}	
	}
	
	@RequestMapping("/eliminar/{id}")
	public String eliminarProducto(@PathVariable("id") Long id, HttpSession session) {
		if ((Integer)session.getAttribute("registrado")==1) {
			productoService.deleteByID(id);
			return"redirect:/producto";
		}else {
			return "login.jsp";
		}
	}
	
	@RequestMapping("/editar/{id}")
	public String editarProducto(@PathVariable("id") Long id, Model model, HttpSession session) {
		if ((Integer)session.getAttribute("registrado")==1) {
			Optional<Producto> producto= productoService.finByID(id);
			
			List<Categoria> listaCategorias =  categoriaService.findAll();
			
			model.addAttribute("producto", producto);
			model.addAttribute("listaCategorias", listaCategorias);
			return "productoEdit.jsp";
		}else {
			return "login.jsp";
		}
		
	}
	
	@RequestMapping("/update")
	public String updateCarrito(@ModelAttribute("producto") Producto producto, HttpSession session ) {
		if ((Integer)session.getAttribute("registrado")==1) {
			productoService.saveProducto(producto);
			return "redirect:/producto";
		}else {
			return "login.jsp";
		}
	}

	
	@RequestMapping("/buscar")
	public String buscarProducto(@RequestParam("palabra") String palabra,
			Model model, HttpSession session) {
		if ((Integer)session.getAttribute("registrado")==1) {
			Page<Producto> listaProducto= productoService.productosPaginados(0, CANT_PRODUCTOS);
			int totalPagina = listaProducto.getTotalPages();
			
			List<Producto> listaSalida = new ArrayList<Producto>() ;
			for(Producto producto : listaProducto.getContent()){
				if(producto.getDescripcion().toLowerCase().contains(palabra.toLowerCase())|| 
						producto.getNombre().toLowerCase().contains(palabra.toLowerCase())) {
					listaSalida.add(producto);
				}
			}
		
			model.addAttribute("productos",listaSalida);
			model.addAttribute("totalPagina",totalPagina );
			return"buscar.jsp";
		}else {
			return "login.jsp";
		}
	}
	
	
	/**
	 * Paginacion
	 * */
	@RequestMapping("/paginacion/{numeroPagina}")
	public String getProductosPagina(@PathVariable("numeroPagina") int numeroPagina,
			Model model, HttpSession session) {
		
		if ((Integer)session.getAttribute("registrado")==1) {
			//paginas iterable comienzan en 0 cero. 1 a maxPag (ultima pagina)
			Page<Producto> productos= productoService.productosPaginados(numeroPagina-1, CANT_PRODUCTOS);
			//productos.
			int totalPagina= productos.getTotalPages();
			List<Categoria> categorias  = categoriaService.findAll();

			model.addAttribute("categorias", categorias);
			model.addAttribute("totalPagina", totalPagina);
			model.addAttribute("productos", productos);
			
			return "producto.jsp";
		}else {
			return "login.jsp";
		}
	}
	
}
