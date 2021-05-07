package com.everis.sumativa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.sumativa.models.Categoria;
import com.everis.sumativa.reporsitories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	public List<Categoria> findAll() {
		// TODO Auto-generated method stub
		return categoriaRepository.findAll();
	}

	public Categoria saveCategoria(Categoria categoria) {
		// TODO Auto-generated method stub
		return categoriaRepository.save(categoria);
	}
}
