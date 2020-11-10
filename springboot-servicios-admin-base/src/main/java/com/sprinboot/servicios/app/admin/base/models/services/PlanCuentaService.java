package com.sprinboot.servicios.app.admin.base.models.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprinboot.servicios.app.admin.base.dto.CuentaDetalladaDto;
import com.sprinboot.servicios.app.admin.base.dto.PlanContableDto;
import com.sprinboot.servicios.app.admin.base.funciones.Funciones;
import com.sprinboot.servicios.app.admin.base.models.dao.ClasifiacionBIenesDao;
import com.sprinboot.servicios.app.admin.base.models.dao.IMonedaDao;
import com.sprinboot.servicios.app.admin.base.models.dao.INivelCuentaDao;
import com.sprinboot.servicios.app.admin.base.models.dao.IOpcionCuentaDao;
import com.sprinboot.servicios.app.admin.base.models.dao.IPeriodoAnioDao;
import com.sprinboot.servicios.app.admin.base.models.dao.IPlanCuentaDao;
import com.sprinboot.servicios.app.admin.base.models.dao.IEmpresaDao;
import com.sprinboot.servicios.app.admin.base.models.dao.ITipoAnalisis;
import com.sprinboot.servicios.app.admin.base.models.dao.ITipoCuenta;
import com.sprinboot.servicios.app.otros.commons.models.entity.ClasificacionBienes;
import com.sprinboot.servicios.app.otros.commons.models.entity.Empresa;
import com.sprinboot.servicios.app.otros.commons.models.entity.Moneda;
import com.sprinboot.servicios.app.otros.commons.models.entity.NivelCuenta;
import com.sprinboot.servicios.app.otros.commons.models.entity.OpcionPlanCuenta;
import com.sprinboot.servicios.app.otros.commons.models.entity.PeriodoAnio;
import com.sprinboot.servicios.app.otros.commons.models.entity.PlanCuenta;
import com.sprinboot.servicios.app.otros.commons.models.entity.TipoAnalisis;
import com.sprinboot.servicios.app.otros.commons.models.entity.TipoCuenta;


@Service
public class PlanCuentaService implements IPlanCuentaServiceImpl {

	private Logger logger = LoggerFactory.getLogger(PlanCuentaService.class);
	
	@Autowired
	private IPlanCuentaDao planCuentaDao;
	
	@Autowired
	private INivelCuentaDao nivelCuentaDao;
	
	@Autowired
	private IOpcionCuentaDao opcionCuentaDao;
	
	@Autowired
	private ITipoAnalisis tipoAnalisis;
	
	@Autowired
	private ITipoCuenta tipoCuenta;
	
	@Autowired
	private IMonedaDao moneda;
	
	@Autowired
	private IEmpresaDao empresaDao;
	
	@Autowired
	private ClasifiacionBIenesDao bIenesDao;
	
	@Autowired
	private IPeriodoAnioDao periodoDao;
	
	@Autowired
	private Funciones funciones;
	
	@Override
	@Transactional(readOnly = true)
	public List<PlanCuenta> findByEnabledPaged() {
		return planCuentaDao.findByEnabledPaged();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<PlanCuenta> findByEnabledPagedPorEmpresa(Integer periodoAnioId,Long empresaId,Pageable pageable) {
		return planCuentaDao.findByEnabledPagedEmpresa( periodoAnioId, empresaId,pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public List<NivelCuenta> findNivelCuentaAll() {
		return nivelCuentaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<OpcionPlanCuenta> findOpcionPlanCuentaAll() {
		return opcionCuentaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoAnalisis> findTipoAnalisisAll() {
		return tipoAnalisis.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoCuenta> findTipoCuentaAll() {
		return tipoCuenta.findAll();
	}

	@Override
	@Transactional
	public PlanCuenta save(PlanCuenta planCuenta) {
		return planCuentaDao.save(planCuenta);
	}

	@Override
	@Transactional(readOnly = true)
	public PlanCuenta findById(Integer id) {
		return planCuentaDao.findByCuenta(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Moneda> findMonedaAll() {
		return moneda.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Moneda findMonedaById(Integer id) {
		return moneda.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public PlanCuenta buscarPlanCuentaPorCod(String cod,Integer idperiodoanio) {
		return planCuentaDao.buscarPlanCuentaPorCod(cod.trim(),idperiodoanio);
	};
	@Override
	public List<PlanCuenta> buscarPlanCuentaPorCodigo(String cod_cuenta,Integer periodoanioid) {
		return planCuentaDao.findPlanCuentaByCodCuenta(cod_cuenta.trim(),periodoanioid);
	}

	@Override
	@Transactional(readOnly = true)
	public Empresa obtenerEmpresaPorNom(String empresa) {
		return empresaDao.obtenerEmpresaPorNom(empresa);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlanCuenta> buscarPlanCuentaPorCodigoEmpresa(String cod_cuenta, Integer periodoanioid) {
		return planCuentaDao.buscarPorCodAndEmpresa(cod_cuenta, periodoanioid);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<PlanCuenta> filtrarPorCodigoLikeEmpresa(String cod_cuenta, Integer periodoanioid) {
		return planCuentaDao.filtrarPorCodigoLikeEmpresa(cod_cuenta,periodoanioid);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ClasificacionBienes> listarClasificacionBienes(){
		return bIenesDao.listClasfiacion();

	};

	@Override
	@Transactional(readOnly = true)
	public List<PlanCuenta> listarPlanContablePorEmpresa(String anio, Integer periodoanioid){
		return planCuentaDao.listarPlanContablePorEmpresa(periodoanioid,anio);
	};
	
	@Override
	@Transactional
	public List<PlanContableDto> listarPlanContablePorEmpresaDto(String anio, Integer periodoanioid){
		List<PlanCuenta> listPlanCuentas= new ArrayList<PlanCuenta>();
		List<PlanContableDto> lstDto = new ArrayList<>();
		listPlanCuentas =planCuentaDao.listarPlanContablePorEmpresa(periodoanioid,anio);
	
		 if(listPlanCuentas.size()>0) {
		    	listPlanCuentas = funciones.getOrdenarPlanContable(listPlanCuentas);
		        listPlanCuentas.forEach(p-> {
		        	 PlanContableDto plan = new PlanContableDto();
		        	 plan.setCodCuenta(p.getCodCuenta());
		        	 plan.setNomCuenta(p.getNombreCuenta());
		        	 lstDto.add(plan);
		        });
		 }
		 
		 return lstDto;
	};
	
	@Override
	@Transactional
	public void planOriginal(Integer periodoanioid){
		   PeriodoAnio periodo =   periodoDao.getOne(periodoanioid);
		   List<PlanCuenta> list = new ArrayList<PlanCuenta>();
		   list = planCuentaDao.listarPlanOriginal();
		   List<PlanCuenta> listnueva = new ArrayList<PlanCuenta>();
		   for (PlanCuenta p : list) {
		       PlanCuenta n = new PlanCuenta();
		           n.setCentroCosto(p.getCentroCosto());
		           n.setClasificacionBienes(p.getClasificacionBienes());
		           n.setCodCuenta(p.getCodCuenta());
		           n.setCuentaAmarreDebe(p.getCuentaAmarreDebe());
		           n.setCuentaAmarreHaber(p.getCuentaAmarreHaber());
		           n.setEnabled(p.getEnabled());
		           n.setEntFinanciera(p.getEntFinanciera());
		           n.setIdCuentaAmarreDebe(p.getIdCuentaAmarreDebe());
		           n.setIdCuentaAmarreHaber(p.getIdCuentaAmarreHaber());
		           n.setMoneda(p.getMoneda());
		           n.setNivelCuenta(p.getNivelCuenta());
		           n.setNombreCuenta(p.getNombreCuenta());
		           n.setNum_cuenta(p.getNum_cuenta());
		           n.setOpcionPlanCuenta(p.getOpcionPlanCuenta());
		           n.setTipoAnalisis(p.getTipoAnalisis());
		           n.setTipoCuenta(p.getTipoCuenta());
		           n.setPeriodoAnio(periodo);
		           n = planCuentaDao.save(n);
		           listnueva.add(n);
		    	   
	   	}
		   /*listnueva = list.stream()
		   .map(p->
		       {   PlanCuenta n = new PlanCuenta();
		           n = p;
		    	   n.setId(null) ; n.setPeriodoAnio(periodo);
			   return  n;
		   }).collect(Collectors.toList());*/
		   
		  for (PlanCuenta p : listnueva) {
			System.out.println("ID = "+p.getId()  +  "    COD = "+p.getCodCuenta()+  "      Empresa: "+p.getPeriodoAnio().getEmpresa().getCodEmpresa());
			//planCuentaDao.save(p);
		}
	//	  return listnueva;
		   //planCuentaDao.save(entity):
	};
	
	@Override
	@Transactional
	public void planPorEmpresa(Integer periodoanioid){
		   PeriodoAnio periodo =   periodoDao.getOne(periodoanioid);
		   List<PlanCuenta> list = new ArrayList<PlanCuenta>();
		   list = planCuentaDao.listarPlanPorEmpresa(periodoanioid);
		   for (PlanCuenta n : list) {
		          if(n.getIdCuentaAmarreDebe()!=null) {
                       if(n.getCuentaAmarreDebe().trim().compareTo("")==0) {
                    	     n.setIdCuentaAmarreDebe(null);
                       }else {
                    	   PlanCuenta p = planCuentaDao.findPlanCuentaByCodCuenta(n.getCuentaAmarreDebe().trim(),periodoanioid).get(0);
    			           n.setIdCuentaAmarreDebe(p.getId());
                       }
		        	   
		          }
		          if(n.getIdCuentaAmarreHaber()!=null) {
		        	  if(n.getCuentaAmarreHaber().trim().compareTo("")==0) {
                 	     n.setIdCuentaAmarreHaber(null);
                    }else {
                       PlanCuenta h =  planCuentaDao.findPlanCuentaByCodCuenta(n.getCuentaAmarreHaber().trim(),periodoanioid).get(0);
  			           n.setIdCuentaAmarreHaber(h.getId());
                    }
		        	  
		          }
		          planCuentaDao.save(n);
	   	}
	};
	
	
	@Override
	@Transactional
	public List<CuentaDetalladaDto> listarPlanContableDetalladoOrdenado(String anio, Integer periodoanioid,String nivelCuenta){
		List<PlanCuenta> listPlanCuentas= new ArrayList<PlanCuenta>();
		List<CuentaDetalladaDto> lstDto = new ArrayList<>();
		listPlanCuentas =planCuentaDao.listarPlanContablePorNivelPorEmpresa(periodoanioid,anio,nivelCuenta);
	
		 if(listPlanCuentas.size()>0) {
		    	listPlanCuentas = funciones.getOrdenarPlanContable(listPlanCuentas);
		        listPlanCuentas.forEach(p-> {
		        	CuentaDetalladaDto plan = new CuentaDetalladaDto();
		        	 plan.setCodCuenta(p.getCodCuenta().trim());
		        	 plan.setNomCuenta(p.getNombreCuenta());
		        	 plan.setNivelCuenta(p.getNivelCuenta().getAbreNomNivelCuenta());
		        	 plan.setTipoCuenta(p.getTipoCuenta().getAbreNomTipoCuenta());
		        	 lstDto.add(plan);
		        });
		 }
		 
		 return lstDto;
	};
}
