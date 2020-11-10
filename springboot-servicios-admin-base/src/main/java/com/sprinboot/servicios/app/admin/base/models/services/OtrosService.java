package com.sprinboot.servicios.app.admin.base.models.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprinboot.servicios.app.admin.base.models.dao.IAnioDao;
import com.sprinboot.servicios.app.admin.base.models.dao.ICentroCostoDao;
import com.sprinboot.servicios.app.admin.base.models.dao.IDocumento;
import com.sprinboot.servicios.app.admin.base.models.dao.IMedioDePago;
import com.sprinboot.servicios.app.admin.base.models.dao.IMesToJobDao;
import com.sprinboot.servicios.app.admin.base.models.dao.IOrigenDao;
import com.sprinboot.servicios.app.admin.base.models.dao.IParametrosAsisDao;
import com.sprinboot.servicios.app.admin.base.models.dao.IParametrosDao;
import com.sprinboot.servicios.app.admin.base.models.dao.IPeriodoAnioDao;
import com.sprinboot.servicios.app.admin.base.models.dao.IPeriodoDao;
import com.sprinboot.servicios.app.admin.base.models.dao.IPlanCuentaDao;
import com.sprinboot.servicios.app.admin.base.models.dao.IEmpresaDao;
import com.sprinboot.servicios.app.admin.base.models.dao.ITipoCambio;
import com.sprinboot.servicios.app.admin.base.models.dao.IUnidadNegocio;
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

@Service
public class OtrosService implements IOtrosServiceImpl {
	
	private Logger logger = LoggerFactory.getLogger(ContAllPersonService.class);

	@Autowired
	private IPeriodoDao periodoDao;
	
	@Autowired
	private IPeriodoAnioDao periodoAnioDao;
	
	@Autowired
	private IMesToJobDao mesToJobDao;
	
	@Autowired
	private IUnidadNegocio unidadNegocioDao;
	
	@Autowired
	private ITipoCambio tipoCambioConect;
	
	@Autowired
	private IDocumento documentoConect;
	
	@Autowired
	private IMedioDePago medioDePagoConect;

	@Autowired
	private IOrigenDao origenDao;
	
	@Autowired
	private IParametrosAsisDao parametrosAsisDao;
	
	@Autowired
	private IParametrosDao parametrosDao;
	
	@Autowired
	private IPlanCuentaDao planCuentaDao;
	
	@Autowired
	private ICentroCostoDao centroCostoDao;
	
	@Autowired
	private IEmpresaDao empresaDao;
	
	@Autowired
	private IAnioDao anioDao;
	
	@Override
	public List<Empresa> findAllRoles() {
		return empresaDao.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public MedioDePago findMedioDePagoById(Integer id) {
		return medioDePagoConect.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<MedioDePago> findAll(Pageable pageable) {
		return medioDePagoConect.findAll(pageable);
	}

	@Override
	@Transactional
	public MedioDePago save(MedioDePago medioDePago) {
		return medioDePagoConect.save(medioDePago);
	}

	@Override
	@Transactional(readOnly=true)
	public TipoCambio findTipoCambioById(Integer id) {
		return tipoCambioConect.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<TipoCambio> findTipoCambioAll(Pageable pageable) {
		return tipoCambioConect.listarTipoCambioOrdenFecha(pageable);
	}

	@Override
	@Transactional
	public TipoCambio save(TipoCambio tipoCambio) {
		return tipoCambioConect.save(tipoCambio);
	}

	@Override
	@Transactional(readOnly=true)
	public Documento findDocumentooById(Integer id) {
		return documentoConect.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly=true)
	public Page<Documento> findDocumentoAll(Pageable pageable) {
		return documentoConect.findAll(pageable);
	}

	@Override
	@Transactional
	public Documento save(Documento documento) {
		return documentoConect.save(documento);
	}

	@Override
	public List<Origen> findAll() {
		return origenDao.findAll();
	}

	@Override
	public List<ParametrosAsis> findAllParametrosAsis(Integer anio) {
		return parametrosAsisDao.findAllParametrosbyPeriodo(anio);
	}

	@Override
	public Parametros findByParametroByAnioPeriodo(Integer periodoId) {
		return parametrosDao.findAllParametrosbyPeriodo(periodoId);
	}
	
	@Override
	public Parametros findByParametrosId(Integer id) {
		return parametrosDao.findById(id).orElse(null);
	}

	@Override
	public List<PlanCuenta> findPlanCuentaByCodCuenta(String term,Integer periodoAnioId) {
		return planCuentaDao.findByCodCuentaStartingWithIgnoreCaseEdit(term,periodoAnioId);
	}

	@Override
	public List<PlanCuenta> buscarPlanCuentaPorCodigo(String cod_cuenta,Integer idperiodoanio) {
		return planCuentaDao.findPlanCuentaByCodCuenta(cod_cuenta.trim(),idperiodoanio);
	}
	

	@Override
	public List<PlanCuenta> buscarPlanCuentaPorId(Integer idplan,Integer idperiodoanio) {
		return planCuentaDao.buscarPorId(idplan,idperiodoanio);
	}

	@Override
	public ParametrosAsis saveParametrosAsis(ParametrosAsis parametrosAsis) {
		return parametrosAsisDao.save(parametrosAsis);
	}

	@Override
	public Parametros saveParametros(Parametros parametros) {
		return parametrosDao.save(parametros);
	}

	@Override
	public ParametrosAsis findByParametrosAsisId(Integer id) {
		return parametrosAsisDao.findById(id).orElse(null);
	}

	@Override
	public UnidadNegocio findByUnidadNegocioId(Integer id) {
		return unidadNegocioDao.findById(id).orElse(null);
	}

	@Override
	public Page<UnidadNegocio> findAllUnidadNegocio(Pageable pageable) {
		return unidadNegocioDao.findByEnabledPaged(pageable);
	}

	@Override
	public UnidadNegocio saveUnidadNegocio(UnidadNegocio unidadNegocio) {
		return unidadNegocioDao.save(unidadNegocio);
	}

	@Override
	public List<CentroCosto> findAllCentroCosto() {
		return centroCostoDao.findAll();
	}

	@Override
	public List<Periodo> findAllPeriodo(String empresa) {
		return periodoDao.listarPeriodo(empresa);
	}

	@Override
	public Periodo savePeriodo(Periodo periodo) {
		return periodoDao.save(periodo);
	}

	@Override
	public Periodo findByPeriodoId(Integer id) {
		return periodoDao.findById(id).orElse(null);
	}

	@Override
	public List<MesToJob> findAllMesToJob() {
		return mesToJobDao.findAll();
	}
	
	
	

	@Override
	public PeriodoAnio savePeriodoAnio(PeriodoAnio periodoAnio) {
		return periodoAnioDao.save(periodoAnio);
	}

	@Override
	public PeriodoAnio findByPeriodoAnioId(Integer id) {
		return periodoAnioDao.findById(id).orElse(null);
	}

	@Override
	public List<PeriodoAnio> findAllPeriodoAnio(String empresa) {
		return periodoAnioDao.allPeriodoAnioByEmpresa(empresa);
	}
	
	@Override
	public List<Anio> findAllAnio() {
		return anioDao.findAllAnio();
	}

	@Override
	public MesToJob saveMesToJob(MesToJob mesToJob) {
		return mesToJobDao.save(mesToJob);
	}

	@Override
	public MesToJob findByMesToJobId(Integer id) {
		return mesToJobDao.findById(id).orElse(null);
	}

	@Override
	public Origen findOrigenById(Integer id) {
		return origenDao.findById(id).orElse(null);
	}

	@Override
	public Periodo findPeriodoById(Integer id) {
		return periodoDao.findById(id).orElse(null);
	}
	
	@Override
	public TipoCambio buscarTipoDeCambioPorFecha(Date fecha) {
		return tipoCambioConect.buscarTipoCambioPorFecha(fecha);
	}
	
	
	@Override
	@Transactional(readOnly=true)
	public List<Documento> findFullDocumentoAll() {
		return documentoConect.findAll();
	}
	
	@Override
	@Transactional(readOnly=true)
	public UnidadNegocio buscarPorCodigo(String cod,Integer idperiodoanio) {
		return unidadNegocioDao.buscarPorCodigo(cod,idperiodoanio);
	};
	
	@Override
	@Transactional(readOnly=true)
	public UnidadNegocio findCentroCostoById(Integer idunidadnegocio) {
		return unidadNegocioDao.buscarPorId(idunidadnegocio);
	};
	
	@Override
	@Transactional(readOnly=true)
	public List<UnidadNegocio> filtrarPorCodigo(String cod,Integer idperiodoanio){
		return unidadNegocioDao.filtrarPorCodigo(cod,idperiodoanio);
	}

	@Override
	@Transactional(readOnly=true)
	public ParametrosAsis findParametrosAsis(Integer origen) {
		return parametrosAsisDao.findParametrosAsis(origen);
	}

	@Override
	@Transactional(readOnly=true)
	public ParametrosAsis getParametrosbyPeriodoAndOrigen(Integer periodoAnio, Integer origen) {
		return parametrosAsisDao.getParametrosbyPeriodoAndOrigen(periodoAnio, origen);
	};
	
	@Override
	@Transactional(readOnly=true)
	public List<Empresa> listarEmpresa(){
		return empresaDao.listarEmpresa();
	};
	
	@Override
	@Transactional(readOnly=true)
	public Anio findAnio(String anio) {
		return anioDao.findAnio(anio);
	};
	
	@Override
	@Transactional(readOnly=true)
	public Parametros buscarCuentaIgvEnParametros(String codigv) {
		return parametrosDao.buscarCuentaIgvEnParametros(codigv.trim());
	};
	
	@Override
	@Transactional(readOnly=true)
	public List<PeriodoAnio> listarPeriodoConAnio() {
		return periodoAnioDao.allEmpresasConAnio();
	}

	@Override
	@Transactional(readOnly=true)
	public List<UnidadNegocio>  listarObras(Integer idperiodoanio) {
		return unidadNegocioDao.buscarPorCodigo(idperiodoanio);
	};
	
	@Override
	@Transactional(readOnly=true)
	public PeriodoAnio buscarPeriodoAnioPorNomModulo(String nomModulo) {
		return periodoAnioDao.busquedaDePeriodoAnioPorModuloEmppresa(nomModulo);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<UnidadNegocio>  listarObrasAdmin(Integer idperiodoanio) {
		return unidadNegocioDao.buscarPorObraAndAdministrativo(idperiodoanio);
	};
}
