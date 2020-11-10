package com.sprinboot.servicios.app.seguridad.sis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.empresa.commons.entity.Libro;

public interface LibroDao extends JpaRepository<Libro, Integer> {
	
	@Query("Select u From Libro u where u.estadoLibro=1")
	public List<Libro> findAllEnabeld();  
}
