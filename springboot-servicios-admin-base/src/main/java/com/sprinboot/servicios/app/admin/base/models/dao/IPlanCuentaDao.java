package com.sprinboot.servicios.app.admin.base.models.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.app.otros.commons.models.entity.PlanCuenta;


public interface IPlanCuentaDao extends JpaRepository<PlanCuenta, Integer> {
	
	@Query("Select u from PlanCuenta u  where u.codCuenta like ?1% and u.periodoAnio.id=?2  and u.enabled = 1 and u.periodoAnio.anio.estado=1")
	public List<PlanCuenta> findByCodCuentaStartingWithIgnoreCaseEdit(String term,Integer periodoanio);
	
	@Query("select u from PlanCuenta u where u.enabled = 1 and u.id=?1 ")
	public PlanCuenta findByCuenta(Integer id);
		
	@Query("select u from PlanCuenta u where u.enabled = 1 and u.periodoAnio.anio.estado=1")
	public List<PlanCuenta> findByEnabledPaged();
	
	@Query("select u from PlanCuenta u where u.enabled = 1 and u.periodoAnio.id=:periodoAnioId and u.periodoAnio.empresa.id=:empresaId and  u.periodoAnio.anio.estado=1")
	public Page<PlanCuenta> findByEnabledPagedEmpresa(Integer periodoAnioId,Long empresaId,Pageable pageable);
	
	@Query("select u from PlanCuenta u where u.codCuenta=?1  and u.periodoAnio.id=?2  and u.enabled = 1 and u.periodoAnio.anio.estado=1")
	public PlanCuenta buscarPlanCuentaPorCod(String cod,Integer idperiodoanio);
	
	@Query("select u from PlanCuenta u where trim(u.codCuenta)=?1  and u.periodoAnio.id=?2 and u.enabled = 1 and u.periodoAnio.anio.estado=1")
	public List<PlanCuenta> findPlanCuentaByCodCuenta(String cod_cuenta,Integer idperiodoanio);
		
	@Query("select u from PlanCuenta u where u.id=?1 and u.periodoAnio.id=?2 and u.enabled = 1 and u.periodoAnio.anio.estado=1")
	public List<PlanCuenta> buscarPorId(Integer idplan,Integer idperiodoanio);
	
	@Query("select u from PlanCuenta u where u.codCuenta=?1 and u.periodoAnio.id=?2 and u.enabled = 1 and u.periodoAnio.anio.estado=1")
	public List<PlanCuenta> buscarPorCodAndEmpresa(String codplan,Integer idperiodoanio);
	
	@Query("Select u from PlanCuenta u  where u.codCuenta like ?1% and u.periodoAnio.id=?2  and u.enabled = 1 and u.periodoAnio.anio.estado=1")
	public List<PlanCuenta> filtrarPorCodigoLikeEmpresa(String term ,Integer idperiodoanio);
	
	@Query("Select u from PlanCuenta u  where u.periodoAnio.anio.nomAnio=?2 and u.periodoAnio.id=?1 and u.enabled = 1 and u.periodoAnio.anio.estado=1")
	public List<PlanCuenta> listarPlanContablePorEmpresa(Integer idperiodoanio,String anio);
	
	@Query("Select u from PlanCuenta u  where u.periodoAnio.empresa.id=3 and u.enabled = 1 and u.periodoAnio.anio.estado=1")
	public List<PlanCuenta> listarPlanOriginal();
	
	@Query("Select u from PlanCuenta u  where u.periodoAnio.id=?1 and u.enabled = 1 and u.periodoAnio.anio.estado=1")
	public List<PlanCuenta> listarPlanPorEmpresa(Integer idperiodoanio);
	
	@Query("Select u from PlanCuenta u  where u.periodoAnio.anio.nomAnio=?2 and u.periodoAnio.id=?1 and  u.nivelCuenta.abreNomNivelCuenta=?3 and u.enabled = 1 and u.periodoAnio.anio.estado=1")
	public List<PlanCuenta> listarPlanContablePorNivelPorEmpresa(Integer idperiodoanio,String anio,String nivelcuenta);
	
}
