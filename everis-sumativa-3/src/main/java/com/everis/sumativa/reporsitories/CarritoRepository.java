package com.everis.sumativa.reporsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.sumativa.models.Carrito;



@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long>{

}
