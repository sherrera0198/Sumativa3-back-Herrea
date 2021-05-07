package com.everis.sumativa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.everis.sumativa.models.Producto;
import com.everis.sumativa.reporsitories.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	ProductoRepository productoRepository;

	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return productoRepository.findAll();
	}

	public Producto saveProducto(Producto producto) {
		// TODO Auto-generated method stub
		return productoRepository.save(producto);
	}

	public Optional<Producto> finByID(Long usuarioID) {
		// TODO Auto-generated method stub
		return productoRepository.findById(usuarioID);
	}

	public void deleteByID(Long id) {
		// TODO Auto-generated method stub
		productoRepository.deleteById(id);
	}

	public Page<Producto> productosPaginados(int numeroPagina, int cantElementos) {
		// TODO Auto-generated method stub
		PageRequest pageRequest = PageRequest.of(numeroPagina, cantElementos);
		
		return productoRepository.findAll(pageRequest);
	}
}
