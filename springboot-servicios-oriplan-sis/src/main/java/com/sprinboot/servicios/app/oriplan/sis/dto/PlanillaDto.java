package com.sprinboot.servicios.app.oriplan.sis.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import org.springframework.format.annotation.DateTimeFormat;

public class PlanillaDto {


    private Integer id;


    private String codigo;


    private String anio;


    private String regimenLabroal;

    private String obra;


    private String declaracion;


    private String dni;


    private String nombresCompletos;


    private String segmento;


    private String ocupacion;


    private String mes;


    private Date fechaIngreso;


    private Date fechaCese;


    private String tipoGrati;


    private Integer numSemanaQuincena;


    private String periodoSemanal;


    private String sistemaPensiones;


    private String cussp;


    private String tipoComision;


    private String tipoContratoEssalud;


    private Integer numHijos;


    private BigDecimal bonifAltura;


    private BigDecimal boniAltitud;


    private BigDecimal contactoAguaCeleste;


    private BigDecimal aguasHervidas;


    private BigDecimal horarioNocturno;


    private BigDecimal diasTrabajados;


    private BigDecimal numNormalHrsTrabajadas;


    private BigDecimal numHorasExtrasSesenta;


    private BigDecimal numHorasExtrasCien;


    private BigDecimal numDiasFeriadosDescanso;


    private BigDecimal numDiasFeriadosTrabaj;


    private BigDecimal numDiasDescansoMedico;


    private BigDecimal jornalBasicoCal;

    private BigDecimal basicoRCal;


    private BigDecimal basicoR1Cal;


    private BigDecimal basicoR2Cal;

    private BigDecimal basicoCal;


    private BigDecimal bucCal;


    private BigDecimal dominicalCal;


    private BigDecimal vacacionalCal;


    private BigDecimal asignEscolar_cal;


    private BigDecimal feriadosDescansosCal;


    private BigDecimal feriadosTrabajadosCal;


    private BigDecimal descansoMedicoCal;


    private BigDecimal movilidadCal;


    private BigDecimal indemnizacionCal;


    private BigDecimal gratificacionCal;


    private BigDecimal bonifExtraTemporalCal;


    private BigDecimal horasExtrasSesentaCal;

    private BigDecimal horasExtrasCienCal;


    private BigDecimal turnoNocheCal;


    private BigDecimal contactoAguaCal;


    private BigDecimal aguasServidasCal;


    private BigDecimal bonificacionAlturaCal;


    private BigDecimal bonificacionAltitudCal;


    private BigDecimal condicionTrabajoCal;


    private BigDecimal horasExtrasFijasCal;


    private BigDecimal totalRemuneracionCal;


    private BigDecimal totalAfecto;


    private BigDecimal onp;


    private BigDecimal comisionAfp;


    private BigDecimal primaAfp;


    private BigDecimal aporteObligatorioAfp;


    private BigDecimal aporteAdicAfp;


    private BigDecimal conafovicer;


    private BigDecimal adelanto;


    private BigDecimal cuotaSindical;


    private BigDecimal otrosDescuentos;


    private BigDecimal totalDsctos;


    private BigDecimal netoPagar;


    private BigDecimal essalud_nueve;


    private BigDecimal stcr;

    private BigDecimal sctrEssaludEps;

    private BigDecimal sctrPensionesPrivado;

    private BigDecimal sctrEssaludVida;


    private BigDecimal aporteAdicionalAfp;


    private BigDecimal totalAporteEmpleados;


    private BigDecimal refrigerio;

    private Date createAt;


    private String username;


    private Integer enabled;

    private String entidadFinanciera;

    private String numCuentaBancaria;

    private String cci;

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getCodigo() {
        return codigo;
    }


    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public String getAnio() {
        return anio;
    }


    public void setAnio(String anio) {
        this.anio = anio;
    }


    public String getRegimenLabroal() {
        return regimenLabroal;
    }


    public void setRegimenLabroal(String regimenLabroal) {
        this.regimenLabroal = regimenLabroal;
    }


    public String getObra() {
        return obra;
    }


    public void setObra(String obra) {
        this.obra = obra;
    }


    public String getDeclaracion() {
        return declaracion;
    }


    public void setDeclaracion(String declaracion) {
        this.declaracion = declaracion;
    }


    public String getDni() {
        return dni;
    }


    public void setDni(String dni) {
        this.dni = dni;
    }


    public String getNombresCompletos() {
        return nombresCompletos;
    }


    public void setNombresCompletos(String nombresCompletos) {
        this.nombresCompletos = nombresCompletos;
    }


    public String getSegmento() {
        return segmento;
    }


    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }


    public String getOcupacion() {
        return ocupacion;
    }


    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }


    public String getMes() {
        return mes;
    }


    public void setMes(String mes) {
        this.mes = mes;
    }


    public Date getFechaIngreso() {
        return fechaIngreso;
    }


    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }


    public Date getFechaCese() {
        return fechaCese;
    }


    public void setFechaCese(Date fechaCese) {
        this.fechaCese = fechaCese;
    }


    public String getTipoGrati() {
        return tipoGrati;
    }


    public void setTipoGrati(String tipoGrati) {
        this.tipoGrati = tipoGrati;
    }


    public Integer getNumSemanaQuincena() {
        return numSemanaQuincena;
    }


    public void setNumSemanaQuincena(Integer numSemanaQuincena) {
        this.numSemanaQuincena = numSemanaQuincena;
    }


    public String getPeriodoSemanal() {
        return periodoSemanal;
    }


    public void setPeriodoSemanal(String periodoSemanal) {
        this.periodoSemanal = periodoSemanal;
    }


    public String getSistemaPensiones() {
        return sistemaPensiones;
    }


    public void setSistemaPensiones(String sistemaPensiones) {
        this.sistemaPensiones = sistemaPensiones;
    }


    public String getCussp() {
        return cussp;
    }


    public void setCussp(String cussp) {
        this.cussp = cussp;
    }


    public String getTipoComision() {
        return tipoComision;
    }


    public void setTipoComision(String tipoComision) {
        this.tipoComision = tipoComision;
    }


    public String getTipoContratoEssalud() {
        return tipoContratoEssalud;
    }


    public void setTipoContratoEssalud(String tipoContratoEssalud) {
        this.tipoContratoEssalud = tipoContratoEssalud;
    }


    public Integer getNumHijos() {
        return numHijos;
    }


    public void setNumHijos(Integer numHijos) {
        this.numHijos = numHijos;
    }


    public BigDecimal getBonifAltura() {
        return bonifAltura;
    }


    public void setBonifAltura(BigDecimal bonifAltura) {
        this.bonifAltura = bonifAltura;
    }


    public BigDecimal getBoniAltitud() {
        return boniAltitud;
    }


    public void setBoniAltitud(BigDecimal boniAltitud) {
        this.boniAltitud = boniAltitud;
    }


    public BigDecimal getContactoAguaCeleste() {
        return contactoAguaCeleste;
    }


    public void setContactoAguaCeleste(BigDecimal contactoAguaCeleste) {
        this.contactoAguaCeleste = contactoAguaCeleste;
    }


    public BigDecimal getAguasHervidas() {
        return aguasHervidas;
    }


    public void setAguasHervidas(BigDecimal aguasHervidas) {
        this.aguasHervidas = aguasHervidas;
    }


    public BigDecimal getHorarioNocturno() {
        return horarioNocturno;
    }


    public void setHorarioNocturno(BigDecimal horarioNocturno) {
        this.horarioNocturno = horarioNocturno;
    }


    public BigDecimal getDiasTrabajados() {
        return diasTrabajados;
    }


    public void setDiasTrabajados(BigDecimal diasTrabajados) {
        this.diasTrabajados = diasTrabajados;
    }


    public BigDecimal getNumNormalHrsTrabajadas() {
        return numNormalHrsTrabajadas;
    }


    public void setNumNormalHrsTrabajadas(BigDecimal numNormalHrsTrabajadas) {
        this.numNormalHrsTrabajadas = numNormalHrsTrabajadas;
    }


    public BigDecimal getNumHorasExtrasSesenta() {
        return numHorasExtrasSesenta;
    }


    public void setNumHorasExtrasSesenta(BigDecimal numHorasExtrasSesenta) {
        this.numHorasExtrasSesenta = numHorasExtrasSesenta;
    }


    public BigDecimal getNumHorasExtrasCien() {
        return numHorasExtrasCien;
    }


    public void setNumHorasExtrasCien(BigDecimal numHorasExtrasCien) {
        this.numHorasExtrasCien = numHorasExtrasCien;
    }


    public BigDecimal getNumDiasFeriadosDescanso() {
        return numDiasFeriadosDescanso;
    }


    public void setNumDiasFeriadosDescanso(BigDecimal numDiasFeriadosDescanso) {
        this.numDiasFeriadosDescanso = numDiasFeriadosDescanso;
    }


    public BigDecimal getNumDiasFeriadosTrabaj() {
        return numDiasFeriadosTrabaj;
    }


    public void setNumDiasFeriadosTrabaj(BigDecimal numDiasFeriadosTrabaj) {
        this.numDiasFeriadosTrabaj = numDiasFeriadosTrabaj;
    }


    public BigDecimal getNumDiasDescansoMedico() {
        return numDiasDescansoMedico;
    }


    public void setNumDiasDescansoMedico(BigDecimal numDiasDescansoMedico) {
        this.numDiasDescansoMedico = numDiasDescansoMedico;
    }


    public BigDecimal getJornalBasicoCal() {
        return jornalBasicoCal;
    }


    public void setJornalBasicoCal(BigDecimal jornalBasicoCal) {
        this.jornalBasicoCal = jornalBasicoCal;
    }


    public BigDecimal getBasicoRCal() {
        return basicoRCal;
    }


    public void setBasicoRCal(BigDecimal basicoRCal) {
        this.basicoRCal = basicoRCal;
    }


    public BigDecimal getBasicoR1Cal() {
        return basicoR1Cal;
    }


    public void setBasicoR1Cal(BigDecimal basicoR1Cal) {
        this.basicoR1Cal = basicoR1Cal;
    }


    public BigDecimal getBasicoR2Cal() {
        return basicoR2Cal;
    }


    public void setBasicoR2Cal(BigDecimal basicoR2Cal) {
        this.basicoR2Cal = basicoR2Cal;
    }


    public BigDecimal getBasicoCal() {
        return basicoCal;
    }


    public void setBasicoCal(BigDecimal basicoCal) {
        this.basicoCal = basicoCal;
    }


    public BigDecimal getBucCal() {
        return bucCal;
    }


    public void setBucCal(BigDecimal bucCal) {
        this.bucCal = bucCal;
    }


    public BigDecimal getDominicalCal() {
        return dominicalCal;
    }


    public void setDominicalCal(BigDecimal dominicalCal) {
        this.dominicalCal = dominicalCal;
    }


    public BigDecimal getVacacionalCal() {
        return vacacionalCal;
    }


    public void setVacacionalCal(BigDecimal vacacionalCal) {
        this.vacacionalCal = vacacionalCal;
    }


    public BigDecimal getAsignEscolar_cal() {
        return asignEscolar_cal;
    }


    public void setAsignEscolar_cal(BigDecimal asignEscolar_cal) {
        this.asignEscolar_cal = asignEscolar_cal;
    }


    public BigDecimal getFeriadosDescansosCal() {
        return feriadosDescansosCal;
    }


    public void setFeriadosDescansosCal(BigDecimal feriadosDescansosCal) {
        this.feriadosDescansosCal = feriadosDescansosCal;
    }


    public BigDecimal getFeriadosTrabajadosCal() {
        return feriadosTrabajadosCal;
    }


    public void setFeriadosTrabajadosCal(BigDecimal feriadosTrabajadosCal) {
        this.feriadosTrabajadosCal = feriadosTrabajadosCal;
    }


    public BigDecimal getDescansoMedicoCal() {
        return descansoMedicoCal;
    }


    public void setDescansoMedicoCal(BigDecimal descansoMedicoCal) {
        this.descansoMedicoCal = descansoMedicoCal;
    }


    public BigDecimal getMovilidadCal() {
        return movilidadCal;
    }


    public void setMovilidadCal(BigDecimal movilidadCal) {
        this.movilidadCal = movilidadCal;
    }


    public BigDecimal getIndemnizacionCal() {
        return indemnizacionCal;
    }


    public void setIndemnizacionCal(BigDecimal indemnizacionCal) {
        this.indemnizacionCal = indemnizacionCal;
    }


    public BigDecimal getGratificacionCal() {
        return gratificacionCal;
    }


    public void setGratificacionCal(BigDecimal gratificacionCal) {
        this.gratificacionCal = gratificacionCal;
    }


    public BigDecimal getBonifExtraTemporalCal() {
        return bonifExtraTemporalCal;
    }


    public void setBonifExtraTemporalCal(BigDecimal bonifExtraTemporalCal) {
        this.bonifExtraTemporalCal = bonifExtraTemporalCal;
    }


    public BigDecimal getHorasExtrasSesentaCal() {
        return horasExtrasSesentaCal;
    }


    public void setHorasExtrasSesentaCal(BigDecimal horasExtrasSesentaCal) {
        this.horasExtrasSesentaCal = horasExtrasSesentaCal;
    }


    public BigDecimal getHorasExtrasCienCal() {
        return horasExtrasCienCal;
    }


    public void setHorasExtrasCienCal(BigDecimal horasExtrasCienCal) {
        this.horasExtrasCienCal = horasExtrasCienCal;
    }


    public BigDecimal getTurnoNocheCal() {
        return turnoNocheCal;
    }


    public void setTurnoNocheCal(BigDecimal turnoNocheCal) {
        this.turnoNocheCal = turnoNocheCal;
    }


    public BigDecimal getContactoAguaCal() {
        return contactoAguaCal;
    }


    public void setContactoAguaCal(BigDecimal contactoAguaCal) {
        this.contactoAguaCal = contactoAguaCal;
    }


    public BigDecimal getAguasServidasCal() {
        return aguasServidasCal;
    }


    public void setAguasServidasCal(BigDecimal aguasServidasCal) {
        this.aguasServidasCal = aguasServidasCal;
    }


    public BigDecimal getBonificacionAlturaCal() {
        return bonificacionAlturaCal;
    }


    public void setBonificacionAlturaCal(BigDecimal bonificacionAlturaCal) {
        this.bonificacionAlturaCal = bonificacionAlturaCal;
    }


    public BigDecimal getBonificacionAltitudCal() {
        return bonificacionAltitudCal;
    }


    public void setBonificacionAltitudCal(BigDecimal bonificacionAltitudCal) {
        this.bonificacionAltitudCal = bonificacionAltitudCal;
    }


    public BigDecimal getCondicionTrabajoCal() {
        return condicionTrabajoCal;
    }


    public void setCondicionTrabajoCal(BigDecimal condicionTrabajoCal) {
        this.condicionTrabajoCal = condicionTrabajoCal;
    }


    public BigDecimal getHorasExtrasFijasCal() {
        return horasExtrasFijasCal;
    }


    public void setHorasExtrasFijasCal(BigDecimal horasExtrasFijasCal) {
        this.horasExtrasFijasCal = horasExtrasFijasCal;
    }


    public BigDecimal getTotalRemuneracionCal() {
        return totalRemuneracionCal;
    }


    public void setTotalRemuneracionCal(BigDecimal totalRemuneracionCal) {
        this.totalRemuneracionCal = totalRemuneracionCal;
    }


    public BigDecimal getTotalAfecto() {
        return totalAfecto;
    }


    public void setTotalAfecto(BigDecimal totalAfecto) {
        this.totalAfecto = totalAfecto;
    }


    public BigDecimal getOnp() {
        return onp;
    }


    public void setOnp(BigDecimal onp) {
        this.onp = onp;
    }


    public BigDecimal getComisionAfp() {
        return comisionAfp;
    }


    public void setComisionAfp(BigDecimal comisionAfp) {
        this.comisionAfp = comisionAfp;
    }


    public BigDecimal getPrimaAfp() {
        return primaAfp;
    }


    public void setPrimaAfp(BigDecimal primaAfp) {
        this.primaAfp = primaAfp;
    }


    public BigDecimal getAporteObligatorioAfp() {
        return aporteObligatorioAfp;
    }


    public void setAporteObligatorioAfp(BigDecimal aporteObligatorioAfp) {
        this.aporteObligatorioAfp = aporteObligatorioAfp;
    }


    public BigDecimal getAporteAdicAfp() {
        return aporteAdicAfp;
    }


    public void setAporteAdicAfp(BigDecimal aporteAdicAfp) {
        this.aporteAdicAfp = aporteAdicAfp;
    }


    public BigDecimal getConafovicer() {
        return conafovicer;
    }


    public void setConafovicer(BigDecimal conafovicer) {
        this.conafovicer = conafovicer;
    }


    public BigDecimal getAdelanto() {
        return adelanto;
    }


    public void setAdelanto(BigDecimal adelanto) {
        this.adelanto = adelanto;
    }


    public BigDecimal getCuotaSindical() {
        return cuotaSindical;
    }


    public void setCuotaSindical(BigDecimal cuotaSindical) {
        this.cuotaSindical = cuotaSindical;
    }


    public BigDecimal getOtrosDescuentos() {
        return otrosDescuentos;
    }


    public void setOtrosDescuentos(BigDecimal otrosDescuentos) {
        this.otrosDescuentos = otrosDescuentos;
    }


    public BigDecimal getTotalDsctos() {
        return totalDsctos;
    }


    public void setTotalDsctos(BigDecimal totalDsctos) {
        this.totalDsctos = totalDsctos;
    }


    public BigDecimal getNetoPagar() {
        return netoPagar;
    }


    public void setNetoPagar(BigDecimal netoPagar) {
        this.netoPagar = netoPagar;
    }


    public BigDecimal getEssalud_nueve() {
        return essalud_nueve;
    }


    public void setEssalud_nueve(BigDecimal essalud_nueve) {
        this.essalud_nueve = essalud_nueve;
    }


    public BigDecimal getStcr() {
        return stcr;
    }


    public void setStcr(BigDecimal stcr) {
        this.stcr = stcr;
    }


    public BigDecimal getSctrEssaludEps() {
        return sctrEssaludEps;
    }


    public void setSctrEssaludEps(BigDecimal sctrEssaludEps) {
        this.sctrEssaludEps = sctrEssaludEps;
    }


    public BigDecimal getSctrPensionesPrivado() {
        return sctrPensionesPrivado;
    }


    public void setSctrPensionesPrivado(BigDecimal sctrPensionesPrivado) {
        this.sctrPensionesPrivado = sctrPensionesPrivado;
    }


    public BigDecimal getSctrEssaludVida() {
        return sctrEssaludVida;
    }


    public void setSctrEssaludVida(BigDecimal sctrEssaludVida) {
        this.sctrEssaludVida = sctrEssaludVida;
    }


    public BigDecimal getAporteAdicionalAfp() {
        return aporteAdicionalAfp;
    }


    public void setAporteAdicionalAfp(BigDecimal aporteAdicionalAfp) {
        this.aporteAdicionalAfp = aporteAdicionalAfp;
    }


    public BigDecimal getTotalAporteEmpleados() {
        return totalAporteEmpleados;
    }


    public void setTotalAporteEmpleados(BigDecimal totalAporteEmpleados) {
        this.totalAporteEmpleados = totalAporteEmpleados;
    }


    public BigDecimal getRefrigerio() {
        return refrigerio;
    }


    public void setRefrigerio(BigDecimal refrigerio) {
        this.refrigerio = refrigerio;
    }


    public Date getCreateAt() {
        return createAt;
    }


    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public Integer getEnabled() {
        return enabled;
    }


    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getEntidadFinanciera() {
        return entidadFinanciera;
    }

    public void setEntidadFinanciera(String entidadFinanciera) {
        this.entidadFinanciera = entidadFinanciera;
    }

    public String getNumCuentaBancaria() {
        return numCuentaBancaria;
    }

    public void setNumCuentaBancaria(String numCuentaBancaria) {
        this.numCuentaBancaria = numCuentaBancaria;
    }

    public String getCci() {
        return cci;
    }

    public void setCci(String cci) {
        this.cci = cci;
    }
}
