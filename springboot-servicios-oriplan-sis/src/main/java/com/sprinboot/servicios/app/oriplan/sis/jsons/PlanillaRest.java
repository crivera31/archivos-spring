package com.sprinboot.servicios.app.oriplan.sis.jsons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanillaRest {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("cussp")
    private String cussp;

    @JsonProperty("tipoDocumento")
    private Integer tipoDocumento;

    @JsonProperty("dni")
    private String dni;

    @JsonProperty("apellidoPaterno")
    private String apellidoPaterno;

    @JsonProperty("apellidoMaterno")
    private String apellidoMaterno;

    @JsonProperty("nombres")
    private String nombres;

    @JsonProperty("relacionLaboral")
    private String relacionLaboral;

    @JsonProperty("inicioRelacionLaboral")
    private String inicioRelacionLaboral;

    @JsonProperty("ceseRelacionLaboral")
    private String ceseRelacionLaboral;

    @JsonProperty("excepcionAportar")
    private String excepcionAportar;

    @JsonProperty("remuneracionAsegurable")
    private BigDecimal remuneracionAsegurable;

    @JsonProperty("aporteVoluntarioAfiConFin")
    private BigDecimal aporteVoluntarioAfiConFin;

    @JsonProperty("aporteVoluntarioAfiSinFin")
    private BigDecimal aporteVoluntarioAfiSinFin;

    @JsonProperty("aporteVoluntarioEmpleador")
    private BigDecimal aporteVoluntarioEmpleador;

    @JsonProperty("tipoTrabajoRubro")
    private String tipoTrabajoRubro;

    @JsonProperty("afp")
    private String afp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCussp() {
        return cussp;
    }

    public void setCussp(String cussp) {
        this.cussp = cussp;
    }

    public Integer getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getRelacionLaboral() {
        return relacionLaboral;
    }

    public void setRelacionLaboral(String relacionLaboral) {
        this.relacionLaboral = relacionLaboral;
    }

    public String getInicioRelacionLaboral() {
        return inicioRelacionLaboral;
    }

    public void setInicioRelacionLaboral(String inicioRelacionLaboral) {
        this.inicioRelacionLaboral = inicioRelacionLaboral;
    }

    public String getCeseRelacionLaboral() {
        return ceseRelacionLaboral;
    }

    public void setCeseRelacionLaboral(String ceseRelacionLaboral) {
        this.ceseRelacionLaboral = ceseRelacionLaboral;
    }

    public String getExcepcionAportar() {
        return excepcionAportar;
    }

    public void setExcepcionAportar(String excepcionAportar) {
        this.excepcionAportar = excepcionAportar;
    }

    public BigDecimal getRemuneracionAsegurable() {
        return remuneracionAsegurable;
    }

    public void setRemuneracionAsegurable(BigDecimal remuneracionAsegurable) {
        this.remuneracionAsegurable = remuneracionAsegurable;
    }

    public BigDecimal getAporteVoluntarioAfiConFin() {
        return aporteVoluntarioAfiConFin;
    }

    public void setAporteVoluntarioAfiConFin(BigDecimal aporteVoluntarioAfiConFin) {
        this.aporteVoluntarioAfiConFin = aporteVoluntarioAfiConFin;
    }

    public BigDecimal getAporteVoluntarioAfiSinFin() {
        return aporteVoluntarioAfiSinFin;
    }

    public void setAporteVoluntarioAfiSinFin(BigDecimal aporteVoluntarioAfiSinFin) {
        this.aporteVoluntarioAfiSinFin = aporteVoluntarioAfiSinFin;
    }

    public BigDecimal getAporteVoluntarioEmpleador() {
        return aporteVoluntarioEmpleador;
    }

    public void setAporteVoluntarioEmpleador(BigDecimal aporteVoluntarioEmpleador) {
        this.aporteVoluntarioEmpleador = aporteVoluntarioEmpleador;
    }

    public String getTipoTrabajoRubro() {
        return tipoTrabajoRubro;
    }

    public void setTipoTrabajoRubro(String tipoTrabajoRubro) {
        this.tipoTrabajoRubro = tipoTrabajoRubro;
    }

    public String getAfp() {
        return afp;
    }

    public void setAfp(String afp) {
        this.afp = afp;
    }
}
