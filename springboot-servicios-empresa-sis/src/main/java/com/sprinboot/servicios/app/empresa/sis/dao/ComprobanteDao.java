package com.sprinboot.servicios.app.empresa.sis.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.empresa.commons.entity.Comprobante;

public interface ComprobanteDao extends JpaRepository<Comprobante, Integer> {

	@Query("Select u From Comprobante u where u.numComprobante=?1 and u.estadoComprobante=1")
	public Comprobante buscarComprobante(Integer numComprobante); 
	
	@Query("Select u From Comprobante u where u.numComprobante=?1 and u.anio =?2 and u.estadoComprobante=1")
	public Comprobante buscarComprobanteAnio(Integer numComprobante,String anio); 
	
	@Query("Select u From Comprobante u where u.anio =?1 and u.estadoComprobante=1")
	public List<Comprobante> listarComprobante(String anio); 
	
	@Query("Select u From Comprobante u where u.numComprobante < ?1  and u.anio =?2 and u.estadoComprobante=1 ORDER BY u.numComprobante")
	public List<Comprobante> ultimaFecha(Integer numcomprobante , String anio); 
}
