package com.sprinboot.servicios.app.admin.base.models.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.sprinboot.servicios.app.otros.commons.models.entity.Anio;
import com.sprinboot.servicios.app.otros.commons.models.entity.CentroCosto;
import com.sprinboot.servicios.app.otros.commons.models.entity.Documento;
import com.sprinboot.servicios.app.otros.commons.models.entity.Empresa;
import com.sprinboot.servicios.app.otros.commons.models.entity.MedioDePago;
import com.sprinboot.servicios.app.otros.commons.models.entity.MesToJob;
import com.sprinboot.servicios.app.otros.commons.models.entity.Origen;
import com.sprinboot.servicios.app.otros.commons.models.entity.Parametros;
import com.sprinboot.servicios.app.otros.commons.models.entity.ParametrosAsis;
import com.sprinboot.servicios.app.otros.commons.models.entity.Periodo;
import com.sprinboot.servicios.app.otros.commons.models.entity.PeriodoAnio;
import com.sprinboot.servicios.app.otros.commons.models.entity.PlanCuenta;
import com.sprinboot.servicios.app.otros.commons.models.entity.TipoCambio;
import com.sprinboot.servicios.app.otros.commons.models.entity.UnidadNegocio;



public interface IOtrosServiceImpl {

	public MedioDePago findMedioDePagoById(Integer id);
	public Page<MedioDePago> findAll(Pageable pageable);
	public MedioDePago save(MedioDePago medioDePago);
	
	public TipoCambio findTipoCambioById(Integer id);
	public Page<TipoCambio> findTipoCambioAll(Pageable pageable);
	public TipoCambio save(TipoCambio tipoCambio);
	
	public Documento findDocumentooById(Integer id);
	public Page<Documento> findDocumentoAll(Pageable pageable);
	public Documento save(Documento documento);
	
	public List<Origen> findAll();
	public Origen findOrigenById(Integer id);
	
	public List<ParametrosAsis> findAllParametrosAsis(Integer periodo);
	public ParametrosAsis saveParametrosAsis(ParametrosAsis parametrosAsis);
	public ParametrosAsis findByParametrosAsisId(Integer id);
	public ParametrosAsis findParametrosAsis(Integer origen);
	public ParametrosAsis getParametrosbyPeriodoAndOrigen(Integer periodoAnio,Integer origen);

	
	public Parametros findByParametroByAnioPeriodo(Integer id);
	public Parametros saveParametros(Parametros parametros);
	public Parametros findByParametrosId(Integer id);

	
	public List<PlanCuenta> buscarPlanCuentaPorCodigo(String cod_cuenta,Integer idperiodoanio);
	public List<PlanCuenta> buscarPlanCuentaPorId(Integer idplan,Integer idperiodoanio) ;
	
	public List<PlanCuenta> findPlanCuentaByCodCuenta(String term,Integer idperiodoanio);
	
	public UnidadNegocio findByUnidadNegocioId(Integer id);
	
	@Query("select u from UnidadNegocio u where u.enabled = 1")
	public Page<UnidadNegocio> findAllUnidadNegocio(Pageable pageable);
	public UnidadNegocio saveUnidadNegocio(UnidadNegocio unidadNegocio);
	
	public List<CentroCosto> findAllCentroCosto();

	public List<Periodo> findAllPeriodo(String empresa);
	public Periodo findPeriodoById(Integer id);
	
	public Periodo savePeriodo(Periodo periodo);
	public Periodo findByPeriodoId(Integer id);
	
	public List<MesToJob> findAllMesToJob();
	public MesToJob saveMesToJob(MesToJob mesToJob);
	public MesToJob findByMesToJobId(Integer id);
	
	public PeriodoAnio savePeriodoAnio(PeriodoAnio periodoAnio);
	public PeriodoAnio findByPeriodoAnioId(Integer id);
	public List<PeriodoAnio> findAllPeriodoAnio(String empresa);	
	
	public TipoCambio buscarTipoDeCambioPorFecha(Date fecha);
	public List<Documento> findFullDocumentoAll();

	public UnidadNegocio buscarPorCodigo(String cod,Integer idperiodoanio);
	public List<UnidadNegocio> filtrarPorCodigo(String cod,Integer idperiodoanio);
	
	public List<Empresa> findAllRoles();
	public List<Empresa> listarEmpresa();
	public List<Anio> findAllAnio() ;
	public Anio findAnio(String anio);
	public Parametros buscarCuentaIgvEnParametros(String codigv) ;
	public List<PeriodoAnio> listarPeriodoConAnio();
	public UnidadNegocio findCentroCostoById(Integer idunidadnegocio);
	public List<UnidadNegocio>  listarObras(Integer idperiodoanio) ;
	public PeriodoAnio buscarPeriodoAnioPorNomModulo(String nomModulo);
	public List<UnidadNegocio>  listarObrasAdmin(Integer idperiodoanio) ;
}
