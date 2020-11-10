package com.sprinboot.servicios.oriplan.commons.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "planilla_excel")
public class PlanillaExcel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "anio")
    private String anio;

    @Column(length = 100, name = "regimen_laboral")
    private String regimenLabroal;

    @Column(length = 8000, name = "obra")
    private String obra;

    @Column(length = 20, name = "declaracion")
    private String declaracion;

    @Column(name = "dni")
    private String dni;

    @Column(name = "nombres_completos")
    private String nombresCompletos;

    @Column(name = "segmento")
    private String segmento;

    @Column(name = "ocupacion")
    private String ocupacion;

    @Column(name = "mes")
    private String mes;

    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaIngreso;


    @Column(name = "fecha_cese")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaCese;

    @Column(name = "tipo_grati")
    private String tipoGrati;

    @Column(name = "num_semana_quincena")
    private Integer numSemanaQuincena;

    @Column(name = "periodo_semanal")
    private String periodoSemanal;

    @Column(name = "sistema_pensiones")
    private String sistemaPensiones;

    @Column(name = "cussp")
    private String cussp;

    @Column(name = "tipo_comision")
    private String tipoComision;

    @Column(name = "tipo_contrato_essalud")
    private String tipoContratoEssalud;

    @Column(name = "num_hijos")
    private Integer numHijos;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "bonif_altura")
    private BigDecimal bonifAltura;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "boni_altitud")
    private BigDecimal boniAltitud;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "contacto_agua_celeste")
    private BigDecimal contactoAguaCeleste;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "aguas_hervidas")
    private BigDecimal aguasHervidas;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "horario_nocturno")
    private BigDecimal horarioNocturno;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "dias_trabajados")
    private BigDecimal diasTrabajados;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "num_normal_hrs_trabajadas")
    private BigDecimal numNormalHrsTrabajadas;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "num_horas_extras_sesenta")
    private BigDecimal numHorasExtrasSesenta;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "num_horas_extras_cien")
    private BigDecimal numHorasExtrasCien;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "num_dias_feriados_descanso")
    private BigDecimal numDiasFeriadosDescanso;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "num_dias_feriados_trabaj")
    private BigDecimal numDiasFeriadosTrabaj;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "num_dias_descanso_medico")
    private BigDecimal numDiasDescansoMedico;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "jornal_basico_cal")
    private BigDecimal jornalBasicoCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "basico_r_cal")
    private BigDecimal basicoRCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "basico_r1_cal")
    private BigDecimal basicoR1Cal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "basico_r2_cal")
    private BigDecimal basicoR2Cal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "basico_cal")
    private BigDecimal basicoCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "buc_cal")
    private BigDecimal bucCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "dominical_cal")
    private BigDecimal dominicalCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "vacacional_cal")
    private BigDecimal vacacionalCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "asign_escolar_cal")
    private BigDecimal asignEscolar_cal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "feriados_descansos_cal")
    private BigDecimal feriadosDescansosCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "feriados_trabajados_cal")
    private BigDecimal feriadosTrabajadosCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "descanso_medico_cal")
    private BigDecimal descansoMedicoCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "movilidad_cal")
    private BigDecimal movilidadCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "indemnizacion_cal")
    private BigDecimal indemnizacionCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "gratificacion_cal")
    private BigDecimal gratificacionCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "bonif_extra_temporal_cal")
    private BigDecimal bonifExtraTemporalCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "horas_extras_sesenta_cal")
    private BigDecimal horasExtrasSesentaCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "horas_extras_cien_cal")
    private BigDecimal horasExtrasCienCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "turno_noche_cal")
    private BigDecimal turnoNocheCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "contacto_agua_cal")
    private BigDecimal contactoAguaCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "aguas_servidas_cal")
    private BigDecimal aguasServidasCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "bonificacion_altura_cal")
    private BigDecimal bonificacionAlturaCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "bonificacion_altitud_cal")
    private BigDecimal bonificacionAltitudCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "condicion_trabajo_cal")
    private BigDecimal condicionTrabajoCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "horas_extras_fijas_cal")
    private BigDecimal horasExtrasFijasCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "total_remuneracion_cal")
    private BigDecimal totalRemuneracionCal;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "total_afecto")
    private BigDecimal totalAfecto;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "onp")
    private BigDecimal onp;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "comision_afp")
    private BigDecimal comisionAfp;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "prima_afp")
    private BigDecimal primaAfp;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "aporte_obligatorio_afp")
    private BigDecimal aporteObligatorioAfp;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "aporte_adic_afp")
    private BigDecimal aporteAdicAfp;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "conafovicer")
    private BigDecimal conafovicer;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "adelanto")
    private BigDecimal adelanto;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "cuota_sindical")
    private BigDecimal cuotaSindical;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "otrosDescuentos")
    private BigDecimal otrosDescuentos;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "total_dsctos")
    private BigDecimal totalDsctos;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "neto_pagar")
    private BigDecimal netoPagar;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "essalud_nueve")
    private BigDecimal essalud_nueve;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "stcr")
    private BigDecimal stcr;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "sctr_essalud_eps")
    private BigDecimal sctrEssaludEps;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "sctr_pensiones_privado")
    private BigDecimal sctrPensionesPrivado;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "sctr_essalud_vida")
    private BigDecimal sctrEssaludVida;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "aporte_adicional_afp")
    private BigDecimal aporteAdicionalAfp;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "total_aporte_empleados")
    private BigDecimal totalAporteEmpleados;

    @Digits(integer = 19, fraction = 7)
    @Column(name = "refrigerio")
    private BigDecimal refrigerio;


    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @Column(name = "username")
    private String username;

    @Column(name = "enabled")
    private Integer enabled;

    @Column(name = "entidad_financiera")
    private String entidadFinanciera;

    @Column(name = "num_cuenta_bancaria")
    private String numCuentaBancaria;

    @Column(name = "cci")
    private String cci;

    public void prePersist() {
        createAt = new Date();
    }


    public Integer getEnabled() {
        return enabled;
    }


    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
	/*@OneToMany(mappedBy="asiento",fetch = FetchType.EAGER,cascade = CascadeType.ALL )
	private List<Voucher> lstVoucher;*/


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


    public BigDecimal getRefrigerio() {
        return refrigerio;
    }


    public void setRefrigerio(BigDecimal refrigerio) {
        this.refrigerio = refrigerio;
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


    public static long getSerialversionuid() {
        return serialVersionUID;
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
