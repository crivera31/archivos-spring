package com.sprinboot.servicios.app.admin.base.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.app.otros.commons.models.entity.Parametros;


public interface IParametrosDao extends JpaRepository<Parametros , Integer> {
	

	@Query("Select u from Parametros u where u.anioId =?1")
	public Parametros findAllParametrosbyPeriodo(Integer periodoAnio);
	
	@Query("Select u from Parametros u where trim(u.ctaImpHon)=?1 or trim(u.ctaImpCom)=?1 or trim(u.ctaImpVta)=?1 or trim(u.ctaImpRet)=?1")
	public Parametros buscarCuentaIgvEnParametros(String codigv);
}
