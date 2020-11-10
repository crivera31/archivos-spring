package com.sprinboot.servicios.oriplan.commons.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "tareo_det")
public class TareoDet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dni")
    private String dni;

    @Column(name = "nombres_completos")
    private String nombresCompletos;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "lunes_normal")
    private BigDecimal lunesNormal;

    @Column(name = "martes_normal")
    private BigDecimal martesNormal;

    @Column(name = "miercoles_normal")
    private BigDecimal miercolesNormal;

    @Column(name = "jueves_normal")
    private BigDecimal juevesNormal;

    @Column(name = "viernes_normal")
    private BigDecimal viernesNormal;

    @Column(name = "sabado_normal")
    private BigDecimal sabadoNormal;

    @Column(name = "domingo_normal")
    private BigDecimal domingoNormal;

    @Column(name = "lunes_extras")
    private BigDecimal lunesExtras;

    @Column(name = "martes_extras")
    private BigDecimal martesExtras;

    @Column(name = "miercoles_extras")
    private BigDecimal miercolesExtras;

    @Column(name = "jueves_extras")
    private BigDecimal juevesExtras;

    @Column(name = "viernes_extras")
    private BigDecimal viernesExtras;

    @Column(name = "sabado_extras")
    private BigDecimal sabadoExtras;

    @Column(name = "domingo_extras")
    private BigDecimal domingoExtras;

    @Column(name = "totales_horas_normales")
    private BigDecimal totalesHorasNormales;

    @Column(name = "totales_horas_extras")
    private BigDecimal totalesHorasExtras;

    @Column(name = "totales_extras_sesenta")
    private BigDecimal totalesExtrasSesenta;

    @Column(name = "totales_extras_cien")
    private BigDecimal totalesExtrasCien;

    @Column(name = "num_dias_feriados_descansados")
    private BigDecimal numDiasFeriadosDescansados;

    @Column(name = "num_dias_feriados_trabajados")
    private BigDecimal numDiasFeriadosTrabajados;

    @Column(name = "num_dias_descanso_medico")
    private BigDecimal numDiasDescansoMedico;

    @Column(name = "sindicato")
    private Integer sindicato;

    @Column(name = "adelanto")
    private BigDecimal adelanto;

    @Column(name = "username")
    private String username;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tareo_cab_id")
    //@ManyToOne(optional=false)
    private TareoCab tareoCab;

    private static final long serialVersionUID = 1L;


    @Column(name = "enabled")
    private Integer enabled;


    public void prePersist() {
        createAt = new Date();
    }

    public Integer getEnabled() {
        return enabled;
    }


    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }


    public BigDecimal getNumDiasFeriadosDescansados() {
        return numDiasFeriadosDescansados;
    }

    public void setNumDiasFeriadosDescansados(BigDecimal numDiasFeriadosDescansados) {
        this.numDiasFeriadosDescansados = numDiasFeriadosDescansados;
    }

    public BigDecimal getNumDiasFeriadosTrabajados() {
        return numDiasFeriadosTrabajados;
    }

    public void setNumDiasFeriadosTrabajados(BigDecimal numDiasFeriadosTrabajados) {
        this.numDiasFeriadosTrabajados = numDiasFeriadosTrabajados;
    }

    public BigDecimal getNumDiasDescansoMedico() {
        return numDiasDescansoMedico;
    }

    public void setNumDiasDescansoMedico(BigDecimal numDiasDescansoMedico) {
        this.numDiasDescansoMedico = numDiasDescansoMedico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getLunesNormal() {
        return lunesNormal;
    }

    public void setLunesNormal(BigDecimal lunesNormal) {
        this.lunesNormal = lunesNormal;
    }

    public BigDecimal getMartesNormal() {
        return martesNormal;
    }

    public void setMartesNormal(BigDecimal martesNormal) {
        this.martesNormal = martesNormal;
    }

    public BigDecimal getMiercolesNormal() {
        return miercolesNormal;
    }

    public void setMiercolesNormal(BigDecimal miercolesNormal) {
        this.miercolesNormal = miercolesNormal;
    }

    public BigDecimal getJuevesNormal() {
        return juevesNormal;
    }

    public void setJuevesNormal(BigDecimal juevesNormal) {
        this.juevesNormal = juevesNormal;
    }

    public BigDecimal getSabadoNormal() {
        return sabadoNormal;
    }

    public void setSabadoNormal(BigDecimal sabadoNormal) {
        this.sabadoNormal = sabadoNormal;
    }

    public BigDecimal getDomingoNormal() {
        return domingoNormal;
    }

    public void setDomingoNormal(BigDecimal domingoNormal) {
        this.domingoNormal = domingoNormal;
    }


    public BigDecimal getViernesNormal() {
        return viernesNormal;
    }

    public void setViernesNormal(BigDecimal viernesNormal) {
        this.viernesNormal = viernesNormal;
    }

    public BigDecimal getLunesExtras() {
        return lunesExtras;
    }

    public void setLunesExtras(BigDecimal lunesExtras) {
        this.lunesExtras = lunesExtras;
    }

    public BigDecimal getMartesExtras() {
        return martesExtras;
    }

    public void setMartesExtras(BigDecimal martesExtras) {
        this.martesExtras = martesExtras;
    }

    public BigDecimal getMiercolesExtras() {
        return miercolesExtras;
    }

    public void setMiercolesExtras(BigDecimal miercolesExtras) {
        this.miercolesExtras = miercolesExtras;
    }

    public BigDecimal getJuevesExtras() {
        return juevesExtras;
    }

    public void setJuevesExtras(BigDecimal juevesExtras) {
        this.juevesExtras = juevesExtras;
    }

    public BigDecimal getViernesExtras() {
        return viernesExtras;
    }

    public void setViernesExtras(BigDecimal viernesExtras) {
        this.viernesExtras = viernesExtras;
    }

    public BigDecimal getSabadoExtras() {
        return sabadoExtras;
    }

    public void setSabadoExtras(BigDecimal sabadoExtras) {
        this.sabadoExtras = sabadoExtras;
    }

    public BigDecimal getDomingoExtras() {
        return domingoExtras;
    }

    public void setDomingoExtras(BigDecimal domingoExtras) {
        this.domingoExtras = domingoExtras;
    }

    public BigDecimal getTotalesHorasNormales() {
        return totalesHorasNormales;
    }

    public void setTotalesHorasNormales(BigDecimal totalesHorasNormales) {
        this.totalesHorasNormales = totalesHorasNormales;
    }

    public BigDecimal getTotalesHorasExtras() {
        return totalesHorasExtras;
    }

    public void setTotalesHorasExtras(BigDecimal totalesHorasExtras) {
        this.totalesHorasExtras = totalesHorasExtras;
    }

    public BigDecimal getTotalesExtrasSesenta() {
        return totalesExtrasSesenta;
    }

    public void setTotalesExtrasSesenta(BigDecimal totalesExtrasSesenta) {
        this.totalesExtrasSesenta = totalesExtrasSesenta;
    }

    public BigDecimal getTotalesExtrasCien() {
        return totalesExtrasCien;
    }

    public void setTotalesExtrasCien(BigDecimal totalesExtrasCien) {
        this.totalesExtrasCien = totalesExtrasCien;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public TareoCab getTareoCab() {
        return tareoCab;
    }

    public void setTareoCab(TareoCab tareoCab) {
        this.tareoCab = tareoCab;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getSindicato() {
        return sindicato;
    }

    public void setSindicato(Integer sindicato) {
        this.sindicato = sindicato;
    }

    public BigDecimal getAdelanto() {
        return adelanto;
    }

    public void setAdelanto(BigDecimal adelanto) {
        this.adelanto = adelanto;
    }


}
