package com.sprinboot.servicios.app.empresa.oriana.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.empresa.commons.entity.Comprobante;
import com.sprinboot.servicios.empresa.commons.entity.ComprobanteDet;

public interface ComprobanteDetDao extends JpaRepository<ComprobanteDet, Integer> {

	
	@Query("Select u From ComprobanteDet u where u.asiento.id = ?1 and  u.comprobante.id = ?2")
	public ComprobanteDet buscar(Integer idasiento , Integer idcomprobante ); 
		
}
