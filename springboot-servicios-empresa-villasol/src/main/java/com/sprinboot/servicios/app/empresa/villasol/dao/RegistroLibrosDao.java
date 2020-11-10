package com.sprinboot.servicios.app.empresa.villasol.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.empresa.commons.entity.RegistroLibros;

public interface RegistroLibrosDao extends JpaRepository<RegistroLibros, Integer>{

	@Query("SELECT u FROM RegistroLibros u WHERE u.voucher.fechaEmision=?1 AND u.voucher.fechaVencimiento=?2 AND u.voucher.codDocumento=?3 AND u.voucher.serieNumero=?4 AND u.voucher.codRuc=?5 AND u.voucher.estadoVoucher=1 AND u.voucher.asiento.estadoAsiento=1 AND u.libro.codLibro='00' AND u.estadoRegLib=1")
	public List<RegistroLibros> listarRegistroLibrosPorFiltro(Date fecha, Date venc, String doc, String numero, String codRuc); 
	
	@Query("SELECT u FROM RegistroLibros u WHERE u.voucher.codDocumento=?1 AND u.voucher.asiento.idPeriodo=?2 AND u.voucher.asiento.codMes=?3 AND u.libro.codLibro=?5 AND u.voucher.asiento.anio=?4 AND u.voucher.codPlan=?6 AND u.estadoRegLib=1 AND u.voucher.estadoVoucher=1 AND u.voucher.asiento.estadoAsiento=1 ORDER BY u.voucher.fechaEmision ASC")
	public List<RegistroLibros> listarRegistroDeCompras(String doc, Integer idperiodo, Integer codmes , String anio,String codLibro,String codPlanParametro); 
	
	@Query("Select u From RegistroLibros u where u.voucher.serieNumero=?1 and u.voucher.codDocumento<>'25' and u.voucher.codRuc=?2 and u.voucher.codDocumento=?3 and u.voucher.estadoVoucher=1 AND u.estadoRegLib=1 AND u.voucher.asiento.estadoAsiento=1 AND u.libro.codLibro<>'00'")
	public List<RegistroLibros> buscarFactura(String serie,String ruc,String codDocumento); 
	
	@Query("SELECT u FROM RegistroLibros u WHERE u.voucher.codDocumento=?1  AND u.voucher.asiento.codMes=?2 AND u.libro.codLibro=?4 AND u.voucher.asiento.anio=?3 AND u.voucher.codPlan=?5 AND u.voucher.id=?6 AND u.estadoRegLib=1 AND u.voucher.estadoVoucher=1 AND u.voucher.asiento.estadoAsiento=1")
	public List<RegistroLibros>  buscarFacturaEnLibros(String doc, Integer codmes , String anio,String codLibro,String codPlanParametro,Integer idvoucher); 
	
	@Query("SELECT u FROM RegistroLibros u WHERE u.voucher.codDocumento=?1 AND u.voucher.asiento.idPeriodo=?2 AND u.voucher.asiento.codMes=?3 AND (u.libro.codLibro=?5 OR u.libro.codLibro=?7)  AND u.voucher.asiento.anio=?4 AND u.voucher.codPlan=?6 AND u.voucher.codUnidadNegocio=?8 AND u.estadoRegLib=1 AND u.voucher.estadoVoucher=1 AND u.voucher.asiento.estadoAsiento=1 ORDER BY u.voucher.fechaEmision ASC")
	public List<RegistroLibros> listarRegistrosLibrosAuxiliares(String doc, Integer idperiodo, Integer codmes , String anio,String codLibro,String codPlanParametro , String codauxiliar , String codObra); 
	
	@Query("SELECT u FROM RegistroLibros u WHERE u.voucher.asiento.idPeriodo=?1 AND u.voucher.asiento.codMes=?2 AND u.libro.codLibro=?4 AND u.voucher.asiento.anio=?3 AND u.voucher.codPlan=?5 AND u.estadoRegLib=1 AND u.voucher.estadoVoucher=1 AND u.voucher.asiento.estadoAsiento=1 ORDER BY u.voucher.fechaEmision ASC")
	public List<RegistroLibros> listarRegistroSistemaMensualFecha(Integer idperiodo, Integer codmes , String anio,String codLibro,String codPlanParametro); 
	
	@Query("SELECT u FROM RegistroLibros u WHERE (u.voucher.asiento.codMes > 0  AND u.voucher.asiento.codMes <= ?1) AND u.libro.codLibro=?3 AND u.voucher.asiento.anio=?2 AND u.voucher.codPlan=?4 AND u.estadoRegLib=1 AND u.voucher.estadoVoucher=1 AND u.voucher.asiento.estadoAsiento=1 ORDER BY u.voucher.fechaEmision ASC")
	public List<RegistroLibros> listarRegistroSistemaAcumuladoFecha(Integer codmes , String anio,String codLibro,String codPlanParametro);
	
	
	
}
