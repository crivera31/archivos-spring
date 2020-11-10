package com.sprinboot.servicios.app.oriplan.sis.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sprinboot.servicios.app.oriplan.sis.dto.PlanillaDto;
import com.sprinboot.servicios.app.oriplan.sis.jsons.PlanillaRest;
import com.sprinboot.servicios.oriplan.commons.entity.*;
import com.sprinboot.servicios.oriplan.commons.services.IObjectService;


public interface PlanillaServiceInterface extends IObjectService<PlanillaExcel> {

    public List<PlanillaExcel> getAll();

    public List<PlanillaDto> planillaListar(String nomSemanaPeriodo, String Obra, String mes, Integer idObraDatosGenerales, String anio);

    public PlanillaDto insertarPlanilla(String codigo, String dni, String nombres, String ocupacion,
                                        String regimenLaboral, String obra, String mes, String declaracion, String segmento, Date fechaIngreso,
                                        Date fechaCese, Integer numSemanaQuincena, String periodoSemanal, String sistemaPensiones, String cussp,
                                        String tipoComision, String tipoContratoEssalud, Integer numHijos, TareoDet tareo,
                                        String codigoSistemaPensiones, Integer idObraDatosGenerales, String anio, BigDecimal condicionTrabajo, Trabajadores trabajador);

    public TareoDet buscarEnTareo(String dni, TareoCab tareo);

    public void guardar(List<PlanillaExcel> lista);

    public boolean buscar(String dni, String nomsemana, String obra);

    public PlanillaExcel getBuscar(String dni, String nomsemana, String obra);

    public List<PlanillaExcel> getPlaniillita(String semana, String obra);

    public List<PlanillaExcel> getPlaniillitaPorDni(String dni, String semana, String obra);

    public List<PlanillaExcel> getmes(String mes, String anio, String obra);

    public List<PlanillaRest> listarPlanillaAfp(String anio, String mes);

    public PlanillaExcel editar(Integer idDatosGenerales, String nomSemana, String dni,
                                      Integer numHijos, BigDecimal condicionTrabajo, BigDecimal adelanto,
                                      String declaracion);

}
