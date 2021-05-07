package com.everis.sumativa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.sumativa.models.Carrito;
import com.everis.sumativa.reporsitories.CarritoRepository;

@Service
public class CarritoService {

	@Autowired
	CarritoRepository carritoRepository;

	public List<Carrito> findAll() {
		// TODO Auto-generated method stub
		return carritoRepository.findAll();
	}

	public Carrito save(Carrito carrito) {
		// TODO Auto-generated method stub
		return carritoRepository.save(carrito);
	}

	public Optional<Carrito> findById(Long id) {
		// TODO Auto-generated method stub
		return carritoRepository.findById(id);
	}
	
}
