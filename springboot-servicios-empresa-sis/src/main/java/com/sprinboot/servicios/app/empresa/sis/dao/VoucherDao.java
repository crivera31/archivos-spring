package com.sprinboot.servicios.app.empresa.sis.dao;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.empresa.commons.entity.Asiento;
import com.sprinboot.servicios.empresa.commons.entity.RegistroLibros;
import com.sprinboot.servicios.empresa.commons.entity.Voucher;

public interface VoucherDao extends JpaRepository<Voucher, Integer>{
	
	@Query("Select u From Voucher u where u.asiento.id=?1 and u.estadoVoucher=1")
	public List<Voucher>listarVouchersByAsiento(Integer asiento); 
	
	@Query("Select SUM(CASE WHEN u.abreNomMoneda=?1 THEN u.debe ELSE (CASE WHEN u.debe IS NOT null THEN u.equivalente END)END)  From Voucher u where u.asiento.idPeriodo=?2 and u.estadoVoucher=1")
	public BigDecimal totalLibroDebeDiarioMensual(String moneda, Integer periodo );
	
	@Query("Select SUM(CASE WHEN u.abreNomMoneda=?1 THEN u.debe ELSE (CASE WHEN u.debe IS NOT null THEN u.equivalente END) END)  From Voucher u where u.asiento.idPeriodo=?2 and u.estadoVoucher=1 and u.asiento.codOrigen=?3")
	public BigDecimal totalLibroDebeDiarioMensualConOrigen(String moneda, Integer periodo , String codOrigen);
	
	@Query("Select SUM(CASE WHEN u.abreNomMoneda=?1 THEN u.haber ELSE (CASE WHEN u.haber IS NOT null THEN u.equivalente END) END) From Voucher u where u.asiento.idPeriodo=?2 and u.estadoVoucher=1")
	public BigDecimal totalHaberLibroDiarioMensual(String moneda, Integer periodo );
	
	@Query("Select SUM(CASE WHEN u.abreNomMoneda=?1 THEN u.haber ELSE (CASE WHEN u.haber IS NOT null THEN u.equivalente END) END) From Voucher u where u.asiento.idPeriodo=?2 and u.estadoVoucher=1 and u.asiento.codOrigen=?3")
	public BigDecimal totalHaberLibroDiarioMensualConOrigen(String moneda, Integer periodo , String codOrigen);
	
	
	@Query("Select SUM(CASE WHEN u.abreNomMoneda=?1 THEN u.debe ELSE (CASE WHEN u.debe IS NOT null THEN u.equivalente END) END) From Voucher u where u.asiento.anio=?3 and u.asiento.codMes >= 0  and u.asiento.codMes<=?2  and u.estadoVoucher=1")
	public BigDecimal totaDebelibroDiarioAcumulado(String moneda, Integer mes , String anio); 
	
	@Query("Select SUM(CASE WHEN u.abreNomMoneda=?1 THEN u.debe ELSE (CASE WHEN u.debe IS NOT null THEN u.equivalente END) END) From Voucher u where u.asiento.anio=?3 and u.asiento.codMes >= 0  and u.asiento.codMes<=?2  and u.estadoVoucher=1 and u.asiento.codOrigen=?4")
	public BigDecimal totaDebelibroDiarioAcumuladoConOrigen(String moneda, Integer mes , String anio,  String codOrigen); 
	
	@Query("Select SUM(CASE WHEN u.abreNomMoneda=?1 THEN u.haber ELSE (CASE WHEN u.haber IS NOT null THEN u.equivalente END) END) From Voucher u where u.asiento.anio=?3 and  u.asiento.codMes >= 0  and u.asiento.codMes<=?2  and u.estadoVoucher=1")
	public BigDecimal totaHaberlibroDiarioAcumulado(String moneda, Integer mes , String anio);
	
	@Query("Select SUM(CASE WHEN u.abreNomMoneda=?1 THEN u.haber ELSE (CASE WHEN u.haber IS NOT null THEN u.equivalente END) END) From Voucher u where u.asiento.anio=?3 and  u.asiento.codMes >= 0  and u.asiento.codMes<=?2  and u.estadoVoucher=1 and u.asiento.codOrigen=?4")
	public BigDecimal totaHaberlibroDiarioAcumuladoConOrigen(String moneda, Integer mes , String anio,  String codOrigen);
	
	@Query("Select u From Voucher u where u.id=?1 and u.estadoVoucher=1")
	public Voucher obtenerId(Integer id); 
		
	@Query("SELECT u FROM Voucher u WHERE u.asiento.codMes=?2  AND u.asiento.anio=?3 AND trim(both from u.codPlan)=?1 AND u.estadoVoucher=1 AND u.asiento.estadoAsiento=1")
	public List<Voucher>  listarParaLibroMayorPorCuenta(String codcuenta, Integer codmes , String anio); 
	
	//consorcios
	@Query("SELECT u FROM Voucher u WHERE u.codUnidadNegocio='0001'  AND u.estadoVoucher=1 AND u.asiento.estadoAsiento=1")
	public List<Voucher>  traerConsorcios(); 
	
	@Query("SELECT u FROM Voucher u WHERE trim(both from u.codPlan)='40111' AND u.estadoVoucher=1 AND u.asiento.estadoAsiento=1 AND u.asiento.id=?1")
	public Voucher  colcoarigvunidad(Integer idasiento); 
	
	@Query("SELECT u FROM Voucher u WHERE trim(both from u.codPlan)='1673' AND u.estadoVoucher=1 AND u.asiento.estadoAsiento=1 AND u.asiento.id=?1")
	public Voucher  colcoarigvPoraplicarunidad(Integer idasiento); 
	
	@Query("SELECT u FROM Voucher u WHERE trim(both from u.serieNumero)=?1 AND u.codDocumento=?2 AND trim(both from u.codRuc)=?3 AND u.estadoVoucher=1 AND u.asiento.estadoAsiento=1 AND trim(both from u.codPlan)='1673'")
	public List<Voucher>  buscarAsientosIgvPOraplicar(String serieNumero , String doc, String ruc);
	
	@Query("SELECT u FROM Voucher u WHERE u.asiento.codMes=?2  AND u.asiento.anio=?3 AND trim(both from u.codPlan)=?1 AND u.asiento.idPeriodo=?4 AND u.estadoVoucher=1 AND u.asiento.estadoAsiento=1")
	public List<Voucher>  listarParaHojaTrabajoMensual(String codcuenta, Integer codmes , String anio, Integer periodo); 
	
	@Query("SELECT u FROM Voucher u WHERE u.asiento.codMes >= 0  AND u.asiento.codMes<=?2  AND u.asiento.anio=?3 AND trim(both from u.codPlan)=?1 AND u.estadoVoucher=1 AND u.asiento.estadoAsiento=1")
	public List<Voucher>  listarParaHojaTrabajoAcumulado(String codcuenta, Integer codmes , String anio);
	
	@Query("SELECT u FROM Voucher u WHERE u.asiento.codMes=?2  AND u.asiento.anio=?3 AND trim(both from u.codPlan)=?1 AND u.asiento.idPeriodo=?4 AND u.estadoVoucher=1 AND u.asiento.estadoAsiento=1")
	public List<Voucher>  listarParaLibroMayorSisMensual(String codcuenta, Integer codmes , String anio, Integer periodo);
	
	@Query("SELECT u FROM Voucher u WHERE u.asiento.codMes >= 0  AND u.asiento.codMes<=?2  AND u.asiento.anio=?3 AND trim(both from u.codPlan)=?1 AND u.estadoVoucher=1 AND u.asiento.estadoAsiento=1")
	public List<Voucher>  listarParaLibroMayorSisAcumulado(String codcuenta, Integer codmes , String anio);
	
}
