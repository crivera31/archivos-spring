package com.sprinboot.servicios.app.empresa.villasol.solid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sprinboot.servicios.app.empresa.villasol.dao.RegistroLibrosDao;
import com.sprinboot.servicios.app.empresa.villasol.dto.CuentaDetalladaDto;
import com.sprinboot.servicios.app.empresa.villasol.exceptions.ClaseException;
import com.sprinboot.servicios.app.empresa.villasol.exceptions.InternalServerErrorException;
import com.sprinboot.servicios.app.empresa.villasol.jsons.HojaTrabajoDetRest;
import com.sprinboot.servicios.app.empresa.villasol.jsons.HojaTrabajoPerdidaRest;
import com.sprinboot.servicios.app.empresa.villasol.jsons.HojaTrabajoRest;
import com.sprinboot.servicios.app.empresa.villasol.jsons.HojaTrabajoSumasRest;
import com.sprinboot.servicios.app.empresa.villasol.jsons.HojaTrabajoTotalesRest;
import com.sprinboot.servicios.app.empresa.villasol.jsons.RegistrosCabRest;
import com.sprinboot.servicios.app.empresa.villasol.jsons.RegistrosDetRest;
import com.sprinboot.servicios.app.empresa.villasol.service.HojaTrabajoService;
import com.sprinboot.servicios.app.empresa.villasol.service.HojaTrabajoServiceInterface;
import com.sprinboot.servicios.app.empresa.villasol.service.RegistrosSistemaServiceInterface;
import com.sprinboot.servicios.empresa.commons.entity.RegistroLibros;
import com.sprinboot.servicios.empresa.commons.entity.Voucher;

@Primary
@Service("RegistroSolesMensual")
public class RegistroSolesMensual implements OpcionHojaTrabajo{
 private static final Logger LOGGER =LoggerFactory.getLogger(RegistroSolesMensual.class);

	private HojaTrabajoServiceInterface service;
	
	@Autowired
	public RegistroSolesMensual(HojaTrabajoServiceInterface service) {
		this.service = service;
	}


	
	@Override
	@Transactional
	public HojaTrabajoRest proceso(Integer idperiodo, String anio, Integer codmes, Integer idperiodoanio) throws ClaseException {
		
			//List<RegistroLibros> lstRegLibros = dao.listarRegistroSistemaMensualFecha(idperiodo, codmes, anio);
			HojaTrabajoRest cab = new HojaTrabajoRest();
			List<HojaTrabajoDetRest> listDet= new ArrayList<HojaTrabajoDetRest>();
			List<CuentaDetalladaDto> planContable = new ArrayList<>();
		    try {
				planContable = service.filtroCuentasMicroservicioAdministracion("R", anio, idperiodoanio);
			} catch (Exception e2) {
				throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","INTERNAL_SERVER_ERROR");

			}
			
			if (planContable.size()>0) {
				LOGGER.info("si hay plan contable");
				planContable.stream().map(p->
				{  try {
					List<Voucher> listVoucher = new ArrayList<>();
					listVoucher = service.filtroDaoMensual(p.getCodCuenta(), idperiodo, anio, codmes);
					HojaTrabajoDetRest det = new HojaTrabajoDetRest();
					if (listVoucher.size()>0) {
						LOGGER.info("si hay vouchers");
						det.setCuenta(p.getCodCuenta().trim());
						det.setNombre(p.getNomCuenta().trim());
						det = service.getHojaTrabajoDetPrimeroCuatroColumnas(listVoucher, "S", det);
						det = service.getHojaTrabajoDetQuintaSextaColumnaInventarios(det, p.getTipoCuenta());
						det = service.getHojaTrabajoDetSeptimaOctavaColumnaNaturaleza(det, p.getTipoCuenta());
						det = service.getHojaTrabajoDetNovenaDecimaColumnaFuncion(det, p.getTipoCuenta());
						listDet.add(det);
					}else {
						LOGGER.info("no hay vouchers");

					}
					} catch (ClaseException e) {
						e.printStackTrace();
					}
				   return p;	
				}).collect(Collectors.toList());
				
				HojaTrabajoTotalesRest totales = new HojaTrabajoTotalesRest();
				HojaTrabajoPerdidaRest perdida = new HojaTrabajoPerdidaRest();
				HojaTrabajoSumasRest sumas = new HojaTrabajoSumasRest();
				
				if (listDet.size()>0) {
						try {
						 totales = service.getTotales(listDet);
						 perdida = service.getPerdidadEjercicio(totales);
						 sumas = service.getSumaIguales(totales, perdida);
						} catch (Exception e2) {
							throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","INTERNAL_SERVER_ERROR");
						}
				}
				cab.setListHojaTrabajoDet(listDet);
				cab.setTotales(totales);
				cab.setPerdidaEjercicio(perdida);
				cab.setSumasIguales(sumas);
				cab.setAnio(anio);
				//cab.setCodRuc(codRuc);
				
			}else {
				
				LOGGER.info("El plan contable esta vacio ");
			}
		
		  return cab;
		

	}

	
}
