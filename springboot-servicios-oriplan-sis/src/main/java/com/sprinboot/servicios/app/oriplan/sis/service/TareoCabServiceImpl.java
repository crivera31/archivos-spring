package com.sprinboot.servicios.app.oriplan.sis.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprinboot.servicios.app.oriplan.sis.dao.TareoCabDao;
import com.sprinboot.servicios.app.oriplan.sis.dao.TareoDetDao;
import com.sprinboot.servicios.oriplan.commons.entity.TareoCab;
import com.sprinboot.servicios.oriplan.commons.entity.TareoDet;
import com.sprinboot.servicios.oriplan.commons.services.ObjectService;

import net.bytebuddy.asm.Advice.This;

@Service
public class TareoCabServiceImpl extends ObjectService<TareoCab, TareoCabDao> implements TareoCabServiceInterface {

	@Autowired
	TareoCabDao dao;

	@Autowired
	TareoDetDao tareoDetDao;

	private static final Logger LOGGER = LoggerFactory.getLogger(This.class);

	@Override
	@Transactional(readOnly = true)
	public List<TareoCab> listar() {
		return dao.listar();
	}

	@Override
	public boolean verificarRepetcion(String name) {
		return dao.verificarrepepiticion(name.trim()).size() > 0;
	}

	@Override
	public TareoCab buscar(String name, Integer idobra) {
		return dao.buscar(name.trim(), idobra);
	}

//	@Override
//	@Transactional
//	public TareoCab guardar(TareoCab x) {
//		TareoCab nuevo = new TareoCab();
//		nuevo.setAnio(x.getAnio());
//		nuevo.setIdDatosGenerales(x.getIdDatosGenerales());
//		nuevo.setEnabled(x.getEnabled());
//		nuevo.setFechaDomingo(x.getFechaDomingo());
//		nuevo.setFechaJueves(x.getFechaJueves());
//		nuevo.setFechaLunes(x.getFechaLunes());
//		nuevo.setFechaMartes(x.getFechaMartes());
//		nuevo.setFechaMiercoles(x.getFechaMiercoles());
//		nuevo.setFechaSabado(x.getFechaSabado());
//		nuevo.setFechaViernes(x.getFechaViernes());
//		nuevo.setMes(x.getMes());
//		nuevo.setPeriodo_semanal(x.getPeriodo_semanal());
//		nuevo.setNomPeriodoSemanal(x.getNomPeriodoSemanal());
//		nuevo = dao.save(nuevo);
//		List<TareoDet> list = new ArrayList<>();
//		for (TareoDet tareoDet : x.getLstTareoDet()) {
//			tareoDet.setAdelanto(isCero(tareoDet.getAdelanto()));
//			tareoDet.setCondicionTrabajo(isCero(tareoDet.getCondicionTrabajo()));
//			tareoDet.setDomingoExtras(isCero(tareoDet.getDomingoExtras()));
//			tareoDet.setDomingoNormal(isCero(tareoDet.getDomingoNormal()));
//			tareoDet.setTotalesExtrasCien(isCero(tareoDet.getTotalesExtrasCien()));
//			tareoDet.setTotalesExtrasSesenta(isCero(tareoDet.getTotalesExtrasSesenta()));
//			tareoDet.setTotalesHorasExtras(isCero(tareoDet.getTotalesHorasExtras()));
//			tareoDet.setJuevesExtras(isCero(tareoDet.getJuevesExtras()));
//			tareoDet.setJuevesNormal(isCero(tareoDet.getJuevesNormal()));
//			tareoDet.setLunesExtras(isCero(tareoDet.getLunesExtras()));
//			tareoDet.setLunesNormal(isCero(tareoDet.getLunesNormal()));
//			tareoDet.setMartesExtras(isCero(tareoDet.getMartesExtras()));
//			tareoDet.setMartesNormal(isCero(tareoDet.getMartesNormal()));
//			tareoDet.setMiercolesExtras(isCero(tareoDet.getMiercolesExtras()));
//			tareoDet.setMiercolesNormal(isCero(tareoDet.getMiercolesNormal()));
//			tareoDet.setNumDiasDescansoMedico(isCero(tareoDet.getNumDiasDescansoMedico()));
//			tareoDet.setNumDiasFeriadosDescansados(isCero(tareoDet.getNumDiasFeriadosDescansados()));
//			tareoDet.setNumDiasFeriadosTrabajados(isCero(tareoDet.getNumDiasFeriadosTrabajados()));
//			tareoDet.setSabadoExtras(isCero(tareoDet.getSabadoExtras()));
//			tareoDet.setSabadoNormal(isCero(tareoDet.getSabadoNormal()));
//			tareoDet.setTotalesHorasNormales(isCero(tareoDet.getTotalesHorasNormales()));
//			tareoDet.setTareoCab(nuevo);
//			list.add(tareoDet);
//
//		}
//
//		nuevo.setLstTareoDet(list);
//		return dao.save(nuevo);
//	}

	@Override
	@Transactional
	public TareoCab guardar(TareoCab x) {

		TareoCab tareoCab = dao.buscar(x.getNomPeriodoSemanal(), x.getIdDatosGenerales());
		// tareoCab.setLstTareoDet(null);
		if (tareoCab != null) {

			LOGGER.info(">>>>>>>>>>>>>:" + tareoCab.getId());
			List<TareoDet> list = new ArrayList<>();
			for (TareoDet tareoDet : x.getLstTareoDet()) {

				TareoDet ta = tareoDetDao.tareoDetPorTareoCabDni(tareoCab.getId(), tareoDet.getDni());

				if (ta != null) {
					ta.setAdelanto(isCero(tareoDet.getAdelanto()));
					ta.setSindicato(isCeroInt(tareoDet.getSindicato()));
					ta.setDomingoExtras(isCero(tareoDet.getDomingoExtras()));
					ta.setDomingoNormal(isCero(tareoDet.getDomingoNormal()));
					ta.setTotalesExtrasCien(isCero(tareoDet.getTotalesExtrasCien()));
					ta.setTotalesExtrasSesenta(isCero(tareoDet.getTotalesExtrasSesenta()));
					ta.setTotalesHorasExtras(isCero(tareoDet.getTotalesHorasExtras()));
					ta.setJuevesExtras(isCero(tareoDet.getJuevesExtras()));
					ta.setJuevesNormal(isCero(tareoDet.getJuevesNormal()));
					ta.setLunesExtras(isCero(tareoDet.getLunesExtras()));
					ta.setLunesNormal(isCero(tareoDet.getLunesNormal()));
					ta.setMartesExtras(isCero(tareoDet.getMartesExtras()));
					ta.setMartesNormal(isCero(tareoDet.getMartesNormal()));
					ta.setMiercolesExtras(isCero(tareoDet.getMiercolesExtras()));
					ta.setMiercolesNormal(isCero(tareoDet.getMiercolesNormal()));
					ta.setNumDiasDescansoMedico(isCero(tareoDet.getNumDiasDescansoMedico()));
					ta.setNumDiasFeriadosDescansados(isCero(tareoDet.getNumDiasFeriadosDescansados()));
					ta.setNumDiasFeriadosTrabajados(isCero(tareoDet.getNumDiasFeriadosTrabajados()));
					ta.setSabadoExtras(isCero(tareoDet.getSabadoExtras()));
					ta.setSabadoNormal(isCero(tareoDet.getSabadoNormal()));
					ta.setTotalesHorasNormales(isCero(tareoDet.getTotalesHorasNormales()));
					ta.setTareoCab(tareoCab);
					tareoDetDao.save(ta);
				} else {
					tareoDet.setAdelanto(isCero(tareoDet.getAdelanto()));
					tareoDet.setSindicato(isCeroInt(tareoDet.getSindicato()));
					tareoDet.setDomingoExtras(isCero(tareoDet.getDomingoExtras()));
					tareoDet.setDomingoNormal(isCero(tareoDet.getDomingoNormal()));
					tareoDet.setTotalesExtrasCien(isCero(tareoDet.getTotalesExtrasCien()));
					tareoDet.setTotalesExtrasSesenta(isCero(tareoDet.getTotalesExtrasSesenta()));
					tareoDet.setTotalesHorasExtras(isCero(tareoDet.getTotalesHorasExtras()));
					tareoDet.setJuevesExtras(isCero(tareoDet.getJuevesExtras()));
					tareoDet.setJuevesNormal(isCero(tareoDet.getJuevesNormal()));
					tareoDet.setLunesExtras(isCero(tareoDet.getLunesExtras()));
					tareoDet.setLunesNormal(isCero(tareoDet.getLunesNormal()));
					tareoDet.setMartesExtras(isCero(tareoDet.getMartesExtras()));
					tareoDet.setMartesNormal(isCero(tareoDet.getMartesNormal()));
					tareoDet.setMiercolesExtras(isCero(tareoDet.getMiercolesExtras()));
					tareoDet.setMiercolesNormal(isCero(tareoDet.getMiercolesNormal()));
					tareoDet.setNumDiasDescansoMedico(isCero(tareoDet.getNumDiasDescansoMedico()));
					tareoDet.setNumDiasFeriadosDescansados(isCero(tareoDet.getNumDiasFeriadosDescansados()));
					tareoDet.setNumDiasFeriadosTrabajados(isCero(tareoDet.getNumDiasFeriadosTrabajados()));
					tareoDet.setSabadoExtras(isCero(tareoDet.getSabadoExtras()));
					tareoDet.setSabadoNormal(isCero(tareoDet.getSabadoNormal()));
					tareoDet.setTotalesHorasNormales(isCero(tareoDet.getTotalesHorasNormales()));
					tareoDet.setTareoCab(tareoCab);
					list.add(tareoDet);
				}

			}
			tareoCab.setLstTareoDet(list);
			return dao.save(tareoCab);

		} else {
			TareoCab nuevo = new TareoCab();
			nuevo.setAnio(x.getAnio());
			nuevo.setIdDatosGenerales(x.getIdDatosGenerales());
			nuevo.setEnabled(x.getEnabled());
			nuevo.setFechaDomingo(x.getFechaDomingo());
			nuevo.setFechaJueves(x.getFechaJueves());
			nuevo.setFechaLunes(x.getFechaLunes());
			nuevo.setFechaMartes(x.getFechaMartes());
			nuevo.setFechaMiercoles(x.getFechaMiercoles());
			nuevo.setFechaSabado(x.getFechaSabado());
			nuevo.setFechaViernes(x.getFechaViernes());
			nuevo.setMes(x.getMes());
			nuevo.setPeriodo_semanal(x.getPeriodo_semanal());
			nuevo.setNomPeriodoSemanal(x.getNomPeriodoSemanal());
			nuevo = dao.save(nuevo);
			List<TareoDet> list = new ArrayList<>();
			for (TareoDet tareoDet : x.getLstTareoDet()) {
				tareoDet.setAdelanto(isCero(tareoDet.getAdelanto()));
				tareoDet.setSindicato(isCeroInt(tareoDet.getSindicato()));
				tareoDet.setDomingoExtras(isCero(tareoDet.getDomingoExtras()));
				tareoDet.setDomingoNormal(isCero(tareoDet.getDomingoNormal()));
				tareoDet.setTotalesExtrasCien(isCero(tareoDet.getTotalesExtrasCien()));
				tareoDet.setTotalesExtrasSesenta(isCero(tareoDet.getTotalesExtrasSesenta()));
				tareoDet.setTotalesHorasExtras(isCero(tareoDet.getTotalesHorasExtras()));
				tareoDet.setJuevesExtras(isCero(tareoDet.getJuevesExtras()));
				tareoDet.setJuevesNormal(isCero(tareoDet.getJuevesNormal()));
				tareoDet.setLunesExtras(isCero(tareoDet.getLunesExtras()));
				tareoDet.setLunesNormal(isCero(tareoDet.getLunesNormal()));
				tareoDet.setMartesExtras(isCero(tareoDet.getMartesExtras()));
				tareoDet.setMartesNormal(isCero(tareoDet.getMartesNormal()));
				tareoDet.setMiercolesExtras(isCero(tareoDet.getMiercolesExtras()));
				tareoDet.setMiercolesNormal(isCero(tareoDet.getMiercolesNormal()));
				tareoDet.setNumDiasDescansoMedico(isCero(tareoDet.getNumDiasDescansoMedico()));
				tareoDet.setNumDiasFeriadosDescansados(isCero(tareoDet.getNumDiasFeriadosDescansados()));
				tareoDet.setNumDiasFeriadosTrabajados(isCero(tareoDet.getNumDiasFeriadosTrabajados()));
				tareoDet.setSabadoExtras(isCero(tareoDet.getSabadoExtras()));
				tareoDet.setSabadoNormal(isCero(tareoDet.getSabadoNormal()));
				tareoDet.setTotalesHorasNormales(isCero(tareoDet.getTotalesHorasNormales()));
				tareoDet.setTareoCab(nuevo);
				list.add(tareoDet);
			}

			nuevo.setLstTareoDet(list);
			return dao.save(nuevo);
		}

	}

	public BigDecimal isCero(BigDecimal num) {
		return num == null ? new BigDecimal("0.00") : num;
	}

	public Integer isCeroInt(Integer num){
		return num == null ? new Integer("0") : num;
	}

	@Override
	public List<TareoCab> getTareoSemObra(String semana, Integer obra) {

		List<TareoCab> lstTareoCab = new ArrayList<>();
		List<TareoDet> lstTareoDet = new ArrayList<>();

		for (TareoCab tareoCab : dao.lstBuscar(semana, obra)) {
			tareoCab.getLstTareoDet().stream().forEach(tareoDet -> {
				tareoDet.setTareoCab(null);
				lstTareoDet.add(tareoDet);

			});
			tareoCab.setLstTareoDet(lstTareoDet);
			lstTareoCab.add(tareoCab);
		}

		return lstTareoCab;
	}

	@Override
	public TareoCab actualizar(TareoCab tareoCab) {

		List<TareoDet> lstTareoDet = new ArrayList<>();

		tareoCab.getLstTareoDet().forEach(tareoDet -> {
			tareoDet.setTareoCab(tareoCab);
			lstTareoDet.add(tareoDet);
		});
		dao.save(tareoCab);
		tareoCab.getLstTareoDet().forEach(tareoDet -> {
			tareoDet.setTareoCab(null);
			lstTareoDet.add(tareoDet);
		});

		return tareoCab;
	}

}
