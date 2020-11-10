package com.sprinboot.servicios.app.oriplan.sis.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.oriplan.commons.entity.TareoCab;
import com.sprinboot.servicios.oriplan.commons.entity.TasasPensiones;
import com.sprinboot.servicios.oriplan.commons.entity.Trabajadores;

public interface TrabajadoresDao  extends JpaRepository<Trabajadores, Integer>{

	@Query("Select u From Trabajadores u where u.enabled=1 and u.activo=1 ")
	public List<Trabajadores> listarActivos(); 
	
	@Query("Select u From Trabajadores u where u.enabled=1 and u.activo=0 ")
	public List<Trabajadores> listarBajas(); 
	
	@Query("Select u From Trabajadores u where u.dni=?1 and u.fechaIngreso=?2 and u.enabled=1 ")
	public List<Trabajadores> buscarSinRepeticion(String dni, Date fechaIngreso); 
	
	@Query("Select u From Trabajadores u where u.dni=?1 and u.fechaIngreso=?2 and  u.activo=1 and u.enabled=1 ")
	public List<Trabajadores> buscar(String dni, Date fechaIngreso); 
	
	@Query("Select u From Trabajadores u where u.dni=?1 and u.activo=1  and u.enabled=1 ")
	public List<Trabajadores> buscarTrabajadorActivo(String dni);



}
