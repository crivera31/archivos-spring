package com.sprinboot.servicios.app.empresa.oriana.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.empresa.commons.entity.Asiento;

public interface AsientoDao extends JpaRepository<Asiento, Integer>{
	
	@Query("Select u From Asiento u where u.codOrigen=?1 and u.numAsiento=?2 and u.idPeriodo=?3 and u.estadoAsiento=1")
	public Asiento findByParametros(String origen,Integer asiento, Integer periodo); 

	@Query("Select u From Asiento u where u.codOrigen=?1 and u.idPeriodo=?2 and u.estadoAsiento=1")
	public List<Asiento> findUltimoAsiento(String origen, Integer periodo); 
	
	@Query("Select u From Asiento u where u.id=?1 and u.estadoAsiento=1")
	public Asiento findOneEnabled(Integer id); 

	@Query("Select u From Asiento u where u.idPeriodo=?1 and u.estadoAsiento=1")
	public List<Asiento> libroDiarioMensual(Integer periodo );

	@Query("Select u From Asiento u where u.idPeriodo=?1 and u.codOrigen=?2 and u.estadoAsiento=1")
	public List<Asiento> libroDiarioMensualConOrigen(Integer periodo , String codOrigen);
	
	@Query("Select u From Asiento u where u.anio=?2 and u.codMes > 0  and u.codMes<=?1 and u.estadoAsiento=1")
	public List<Asiento> libroDiarioAcumulado( Integer mes , String anio);
	
	@Query("Select u From Asiento u where u.anio=?2 and u.codMes > 0  and u.codMes<=?1  and u.estadoAsiento=1 and u.codOrigen=?3")
	public List<Asiento> libroDiarioAcumuladoConOrigen( Integer mes , String anio , String codOrigen);
	 
	@Query("SELECT u FROM Asiento u WHERE u.codMes=?1 AND u.anio=?2 AND u.estadoAsiento=1 AND u.codOrigen=?3")
	public List<Asiento> libroDiarioSunat(Integer codmes ,String anio, String codorigen); 
	
	@Query("Select u From Asiento u where u.anio=?2 and u.codMes >= 0  and u.codMes<=?1  and u.estadoAsiento=1")
	public List<Asiento> listaDeConsistencias(Integer mes , String anio); 
	
	@Query("Select u From Asiento u where u.anio=?2 and u.codMes=?1  and u.estadoAsiento=1")
	public List<Asiento> listaDeConsistenciasPorMes(Integer mes , String anio); 
	
	@Query(value="{call VERIFICAR_DUPLICIDAD_LIBROS()}", nativeQuery= true)
	public List<Object[]> validarSP(); 

	@Query("Select u.numComprobante From Asiento u where u.anio=?1 and u.estadoAsiento=1  and u.numComprobante>0 GROUP BY u.numComprobante ORDER BY u.numComprobante")
	public List<Object[]> listultimocomprobante(String anio); 
	
	@Query("Select u From Asiento u where u.numComprobante=?1 and u.anio=?2 and u.estadoAsiento=1")
	public List<Asiento> listarAsientosPorNumComprobante( Integer numcomprobante , String anio);
	
	
}
