package com.sprinboot.servicios.app.empresa.constructores.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinboot.servicios.app.empresa.constructores.client.DataServiceClient;
import com.sprinboot.servicios.app.empresa.constructores.dao.VoucherDao;
import com.sprinboot.servicios.app.empresa.constructores.dto.CuentaDetalladaDto;
import com.sprinboot.servicios.app.empresa.constructores.dto.LEDMCuentaDto;
import com.sprinboot.servicios.app.empresa.constructores.dto.LEDMVoucherDto;
import com.sprinboot.servicios.app.empresa.constructores.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.constructores.exceptions.InternalServerErrorException;
import com.sprinboot.servicios.app.empresa.constructores.funciones.Funciones;
import com.sprinboot.servicios.app.empresa.constructores.jsons.HojaTrabajoDetRest;
import com.sprinboot.servicios.app.empresa.constructores.jsons.HojaTrabajoPerdidaRest;
import com.sprinboot.servicios.app.empresa.constructores.jsons.HojaTrabajoRest;
import com.sprinboot.servicios.app.empresa.constructores.jsons.HojaTrabajoSumasRest;
import com.sprinboot.servicios.app.empresa.constructores.jsons.HojaTrabajoTotalesRest;
import com.sprinboot.servicios.app.empresa.constructores.jsons.RegistrosCabRest;
import com.sprinboot.servicios.app.empresa.constructores.jsons.RegistrosDetRest;
import com.sprinboot.servicios.app.empresa.constructores.solid.OpcionHojaTrabajo;
import com.sprinboot.servicios.app.empresa.constructores.solid.OpcionReporte;
import com.sprinboot.servicios.empresa.commons.entity.RegistroLibros;
import com.sprinboot.servicios.empresa.commons.entity.Voucher;

@Service
public class HojaTrabajoService implements HojaTrabajoServiceInterface {
	
   private static final Logger LOGGER =LoggerFactory.getLogger(HojaTrabajoService.class);
	
	@Autowired
    DataServiceClient dataServiceClient;
	
	@Autowired
	VoucherDao voucherDao;
	
	@Autowired
	Funciones funciones;

	@Override
	@Transactional
	public HojaTrabajoRest reporteRegistros(List<OpcionHojaTrabajo> lstOpcion, Integer idperiodo, String anio,	Integer codmes, Integer idperiodoanio) throws ClaseException{
		HojaTrabajoRest hojaTrabajoRest = new HojaTrabajoRest(); 
		List<HojaTrabajoDetRest> listDet= new ArrayList<HojaTrabajoDetRest>();
		hojaTrabajoRest.setListHojaTrabajoDet(listDet);
		for (OpcionHojaTrabajo opcion : lstOpcion) {
			hojaTrabajoRest =   opcion.proceso(idperiodo, anio, codmes,idperiodoanio);
		  }
		return hojaTrabajoRest;
	} 
	
	@Override
	@Transactional(readOnly=true)
	public List<CuentaDetalladaDto> filtroCuentasMicroservicioAdministracion(String nivelcuenta, String anio,Integer idperiodoanio) throws ClaseException{
		return dataServiceClient.listarPlanContableDetalladoOrdenado(anio, idperiodoanio, nivelcuenta);
	} 
	
	@Override
	@Transactional(readOnly=true)
	public List<Voucher> filtroDaoMensual(String codcuenta,Integer idperiodo, String anio,	Integer codmes) throws ClaseException{
		return voucherDao.listarParaHojaTrabajoMensual(codcuenta.trim(), codmes, anio, idperiodo);
	} 
	
	@Override
	@Transactional(readOnly=true)
	public List<Voucher> filtroDaoAcumulado(String codcuenta,String anio,	Integer codmes) throws ClaseException{
		return voucherDao.listarParaHojaTrabajoAcumulado(codcuenta.trim(), codmes, anio);
	} 
	
	@Override
	 public HojaTrabajoDetRest getHojaTrabajoDetPrimeroCuatroColumnas(List<Voucher> lista,String moneda,HojaTrabajoDetRest det ) throws ClaseException {
		LEDMCuentaDto montosMayor  = getDatosMontosMayor(lista, moneda);
        det.setMontoDebito(montosMayor.getTotalDebe());
        det.setMontoCredito(montosMayor.getTotalHaber());
        det.setSaldoDeudor(montosMayor.getSaldoDebe());
        det.setSaldoAcreedor(montosMayor.getSaldoHaber());
	    return det ;
	}
	
	
	@Override
	 public HojaTrabajoDetRest getHojaTrabajoDetQuintaSextaColumnaInventarios(HojaTrabajoDetRest det, String tipoCuenta ) throws ClaseException {
		//pasivo o activo 
		if(tipoCuenta.trim().compareTo("P")==0 || tipoCuenta.trim().compareTo("A")==0 ) {
		      det.setInventarioActivo(det.getSaldoDeudor());
		      det.setInventarioPasivo(det.getSaldoAcreedor());
		}else {
			det.setInventarioActivo(new BigDecimal("0.00"));
		    det.setInventarioPasivo(new BigDecimal("0.00"));
		}
        
	    return det ;
	}
	
	@Override
	 public HojaTrabajoDetRest getHojaTrabajoDetSeptimaOctavaColumnaNaturaleza(HojaTrabajoDetRest det, String tipoCuenta ) throws ClaseException {
		if(tipoCuenta.trim().compareTo("R")==0 || tipoCuenta.trim().compareTo("N")==0 ) {
		      det.setNaturalezaPerdida(det.getSaldoDeudor());
		      det.setNaturalezaGanancia(det.getSaldoAcreedor());
		}else {
		      det.setNaturalezaPerdida(new BigDecimal("0.00"));
		      det.setNaturalezaGanancia(new BigDecimal("0.00"));
			
		}
	    return det ;
	}
	
	@Override
	 public HojaTrabajoDetRest getHojaTrabajoDetNovenaDecimaColumnaFuncion(HojaTrabajoDetRest det, String tipoCuenta ) throws ClaseException {
		if(tipoCuenta.trim().compareTo("R")==0 || tipoCuenta.trim().compareTo("F")==0 ) {
		      det.setFuncionPerdida(det.getSaldoDeudor());
		      det.setFuncionGanancia(det.getSaldoAcreedor());
		}else {
			 det.setFuncionPerdida(new BigDecimal("0.00"));
		      det.setFuncionGanancia(new BigDecimal("0.00"));
		}
	    return det ;
	}
	
	
	@Override
	public LEDMCuentaDto getDatosMontosMayor(List<Voucher> listVoucher, String moneda) throws ClaseException{
		LEDMCuentaDto montosMayor = new LEDMCuentaDto();
		try {
			listVoucher.stream().map(v ->  {
				     montosMayor.setTotalDebe(funciones.sumarRedondear(montosMayor.getTotalDebe(),  montoSegunMoneda(v , moneda, v.getDebe())   , 2));
				     montosMayor.setTotalHaber(funciones.sumarRedondear(montosMayor.getTotalHaber(),  montoSegunMoneda(v , moneda, v.getHaber()), 2));
                 return v;
			}).collect(Collectors.toList()); 
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","INTERNAL_SERVER_ERROR");
		}
		
		return calcularSaldos(montosMayor);
		
	} 
	
	
	private BigDecimal montoSegunMoneda(Voucher v, String moneda,BigDecimal monto) {
		  if(moneda.compareTo(v.getAbreNomMoneda().trim())==0) {
			  return monto;
		  }else {
			  if (monto!=null) {
			    return monto.compareTo(new BigDecimal("0"))==0 ? new BigDecimal("0.00") : v.getEquivalente();	
			 }else {
				return new BigDecimal("0.00");
			}
		}
		
	}
	
	
	public LEDMCuentaDto calcularSaldos(LEDMCuentaDto mCuentaDto ) {
		 
		if (mCuentaDto.getTotalHaber().compareTo(mCuentaDto.getTotalDebe())==0) {
			//saldos Iguales
			  mCuentaDto.setSaldoDebe(new BigDecimal("0.00"));
			   mCuentaDto.setSaldoHaber(new BigDecimal("0.00"));
		}else {
			if (mCuentaDto.getTotalHaber().compareTo(mCuentaDto.getTotalDebe())==1) {
				//Haber es mayor que el debe
				mCuentaDto.setSaldoHaber(funciones.restarRedondear(mCuentaDto.getTotalHaber(), mCuentaDto.getTotalDebe(), 2));
			    mCuentaDto.setSaldoDebe(new BigDecimal("0.00"));
			}else {
				//Debe es mayor que el debe
			    mCuentaDto.setSaldoHaber(new BigDecimal("0.00"));
				mCuentaDto.setSaldoDebe(funciones.restarRedondear(mCuentaDto.getTotalDebe(), mCuentaDto.getTotalHaber(), 2));
			}
		}
		return mCuentaDto;
	}
	
	
	
	@Override
	public HojaTrabajoTotalesRest getTotales(List<HojaTrabajoDetRest> list) throws ClaseException{
		HojaTrabajoTotalesRest totales = new HojaTrabajoTotalesRest();
		try {
			list.stream().map(d ->  {
                 totales.setMontoDebito(funciones.sumarRedondear(totales.getMontoDebito(), d.getMontoDebito(), 2));
                 totales.setMontoCredito(funciones.sumarRedondear(totales.getMontoCredito(), d.getMontoCredito(), 2));
                 totales.setSaldoDeudor(funciones.sumarRedondear(totales.getSaldoDeudor(), d.getSaldoDeudor(), 2));
                 totales.setSaldoAcreedor(funciones.sumarRedondear(totales.getSaldoAcreedor(), d.getSaldoAcreedor(),2));
                 totales.setInventarioActivo(funciones.sumarRedondear(totales.getInventarioActivo(), d.getInventarioActivo(), 2));
                 totales.setInventarioPasivo(funciones.sumarRedondear(totales.getInventarioPasivo(),d.getInventarioPasivo(),2));
                 totales.setNaturalezaPerdida(funciones.sumarRedondear(totales.getNaturalezaPerdida(), d.getNaturalezaPerdida(), 2));
                 totales.setNaturalezaGanancia(funciones.sumarRedondear(totales.getNaturalezaGanancia(), d.getNaturalezaGanancia(), 2));
                 totales.setFuncionPerdida(funciones.sumarRedondear(totales.getFuncionPerdida(), d.getFuncionPerdida(), 2));
                 totales.setFuncionGanancia(funciones.sumarRedondear(totales.getFuncionGanancia(), d.getFuncionGanancia(), 2));
                 return d;
			}).collect(Collectors.toList()); 
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","INTERNAL_SERVER_ERROR");
		}
		
		return totales;
	} 
	
	@Override
	public HojaTrabajoPerdidaRest getPerdidadEjercicio(HojaTrabajoTotalesRest totales) throws ClaseException{
		HojaTrabajoPerdidaRest perdida = new HojaTrabajoPerdidaRest();
		try {
			  BigDecimal inventarioActivo = getPrimerColumnaPorEstado(totales.getInventarioActivo(), totales.getInventarioPasivo());
			  if (inventarioActivo!=null) {
				  perdida.setInventarioActivo(inventarioActivo);
			   }else {
				   BigDecimal inventarioPasivo = getSegundacolumnaPorEstado(totales.getInventarioActivo(),totales.getInventarioPasivo());
					  if (inventarioPasivo!=null) {
						  perdida.setInventarioPasivo(inventarioPasivo);
					   }
		       }
			  
			  BigDecimal naturalezaPerdida = getPrimerColumnaPorEstado(totales.getNaturalezaPerdida(), totales.getNaturalezaGanancia());
			  if (naturalezaPerdida!=null) {
				  perdida.setNaturalezaPerdida(naturalezaPerdida);
			   }else {
				   BigDecimal naturalezaGanancia = getSegundacolumnaPorEstado( totales.getNaturalezaPerdida(),totales.getNaturalezaGanancia());
					  if (naturalezaGanancia!=null) {
						  perdida.setNaturalezaGanancia(naturalezaGanancia);
					   }
		       }
			  
			  BigDecimal funcionPerdida = getPrimerColumnaPorEstado(totales.getFuncionPerdida(), totales.getFuncionGanancia());
			  if (funcionPerdida!=null) {
				  perdida.setFuncionPerdida(funcionPerdida);
			   }else {
				   BigDecimal funcionGanancia = getSegundacolumnaPorEstado(totales.getFuncionPerdida(),totales.getFuncionGanancia());
					  if (funcionGanancia!=null) {
						  perdida.setFuncionGanancia(funcionGanancia);
					   }
		       }
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","INTERNAL_SERVER_ERROR");
		}
		
		return perdida;
	} 
	
	private BigDecimal getPrimerColumnaPorEstado(BigDecimal primer , BigDecimal segundo) {
           if ( segundo.compareTo(primer)==1 ) {
			     return funciones.restarRedondear(segundo, primer , 2);
		    }else {
		    	return null;
		    }
	}
	
	private BigDecimal getSegundacolumnaPorEstado(BigDecimal primer , BigDecimal segundo) {
        if ( primer.compareTo(segundo)==1 ) {
			     return funciones.restarRedondear(primer, segundo , 2);
		    }else {
		    	return null;
		    }
	}
	
	
	@Override
	public HojaTrabajoSumasRest getSumaIguales(HojaTrabajoTotalesRest totales, HojaTrabajoPerdidaRest perdida) throws ClaseException{
		HojaTrabajoSumasRest sumas = new HojaTrabajoSumasRest();
		try {
		
			sumas.setInventarioActivo(funciones.sumarRedondear(totales.getInventarioActivo(), perdida.getInventarioActivo(), 2));
			sumas.setInventarioPasivo(funciones.sumarRedondear(totales.getInventarioPasivo(), perdida.getInventarioPasivo(), 2));
			sumas.setNaturalezaPerdida(funciones.sumarRedondear(totales.getNaturalezaPerdida(), perdida.getNaturalezaPerdida(), 2));
			sumas.setNaturalezaGanancia(funciones.sumarRedondear(totales.getNaturalezaGanancia(), perdida.getNaturalezaGanancia(), 2));
			sumas.setFuncionPerdida(funciones.sumarRedondear(totales.getFuncionPerdida(), perdida.getFuncionPerdida(), 2));
			sumas.setFuncionGanancia(funciones.sumarRedondear(totales.getFuncionGanancia(), perdida.getFuncionGanancia(), 2));
			
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","INTERNAL_SERVER_ERROR");
		}
		
		return sumas;
	} 
}
