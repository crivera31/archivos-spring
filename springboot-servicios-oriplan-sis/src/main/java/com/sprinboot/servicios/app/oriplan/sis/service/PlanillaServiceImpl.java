package com.sprinboot.servicios.app.oriplan.sis.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import com.sprinboot.servicios.app.oriplan.sis.dto.TrabajadorAfpDto;
import com.sprinboot.servicios.app.oriplan.sis.jsons.PlanillaRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinboot.servicios.app.oriplan.sis.dao.DatosGeneralesDao;
import com.sprinboot.servicios.app.oriplan.sis.dao.PlanillaExcelDao;
import com.sprinboot.servicios.app.oriplan.sis.dto.MesDto;
import com.sprinboot.servicios.app.oriplan.sis.dto.PlanillaDto;
import com.sprinboot.servicios.app.oriplan.sis.funciones.Funciones;
import com.sprinboot.servicios.oriplan.commons.entity.DatosGenerales;
import com.sprinboot.servicios.oriplan.commons.entity.PlanillaExcel;
import com.sprinboot.servicios.oriplan.commons.entity.TablaSalarial;
import com.sprinboot.servicios.oriplan.commons.entity.TareoCab;
import com.sprinboot.servicios.oriplan.commons.entity.TareoDet;
import com.sprinboot.servicios.oriplan.commons.entity.TasasPensiones;
import com.sprinboot.servicios.oriplan.commons.entity.Trabajadores;
import com.sprinboot.servicios.oriplan.commons.entity.Sctr;
import com.sprinboot.servicios.oriplan.commons.services.ObjectService;

@Service
public class PlanillaServiceImpl extends ObjectService<PlanillaExcel, PlanillaExcelDao>
        implements PlanillaServiceInterface {

    @Autowired
    private PlanillaExcelDao libroDao;

    @Autowired
    private TrabajadoresServiceInterface trabajadoresService;

    @Autowired
    private TablaSalarialInterface tablaService;

    @Autowired
    private TasaPensionesServiceInterface tasaPensiones;

    @Autowired
    private SctrServiceInterface sctrService;

    @Autowired
    private DatosGeneralesServiceInterface datosService;

    @Autowired
    private TareoCabServiceInterface tareoService;

    @Autowired
    private SistemaPensionesInterface sistemaPensionesInterface;

    @Autowired
    Funciones funciones;

    @Override
    public List<PlanillaExcel> getAll() {
        return libroDao.findAll();
    }

    @Override
    public List<PlanillaExcel> getmes(String mes, String anio, String obra) {
        return libroDao.buscarpormes(mes, anio, obra);
    }

    @Override
    public List<PlanillaExcel> getPlaniillita(String semana, String obra) {
        return libroDao.buscarparaBoleta(semana, obra);
    }

    @Override
    @Transactional
    public void guardar(List<PlanillaExcel> lista) {
        for (PlanillaExcel planillaExcel : lista) {
            PlanillaExcel e = new PlanillaExcel();

            libroDao.save(planillaExcel);
//            e.setCodigo(e.getCodigo() + e.getId() + "ORIPLAN");
//            e.setCodigo(e.getDni() + "ORIPLAN");
//            e.setTotalRemuneracionCal(
//                    funciones.sumarRedondear(e.getTotalRemuneracionCal(), e.getCondicionTrabajoCal(), 6));
//            e.setTotalDsctos(funciones.sumarRedondear(e.getTotalDsctos(), e.getAdelanto(), 6));
//            e.setNetoPagar(funciones.restarRedondear(e.getTotalRemuneracionCal(), e.getTotalDsctos(), 6));
//            libroDao.save(e);
        }
    }

    @Override
    @Transactional
    public List<PlanillaDto> planillaListar(String nomSemanaPeriodo, String Obra, String mes,
                                            Integer idObraDatosGenerales, String anio) {
        TareoCab tareo = tareoService.buscar(nomSemanaPeriodo, idObraDatosGenerales);
        List<Trabajadores> trabajadores = trabajadoresService.listarTrabajadoresActivos();
        List<PlanillaDto> lista = new ArrayList<PlanillaDto>();
        for (Trabajadores trabajador : trabajadores) {
            String codigo = "NOMBRES INICIALES";
            TareoDet det = buscarEnTareo(trabajador.getDni(), tareo);

            PlanillaDto n = new PlanillaDto();

            n = insertarPlanilla(codigo, trabajador.getDni().trim(), trabajador.getNombresCompletos(),
                    trabajador.getCargoOcupacion(), "Régimen de Construcción Civi", Obra, mes, "SI", "",
                    trabajador.getFechaIngreso(), trabajador.getFecha_egreso(), tareo.getPeriodo_semanal(),
                    tareo.getNomPeriodoSemanal(),
                    sistemaPensionesInterface.buscarNombre(trabajador.getSistemaPensiones().toString()).getNombre(),
                    trabajador.getCussp(), trabajador.getTipoComision(), "Privado", trabajador.getNumhijos(), det,
                    trabajador.getSistemaPensiones().toString(), idObraDatosGenerales, anio, trabajador.getCondicionTrabajo(), trabajador);
            lista.add(n);
        }

        return lista;
    }

    @Override
    @Transactional
    public TareoDet buscarEnTareo(String dni, TareoCab tareo) {
        TareoDet retorno = null;
        for (TareoDet x : tareo.getLstTareoDet()) {
            if (x.getDni().trim().compareTo(dni.trim()) == 0) {
                retorno = x;
                break;
            }
        }
        return retorno;
    }

    @Override
    @Transactional
    public PlanillaDto insertarPlanilla(String codigo, String dni, String nombres, String ocupacion,
                                        String regimenLaboral, String obra, String mes, String declaracion, String segmento, Date fechaIngreso,
                                        Date fechaCese, Integer numSemanaQuincena, String periodoSemanal, String sistemaPensiones, String cussp,
                                        String tipoComision, String tipoContratoEssalud, Integer numHijos, TareoDet tareo,
                                        String codigoSistemaPensiones, Integer idObraDatosGenerales, String anio, BigDecimal condicionTrabajo, Trabajadores trabajador) {
        PlanillaDto p = new PlanillaDto();

//        p.setCodigo(codigo);
        p.setDni(dni);
        p.setCodigo(p.getDni() + "ORIPLAN");
        p.setAnio(anio);
        p.setNombresCompletos(nombres);
        p.setSegmento(segmento);
        p.setOcupacion(ocupacion);
        p.setRegimenLabroal(regimenLaboral);
        p.setObra(obra);
        p.setDeclaracion(declaracion);
        p.setMes(mes);
        p.setFechaIngreso(fechaIngreso);
        p.setFechaCese(fechaCese);
        p.setTipoGrati(tipoGratificacion(mes));
        p.setNumSemanaQuincena(numSemanaQuincena);
        p.setPeriodoSemanal(periodoSemanal);
        p.setSistemaPensiones(sistemaPensiones);
        p.setCussp(cussp);
        p.setTipoComision(tipoComision);
        p.setTipoContratoEssalud(tipoContratoEssalud);
        p.setNumHijos(numHijos);
        // p.setBonifAltura(new BigDecimal("0"));
        // p.setBoniAltitud(new BigDecimal("0"));
        // p.setContactoAguaCeleste(new BigDecimal("0"));
        // p.setAguasHervidas(new BigDecimal("0"));

        p.setDiasTrabajados(funciones.dividirRedondear(tareo.getTotalesHorasNormales(), new BigDecimal("8"), 7));
        p.setNumNormalHrsTrabajadas(tareo.getTotalesHorasNormales());
        p.setNumHorasExtrasSesenta(tareo.getTotalesExtrasSesenta());
        p.setNumHorasExtrasCien(tareo.getTotalesExtrasCien());

        p.setRefrigerio(calculoDelRefrigerio(ocupacion, tareo));
        p.setNumDiasFeriadosDescanso(tareo.getNumDiasFeriadosDescansados());
        p.setNumDiasFeriadosTrabaj(tareo.getNumDiasFeriadosTrabajados());
        p.setNumDiasDescansoMedico(tareo.getNumDiasDescansoMedico());
        // *********************CALCULOS*******************
        p.setJornalBasicoCal(calculoJornalBasico(ocupacion, p.getDiasTrabajados()));
        p.setBasicoCal(p.getJornalBasicoCal());
        p.setBucCal(calculoBuc(ocupacion, p.getDiasTrabajados()));
        p.setDominicalCal(calculoDomincal(ocupacion, p.getDiasTrabajados()));
        p.setVacacionalCal(calculoVacacional(ocupacion, p.getDiasTrabajados()));
        p.setAsignEscolar_cal(calculoAsigEscolar(ocupacion, p.getDiasTrabajados(), numHijos));
        p.setFeriadosDescansosCal(calculoFeriadosDescansos(ocupacion, tareo.getNumDiasFeriadosDescansados()));
        p.setDescansoMedicoCal(calculoDescansoMedico(ocupacion, tareo.getNumDiasDescansoMedico()));
        p.setMovilidadCal(calculoMovilidad(ocupacion, p.getDiasTrabajados()));
        p.setIndemnizacionCal(calculoIndemnizacion(ocupacion, p.getDiasTrabajados()));
        p.setGratificacionCal(calculoGrati(ocupacion, p.getDiasTrabajados(), p.getTipoGrati()));
        p.setBonifExtraTemporalCal(calculoBonifExtraTemp(p.getGratificacionCal()));
        p.setHorasExtrasSesentaCal(calculoHorasExtrasSesenta(ocupacion, p.getNumHorasExtrasSesenta()));
        p.setHorasExtrasCienCal(calculoHorasExtrasCien(ocupacion, p.getNumHorasExtrasCien()));
        p.setTurnoNocheCal(calculoTurnoNoche(ocupacion, p.getHorarioNocturno())); // el horario nocturno no se está
        // seteando
        p.setCondicionTrabajoCal(condicionTrabajo);// acomodar esto
        // ¿horas extras fija?
        p.setTotalRemuneracionCal(funciones.sumarVeinte(p.getJornalBasicoCal(), p.getBucCal(), p.getDominicalCal(),
                p.getVacacionalCal(), p.getAsignEscolar_cal(), p.getFeriadosDescansosCal(),
                p.getFeriadosTrabajadosCal(), p.getDescansoMedicoCal(), p.getMovilidadCal(), p.getIndemnizacionCal(),
                p.getGratificacionCal(), p.getBonifExtraTemporalCal(), p.getHorasExtrasSesentaCal(),
                p.getHorasExtrasCienCal(), p.getCondicionTrabajoCal(), p.getRefrigerio(), null, 7));
        p.setTotalAfecto(
                calculoTotalRemu(p.getJornalBasicoCal(), p.getBucCal(), p.getDominicalCal(), p.getVacacionalCal(),
                        p.getFeriadosDescansosCal(), p.getFeriadosTrabajadosCal(), p.getDescansoMedicoCal(),
                        p.getHorasExtrasSesentaCal(), p.getHorasExtrasCienCal(), null, null, null, null, null, 7));
        p.setOnp(calculoOnp(codigoSistemaPensiones, p.getTotalAfecto()));

        /*if (p.getDni().compareTo("44487697") == 0){
            System.out.println("DNI: " + dni);
            System.out.println("codigoSistemaPensiones: " + codigoSistemaPensiones + " tipoComision: " + tipoComision + " p.getTotalAfecto(): " + p.getTotalAfecto());
        }*/

        p.setComisionAfp(calculoComisionAFP(codigoSistemaPensiones, tipoComision, p.getTotalAfecto()));
        p.setPrimaAfp(calculoAfpPrima(codigoSistemaPensiones, p.getTotalAfecto()));
        p.setAporteAdicAfp(calculoAporteAdicAfp(codigoSistemaPensiones, p.getTotalAfecto()));
        p.setAporteObligatorioAfp(calculoAporteObliAfp(codigoSistemaPensiones, p.getTotalAfecto()));
        p.setConafovicer(calculoConafovicer(ocupacion, p.getDiasTrabajados()));
        p.setAdelanto(tareo.getAdelanto());
        p.setCuotaSindical(calculoCuotaSindical(idObraDatosGenerales, tareo, p.getDni())); //a

        p.setTotalDsctos(calculoTotalRemu(p.getOnp(), p.getComisionAfp(), p.getPrimaAfp(), p.getAporteObligatorioAfp(),
                p.getAporteAdicAfp(), p.getConafovicer(), p.getAdelanto(), p.getCuotaSindical(), null, null, null, null, null, null,
                7));
        p.setNetoPagar(funciones.restarRedondear(p.getTotalRemuneracionCal(), p.getTotalDsctos(), 7));
        p.setEssalud_nueve(funciones.multiRedondear(p.getTotalAfecto(), new BigDecimal("0.09"), 7));
        p.setSctrEssaludEps(calculoScrEssalud(p.getTotalAfecto()));
        p.setSctrPensionesPrivado(calculoScrPensionesPrivadas(p.getTotalAfecto()));
        p.setSctrEssaludVida(calculoEssaludVida(idObraDatosGenerales));
        p.setAporteAdicionalAfp(p.getAporteAdicAfp());
        p.setTotalAporteEmpleados(calculoTotalRemu(p.getEssalud_nueve(), p.getStcr(), p.getSctrEssaludEps(),
                p.getSctrPensionesPrivado(), p.getSctrEssaludVida(), p.getAporteAdicionalAfp(), null, null, null, null,
                null, null, null, null, 7));
        p.setEntidadFinanciera(trabajador.getEntidadFinanciera());
        p.setNumCuentaBancaria(trabajador.getNumCuentaBancaria());
        p.setCci(trabajador.getCci());

        return p;

    }

    public BigDecimal calculoCuotaSindical(Integer idObraDatosGenerales, TareoDet tareo, String dni) {

        if (tareo.getSindicato() == 1) {
            System.out.println("dni: " + dni);
            System.out.println("sindicato: " + tareo.getSindicato());
            System.out.println("categoria: " + tareo.getCategoria());
            DatosGenerales datosGenerales = datosService.findByIdCommon(idObraDatosGenerales);
            if (tareo.getCategoria().compareTo("Peón") == 0) {
                System.out.println("Peón");
                return datosGenerales.getPeon();
            } else if (tareo.getCategoria().compareTo("Oficial") == 0) {
                System.out.println("Oficial");
                return datosGenerales.getOficial();
            } else if (tareo.getCategoria().compareTo("Operario") == 0) {
                System.out.println("Operario");
                return datosGenerales.getOperario();
            }
        }
        return new BigDecimal("0");
    }

    public BigDecimal calculoDelRefrigerio(String ocupacion, TareoDet tareo) {

        TablaSalarial ts = tablaService.porCategoria(ocupacion);

        if (tareo.getTotalesExtrasCien().compareTo(new BigDecimal("0")) == 0
                || tareo.getTotalesExtrasCien().compareTo(new BigDecimal("0.00")) == 0) {
            return funciones.multiRedondear(tareo.getTotalesHorasExtras(), ts.getHorasExtrasFijas(), 6);
        } else {
            return new BigDecimal("0");
        }

    }

    public BigDecimal calculoEssaludVida(Integer idObraDatosGenerales) {

        DatosGenerales ts = datosService.findByIdCommon(idObraDatosGenerales);
        return ts.getImporte();
    }

    public BigDecimal calculoScrPensionesPrivadas(BigDecimal totalafecto) {

        Sctr ts = sctrService.buscar();
        return funciones.multiRedondear(ts.getSctrPensiones(), totalafecto, 7);

    }

    public BigDecimal calculoScrEssalud(BigDecimal totalafecto) {

        Sctr ts = sctrService.buscar();
        return funciones.multiRedondear(ts.getSctrEssalud(), totalafecto, 7);

    }

    public BigDecimal calculoConafovicer(String ocupacion, BigDecimal diasTrabajados) {

        TablaSalarial ts = tablaService.porCategoria(ocupacion);
        return funciones.multiRedondear(ts.getConafovicer(), diasTrabajados, 7);

    }

    public BigDecimal calculoAporteAdicAfp(String codigo, BigDecimal totalAfecto) {
        if (codigo.compareTo("2") == 0) {
            return null;
        } else {
            TasasPensiones ts = tasaPensiones.buscar(codigo);
            return funciones.multiRedondear(ts.getAporteAdicionalAfp(), totalAfecto, 7);
        }

    }

    public BigDecimal calculoAporteObliAfp(String codigo, BigDecimal totalAfecto) {
        if (codigo.compareTo("2") == 0) {
            return null;
        } else {
            TasasPensiones ts = tasaPensiones.buscar(codigo);
            return funciones.multiRedondear(ts.getAporte(), totalAfecto, 7);
        }

    }

    public BigDecimal calculoAfpPrima(String codigo, BigDecimal totalAfecto) {
        if (codigo.compareTo("2") == 0) {
            return null;
        } else {
            TasasPensiones ts = tasaPensiones.buscar(codigo);
            return funciones.multiRedondear(ts.getPrima(), totalAfecto, 7);
        }

    }

    public BigDecimal calculoComisionAFP(String codigo, String tipoComision, BigDecimal totalAfecto) {
        if (codigo.compareTo("2") == 0) {
            return null;
        } else {
            System.out.println("¨**************************************************");
            if (tipoComision.compareTo("Comisión flujo") == 0) {
                System.out.println("código: " + codigo + " tipo comisión: " + tipoComision + " Total afecto: " + totalAfecto);
                TasasPensiones ts = tasaPensiones.buscar(codigo);
                System.out.println(":: " + funciones.multiRedondear(ts.getComision(), totalAfecto, 7));
                return funciones.multiRedondear(ts.getComision(), totalAfecto, 7);
            } else {
                TasasPensiones ts = tasaPensiones.buscar(codigo);
                return funciones.multiRedondear(ts.getComisionMixta(), totalAfecto, 7);
            }

        }

    }

    public BigDecimal calculoOnp(String codigo, BigDecimal totalAfecto) {
        if (codigo.compareTo("2") == 0) {
            TasasPensiones ts = tasaPensiones.buscar(codigo);
            return funciones.multiRedondear(ts.getTotal(), totalAfecto, 7);
        } else {
            return null;
        }

    }

    public BigDecimal calculoTotalRemu(BigDecimal n, BigDecimal n2, BigDecimal n3, BigDecimal n4, BigDecimal n5,
                                       BigDecimal n6, BigDecimal n7, BigDecimal n8, BigDecimal n9, BigDecimal n10, BigDecimal n11, BigDecimal n12,
                                       BigDecimal n13, BigDecimal n14, Integer decimales) {

        n = n != null ? n : new BigDecimal("0");
        n2 = n2 != null ? n2 : new BigDecimal("0");
        n3 = n3 != null ? n3 : new BigDecimal("0");
        n4 = n4 != null ? n4 : new BigDecimal("0");
        n5 = n5 != null ? n5 : new BigDecimal("0");
        n6 = n6 != null ? n6 : new BigDecimal("0");
        n7 = n7 != null ? n7 : new BigDecimal("0");
        n8 = n8 != null ? n8 : new BigDecimal("0");
        n9 = n9 != null ? n9 : new BigDecimal("0");
        n10 = n10 != null ? n10 : new BigDecimal("0");
        n11 = n11 != null ? n11 : new BigDecimal("0");
        n12 = n12 != null ? n12 : new BigDecimal("0");
        n13 = n13 != null ? n13 : new BigDecimal("0");
        n14 = n14 != null ? n14 : new BigDecimal("0");

        BigDecimal resultadoSuma;
        try {
            BigDecimal a = new BigDecimal("" + n);
            BigDecimal b = new BigDecimal("" + n2);
            BigDecimal c = new BigDecimal("" + n3);
            BigDecimal d = new BigDecimal("" + n4);
            BigDecimal e = new BigDecimal("" + n5);
            BigDecimal f = new BigDecimal("" + n6);
            BigDecimal g = new BigDecimal("" + n7);
            BigDecimal h = new BigDecimal("" + n8);
            BigDecimal i = new BigDecimal("" + n9);
            BigDecimal j = new BigDecimal("" + n10);
            BigDecimal k = new BigDecimal("" + n11);
            BigDecimal l = new BigDecimal("" + n12);
            BigDecimal m = new BigDecimal("" + n13);
            BigDecimal o = new BigDecimal("" + n14);

            resultadoSuma = a.add(b).add(c).add(d).add(e).add(f).add(g).add(h).add(i).add(j).add(k).add(l).add(m)
                    .add(o);
            // HALF_DOWN Redondea hacia arriba solo si el decimal es > a 0.5
            // HALF_up Redondea hacia arriba solo si el decimal es > a 0.5
            resultadoSuma = resultadoSuma.setScale(decimales, RoundingMode.HALF_UP);
            System.out.print("Resultado Suma: " + resultadoSuma + "\n");
        } catch (Exception e) {
            resultadoSuma = null;
        }
        System.out.println("RESULTADO ES = " + resultadoSuma);
        return resultadoSuma;
    }

    public BigDecimal calculoTurnoNoche(String ocupacion, BigDecimal turnoNoche) {
        TablaSalarial ts = tablaService.porCategoria(ocupacion);
        return funciones.multiRedondear(ts.getHorarioNocturno(), turnoNoche, 7);
    }

    public BigDecimal calculoHorasExtrasCien(String ocupacion, BigDecimal numHorasExtrasCien) {
        TablaSalarial ts = tablaService.porCategoria(ocupacion);
        return funciones.multiRedondear(ts.getHorasExtrasCien(), numHorasExtrasCien, 7);
    }

    public BigDecimal calculoHorasExtrasSesenta(String ocupacion, BigDecimal numHorasExtrasSesenta) {
        TablaSalarial ts = tablaService.porCategoria(ocupacion);
        return funciones.multiRedondear(ts.getHoras_extras_sesenta(), numHorasExtrasSesenta, 7);
    }

    public BigDecimal calculoBonifExtraTemp(BigDecimal grati) {
        return funciones.multiRedondear(grati, new BigDecimal("0.09"), 7);
    }

    public BigDecimal calculoGrati(String ocupacion, BigDecimal diastrabajados, String tipograti) {
        TablaSalarial ts = tablaService.porCategoria(ocupacion);
        if (tipograti.trim().compareTo("Patrias") == 0) {
            BigDecimal primero = funciones.multiRedondear(ts.getFiestasPatrias(), diastrabajados, 7);
            BigDecimal segundo = funciones.dividirRedondear(ts.getFiestasPatrias(), new BigDecimal("6"), 7);
            BigDecimal tercero = funciones.multiRedondear(segundo, diastrabajados, 7);
            return funciones.sumarRedondear(primero, tercero, 7);
        } else {
            BigDecimal primero = funciones.multiRedondear(ts.getFiestas_navidenas(), diastrabajados, 7);
            BigDecimal segundo = funciones.dividirRedondear(ts.getFiestas_navidenas(), new BigDecimal("6"), 7);
            BigDecimal tercero = funciones.multiRedondear(segundo, diastrabajados, 7);
            return funciones.sumarRedondear(primero, tercero, 7);
        }

    }

    public BigDecimal calculoIndemnizacion(String ocupacion, BigDecimal diastrabajados) {
        TablaSalarial ts = tablaService.porCategoria(ocupacion);
        return funciones.multiRedondear(ts.getCts(), diastrabajados, 7);
    }

    public BigDecimal calculoMovilidad(String ocupacion, BigDecimal diastrabajados) {
        TablaSalarial ts = tablaService.porCategoria(ocupacion);
        return funciones.multiRedondear(ts.getMovilidad(), diastrabajados, 7);
    }

    public BigDecimal calculoDescansoMedico(String ocupacion, BigDecimal numDiasDescansoMedico) {

        TablaSalarial ts = tablaService.porCategoria(ocupacion);
        return funciones.multiRedondear(ts.getDescansoMedico(), numDiasDescansoMedico, 7);
    }

    public BigDecimal calculoFeriadosDescansos(String ocupacion, BigDecimal numDiasFeriadosDescansados) {

        TablaSalarial ts = tablaService.porCategoria(ocupacion);
        return funciones.multiRedondear(ts.getFeriadosDescansados(), numDiasFeriadosDescansados, 7);
    }

    public BigDecimal calculoAsigEscolar(String ocupacion, BigDecimal diasTrabajados, Integer hijos) {
        String hijitos = "" + (hijos != null ? hijos : "0");
        TablaSalarial ts = tablaService.porCategoria(ocupacion);
        if ((new BigDecimal(hijitos)).compareTo(new BigDecimal("0")) == 1) {
            BigDecimal primero = (funciones.multiRedondear(ts.getAsignacionEscolar(), (new BigDecimal(hijitos)), 7));
            BigDecimal segundo = funciones.multiRedondear(primero, diasTrabajados, 7);
            return funciones.sumarRedondear(primero, segundo, 7);

        } else {
            return new BigDecimal("0");
        }

    }

    public BigDecimal calculoVacacional(String ocupacion, BigDecimal diasTrabajados) {

        TablaSalarial ts = tablaService.porCategoria(ocupacion);
        return funciones.multiRedondear(ts.getVacaciones(), diasTrabajados, 7);
    }

    public BigDecimal calculoDomincal(String ocupacion, BigDecimal diasTrabajados) {

        TablaSalarial ts = tablaService.porCategoria(ocupacion);
        return funciones.multiRedondear(ts.getDominical(), diasTrabajados, 7);
    }

    public BigDecimal calculoJornalBasico(String ocupacion, BigDecimal diasTrabajados) {

        TablaSalarial ts = tablaService.porCategoria(ocupacion);

        return funciones.multiRedondear(ts.getJornalBasico(), diasTrabajados, 7);
        // if (ocupacion.trim().compareTo("Operario")==0) {

        // }else{
        // if (ocupacion.trim().compareTo("Oficial")==0) {

        // }else {
        // if (ocupacion.trim().compareTo("Peón")==0) {

        // }
        // }
        // }

    }

    public BigDecimal calculoBuc(String ocupacion, BigDecimal diasTrabajados) {

        TablaSalarial ts = tablaService.porCategoria(ocupacion);
        return funciones.multiRedondear(ts.getBuc(), diasTrabajados, 7);
    }

    public String tipoGratificacion(String mes) {

        List<MesDto> list = new ArrayList<MesDto>();
        MesDto m = new MesDto();
        m.setId(1);
        m.setName("ENERO");
        list.add(m);
        m = new MesDto();
        m.setId(2);
        m.setName("FEBRERO");
        list.add(m);
        m = new MesDto();
        m.setId(3);
        m.setName("MARZO");
        list.add(m);
        m = new MesDto();
        m.setId(4);
        m.setName("ABRIL");
        list.add(m);
        m = new MesDto();
        m.setId(5);
        m.setName("MAYO");
        list.add(m);
        m = new MesDto();
        m.setId(6);
        m.setName("JUNIO");
        list.add(m);
        m = new MesDto();
        m.setId(7);
        m.setName("JULIO");
        list.add(m);
        m = new MesDto();
        m.setId(8);
        m.setName("AGOSTO");
        list.add(m);
        m = new MesDto();
        m.setId(9);
        m.setName("SEPTIEMBRE");
        list.add(m);
        m = new MesDto();
        m.setId(10);
        m.setName("OCTUBRE");
        list.add(m);
        m = new MesDto();
        m.setId(11);
        m.setName("NOVIEMBRE");
        list.add(m);
        m = new MesDto();
        m.setId(12);
        m.setName("DICIEMBRE");
        list.add(m);

        Integer retorno = null;
        for (MesDto mesDto : list) {
            if (mes.trim().compareTo(mesDto.getName().trim()) == 0) {
                retorno = mesDto.getId();
                break;
            }
        }

        return retorno < 7 ? "Patrias" : "Navideñas";

    }

    @Override
    public boolean buscar(String dni, String nomsemana, String obra) {
        // TODO Auto-generated method stub
        return libroDao.buscar(dni, nomsemana, obra) != null;
    }

    @Override
    public PlanillaExcel getBuscar(String dni, String nomsemana, String obra) {
        return libroDao.buscar(dni, nomsemana, obra);
    }

    @Override
    public List<PlanillaExcel> getPlaniillitaPorDni(String dni, String semana, String obra) {
        // TODO Auto-generated method stub
        return libroDao.buscarparaBoletaPorDni(dni, semana, obra);
    }

    @Override
    public List<PlanillaRest> listarPlanillaAfp(String anio, String mes) {
        System.out.println("listarPlanillaAfp");

        List<PlanillaExcel> lstPlanillaExcel;
        List<PlanillaRest> lstPlanillaRest = new ArrayList<>();

        lstPlanillaExcel = libroDao.listarPlanillaAfp(anio, mes);

        lstPlanillaExcel.stream().forEach(result -> {

            PlanillaRest planillaRest = new PlanillaRest();
            List<Object[]> object = libroDao.planillaAfp(result.getAnio(), result.getMes(), result.getDni());


            object.stream().forEach(res -> {

                BigDecimal totalAfecto = new BigDecimal("0");
                String apellidoPaterno = "";
                String apellidoMaterno = "";
                String nombres = "";
                String[] parts = result.getNombresCompletos().trim().split(" ");

                totalAfecto = new BigDecimal(res[1].toString());

                if (parts[0] != "" || parts[0] != null) {
                    apellidoPaterno = parts[0];
                }
                if (parts[1] != "" || parts[1] != null) {
                    apellidoMaterno = parts[1];
                }
                if (parts[2] != "" || parts[2] != null) {
                    nombres = parts[2];
                }

                planillaRest.setCussp(result.getCussp());
                planillaRest.setTipoDocumento(0);
                planillaRest.setDni(result.getDni());
                planillaRest.setApellidoPaterno(apellidoPaterno);
                planillaRest.setApellidoMaterno(apellidoMaterno);
                planillaRest.setNombres(nombres);
                planillaRest.setRelacionLaboral("");
                planillaRest.setInicioRelacionLaboral("");
                planillaRest.setCeseRelacionLaboral("");
                planillaRest.setExcepcionAportar("");
                planillaRest.setRemuneracionAsegurable(totalAfecto);
                planillaRest.setAporteVoluntarioAfiConFin(new BigDecimal("0"));
                planillaRest.setAporteVoluntarioAfiSinFin(new BigDecimal("0"));
                planillaRest.setAporteVoluntarioEmpleador(new BigDecimal("0"));
                planillaRest.setTipoTrabajoRubro("");
                planillaRest.setAfp("");

                lstPlanillaRest.add(planillaRest);
            });
        });


        return lstPlanillaRest;
    }

    @Override
    public PlanillaExcel editar(Integer idDatosGenerales, String nomSemana, String dni,
                                Integer numHijos, BigDecimal condicionTrabajo, BigDecimal adelanto,
                                String declaracion) {
        DatosGenerales datosGenerales = datosService.findByIdCommon(idDatosGenerales);

        // seteando los cambios
        PlanillaExcel planillaExcel = libroDao.buscar(dni, nomSemana, datosGenerales.getNomObra());
        planillaExcel.setNumHijos(numHijos);
        planillaExcel.setCondicionTrabajoCal(condicionTrabajo);
        planillaExcel.setAdelanto(adelanto);
        planillaExcel.setDeclaracion(declaracion);

        //calculando la planilla
        planillaExcel.setAsignEscolar_cal(calculoAsigEscolar(planillaExcel.getOcupacion(), planillaExcel.getDiasTrabajados(), planillaExcel.getNumHijos()));
        planillaExcel.setTotalRemuneracionCal(
                funciones.sumarRedondear(planillaExcel.getTotalRemuneracionCal(), planillaExcel.getCondicionTrabajoCal(), 6));
        planillaExcel.setTotalDsctos(funciones.sumarRedondear(planillaExcel.getTotalDsctos(), planillaExcel.getAdelanto(), 6));
        planillaExcel.setNetoPagar(funciones.restarRedondear(planillaExcel.getTotalRemuneracionCal(), planillaExcel.getTotalDsctos(), 6));

        return planillaExcel;
    }

}
