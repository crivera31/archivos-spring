package com.sprinboot.servicios.app.admin.base.models.dao;


import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.app.otros.commons.models.entity.TipoCambio;


public interface ITipoCambio extends JpaRepository<TipoCambio,Integer> {

	@Query("select u from TipoCambio u where u.fecha = ?1")
	public TipoCambio buscarTipoCambioPorFecha(Date fecha);
	
	@Query("select u from TipoCambio u ORDER BY u.fecha ASC")
	public Page<TipoCambio> listarTipoCambioOrdenFecha(Pageable pageable);
}
