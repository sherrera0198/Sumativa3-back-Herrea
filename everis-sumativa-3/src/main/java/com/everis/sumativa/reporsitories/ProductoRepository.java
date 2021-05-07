package com.everis.sumativa.reporsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.everis.sumativa.models.Producto;

@Repository
public interface ProductoRepository  extends JpaRepository<Producto, Long>{

}
