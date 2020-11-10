package com.sprinboot.servicios.app.empresa.oriana.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.empresa.commons.entity.RegistroDocumentos;

public interface RegistroDocumentosDao extends JpaRepository<RegistroDocumentos, Integer>{

	@Query("SELECT u FROM RegistroDocumentos u WHERE u.voucher.codRuc=?1 AND u.voucher.asiento.anio=?3 AND u.voucher.codPlan=?2  AND u.estadoRegDoc=1 AND u.cancelado=0 AND u.voucher.estadoVoucher=1 AND u.voucher.asiento.estadoAsiento=1")
	public List<RegistroDocumentos> listarByPersona(String codigo,String codPlan,String anio); 
	
	@Query("SELECT u FROM RegistroDocumentos u WHERE u.id=?1  AND u.estadoRegDoc=1 AND u.cancelado=0 AND u.voucher.estadoVoucher=1 AND u.voucher.asiento.estadoAsiento=1")
	public RegistroDocumentos buscarReg(Integer id); 
	
	//@Query("SELECT u FROM RegistroDocumentos u WHERE u.voucher.fechaVencimiento=?1 AND u.voucher.codDocumento=?2 AND u.voucher.serieNumero=?3 AND u.voucher.abreNomMoneda=?4 AND u.voucher.fechaEmision=?5 AND u.estadoRegDoc=1 AND u.cancelado=0 AND u.voucher.estadoVoucher=1 AND u.voucher.asiento.estadoAsiento=1")
	@Query("SELECT u FROM RegistroDocumentos u WHERE u.voucher.codDocumento=?1 AND u.voucher.serieNumero=?2 AND u.voucher.abreNomMoneda=?3 AND u.estadoRegDoc=1 AND u.cancelado=0 AND u.voucher.estadoVoucher=1 AND u.voucher.asiento.estadoAsiento=1")
	public List<RegistroDocumentos> listarRegistroDocumentos(String codDoc, String serie, String codMoneda); 
	
	//@Query("SELECT u FROM RegistroDocumentos u WHERE u.voucher.fechaVencimiento=?1 AND u.voucher.codDocumento=?2 AND u.voucher.serieNumero=?3 AND u.voucher.abreNomMoneda=?4 AND u.voucher.fechaEmision=?5 AND u.estadoRegDoc=1 AND u.voucher.estadoVoucher=1 AND u.voucher.asiento.estadoAsiento=1")
	@Query("SELECT u FROM RegistroDocumentos u WHERE u.voucher.codDocumento=?1 AND u.voucher.serieNumero=?2 AND u.voucher.abreNomMoneda=?3 AND u.estadoRegDoc=1 AND u.voucher.estadoVoucher=1 AND u.voucher.asiento.estadoAsiento=1")
	public List<RegistroDocumentos> listarRegistroDocumentosFull(String codDoc, String serie, String codMoneda); 
	
	@Query("SELECT u FROM RegistroDocumentos u WHERE u.id=?1  AND u.estadoRegDoc=1 AND u.voucher.estadoVoucher=1 AND u.voucher.asiento.estadoAsiento=1")
	public RegistroDocumentos buscarRegAll(Integer id); 
}
