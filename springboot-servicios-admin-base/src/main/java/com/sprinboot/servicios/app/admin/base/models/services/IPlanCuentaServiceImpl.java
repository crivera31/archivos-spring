package com.sprinboot.servicios.app.admin.base.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.app.admin.base.dto.CuentaDetalladaDto;
import com.sprinboot.servicios.app.admin.base.dto.PlanContableDto;
import com.sprinboot.servicios.app.otros.commons.models.entity.ClasificacionBienes;
import com.sprinboot.servicios.app.otros.commons.models.entity.Empresa;
import com.sprinboot.servicios.app.otros.commons.models.entity.Moneda;
import com.sprinboot.servicios.app.otros.commons.models.entity.NivelCuenta;
import com.sprinboot.servicios.app.otros.commons.models.entity.OpcionPlanCuenta;
import com.sprinboot.servicios.app.otros.commons.models.entity.PlanCuenta;
import com.sprinboot.servicios.app.otros.commons.models.entity.TipoAnalisis;
import com.sprinboot.servicios.app.otros.commons.models.entity.TipoCuenta;

public interface IPlanCuentaServiceImpl {
	
	@Query("select u from PlanCuenta u where u.enabled = 1")
	public List<PlanCuenta> findByEnabledPaged();
	
	
	public Page<PlanCuenta> findByEnabledPagedPorEmpresa(Integer periodoAnioId,Long empresaId,Pageable pageable);

	
	public List<NivelCuenta> findNivelCuentaAll();
	
	public List<OpcionPlanCuenta> findOpcionPlanCuentaAll();
	
	public List<TipoAnalisis> findTipoAnalisisAll();
	
	public List<TipoCuenta> findTipoCuentaAll();
	
	public List<Moneda> findMonedaAll();
	
	public Moneda findMonedaById(Integer id);
	
	public PlanCuenta save(PlanCuenta planCuenta);
	
	public PlanCuenta findById(Integer id);
	
	public PlanCuenta buscarPlanCuentaPorCod(String cod,Integer idperiodoanio);
	public List<PlanCuenta> buscarPlanCuentaPorCodigo(String cod_cuenta,Integer periodoanioid);
	public List<PlanCuenta> buscarPlanCuentaPorCodigoEmpresa(String cod_cuenta,Integer periodoanioid);
	public List<PlanCuenta> filtrarPorCodigoLikeEmpresa(String cod_cuenta, Integer periodoanioid) ;
	public Empresa obtenerEmpresaPorNom(String empresa);
	public List<ClasificacionBienes> listarClasificacionBienes() ;
	public List<PlanCuenta> listarPlanContablePorEmpresa(String anio, Integer periodoanioid);
	public List<PlanContableDto> listarPlanContablePorEmpresaDto(String anio, Integer periodoanioid);
	public  void planOriginal(Integer periodoanioid);
	public void planPorEmpresa(Integer periodoanioid);
	public List<CuentaDetalladaDto> listarPlanContableDetalladoOrdenado(String anio, Integer periodoanioid,String nivelCuenta);

}
