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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "datos_generales")
public class DatosGenerales implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "username")
    private String username;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;


    @Column(length = 1000, name = "empresa")
    private String empresa;

    @Column(length = 11, name = "ruc_empresa")
    private String rucEmpresa;

    @Column(length = 1000, name = "consorcio")
    private String consorcio;

    @Column(length = 1000, name = "direccion_empresa")
    private String direccion_empresa;

    @Column(length = 8000, name = "nom_obra")
    private String nomObra;

    @Column(name = "montoContrato")
    private BigDecimal montoContrato;

    @Column(name = "valoruit")
    private BigDecimal valoruit;

    @Column(name = "afecto_vida")
    private String afectoVida;

    @Column(name = "importe")
    private BigDecimal importe;

    @Column(name = "enabled")
    private Integer enabled;

    @Column(name = "peon")
    private BigDecimal peon;

    @Column(name = "oficial")
    private BigDecimal oficial;

    @Column(name = "operario")
    private BigDecimal operario;

    public void prePersist() {
        createAt = new Date();
    }


    public BigDecimal getMontoContrato() {
        return montoContrato;
    }


    public void setMontoContrato(BigDecimal montoContrato) {
        this.montoContrato = montoContrato;
    }


    public BigDecimal getValoruit() {
        return valoruit;
    }


    public void setValoruit(BigDecimal valoruit) {
        this.valoruit = valoruit;
    }


    public String getAfectoVida() {
        return afectoVida;
    }


    public void setAfectoVida(String afectoVida) {
        this.afectoVida = afectoVida;
    }


    public BigDecimal getImporte() {
        return importe;
    }


    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }


    public Integer getEnabled() {
        return enabled;
    }


    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }


    private static final long serialVersionUID = 1L;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
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


    public String getEmpresa() {
        return empresa;
    }


    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }


    public String getRucEmpresa() {
        return rucEmpresa;
    }


    public void setRucEmpresa(String rucEmpresa) {
        this.rucEmpresa = rucEmpresa;
    }


    public String getConsorcio() {
        return consorcio;
    }


    public void setConsorcio(String consorcio) {
        this.consorcio = consorcio;
    }


    public String getDireccion_empresa() {
        return direccion_empresa;
    }


    public void setDireccion_empresa(String direccion_empresa) {
        this.direccion_empresa = direccion_empresa;
    }


    public String getNomObra() {
        return nomObra;
    }


    public void setNomObra(String nomObra) {
        this.nomObra = nomObra;
    }


    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public BigDecimal getPeon() {
        return peon;
    }

    public void setPeon(BigDecimal peon) {
        this.peon = peon;
    }

    public BigDecimal getOficial() {
        return oficial;
    }

    public void setOficial(BigDecimal oficial) {
        this.oficial = oficial;
    }

    public BigDecimal getOperario() {
        return operario;
    }

    public void setOperario(BigDecimal operario) {
        this.operario = operario;
    }
}
