package com.sprinboot.servicios.oriplan.commons.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@Entity
@Table(name = "trabajadores")
public class Trabajadores implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "dni")
    private String dni;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "nombres_completos")
    private String nombresCompletos;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "estadoCivil")
    private String estadoCivil;

    @Column(name = "celular")
    private String celular;

    @Column(name = "correo")
    private String correo;

    @Column(name = "direccion")
    private String direccion;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaIngreso;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fecha_egreso")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_egreso;

    @Column(name = "cargo_ocupacion")
    private String cargoOcupacion;


    @Column(name = "sistema_pensiones")
    private Integer sistemaPensiones;

    @Column(name = "sindicalizado")
    private Integer sindicalizado;

    @Column(name = "grado_instruccion")
    private Integer gradoInstruccion;


    @Column(name = "nombre_uni")
    private String nombreUni;

    @Column(name = "carrera")
    private String carrera;

    @Column(name = "activo")
    private Integer activo;


    @Column(name = "anio_ingreso")
    private String anio_ingreso;


    @Column(name = "entidad_financiera")
    private String entidadFinanciera;

    @Column(name = "num_cuenta_bancaria")
    private String numCuentaBancaria;

    @Column(name = "sueldo")
    private BigDecimal sueldo;

    @Column(name = "cci")
    private String cci;

    @Column(name = "cussp")
    private String cussp;

    @Column(name = "numhijos")
    private Integer numhijos;

    @Column(name = "tipo_comision")
    private String tipoComision;

    @Column(name = "planilla")
    private Integer planilla;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @Column(name = "username")
    private String username;

    private static final long serialVersionUID = 1L;

    @Column(name = "enabled")
    private Integer enabled;

    @Column(name = "condicion_trabajo")
    private BigDecimal condicionTrabajo;

    public BigDecimal getCondicionTrabajo() {
        return condicionTrabajo;
    }

    public void setCondicionTrabajo(BigDecimal condicionTrabajo) {
        this.condicionTrabajo = condicionTrabajo;
    }

    public void prePersist() {
        createAt = new Date();
    }


    public BigDecimal getSueldo() {
        return sueldo;
    }


    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }


    public Integer getPlanilla() {
        return planilla;
    }


    public void setPlanilla(Integer planilla) {
        this.planilla = planilla;
    }


    public String getCussp() {
        return cussp;
    }


    public void setCussp(String cussp) {
        this.cussp = cussp;
    }


    public Integer getNumhijos() {
        return numhijos;
    }


    public void setNumhijos(Integer numhijos) {
        this.numhijos = numhijos;
    }


    public String getTipoComision() {
        return tipoComision;
    }


    public void setTipoComision(String tipoComision) {
        this.tipoComision = tipoComision;
    }


    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    public Date getFecha_egreso() {
        return fecha_egreso;
    }


    public Integer getActivo() {
        return activo;
    }


    public void setActivo(Integer activo) {
        this.activo = activo;
    }


    public void setFecha_egreso(Date fecha_egreso) {
        this.fecha_egreso = fecha_egreso;
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


    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }


    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    public String getNombresCompletos() {
        return nombresCompletos;
    }


    public void setNombresCompletos(String nombresCompletos) {
        this.nombresCompletos = nombresCompletos;
    }


    public String getSexo() {
        return sexo;
    }


    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


    public String getEstadoCivil() {
        return estadoCivil;
    }


    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }


    public String getCelular() {
        return celular;
    }


    public void setCelular(String celular) {
        this.celular = celular;
    }


    public String getCorreo() {
        return correo;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }


    public String getDireccion() {
        return direccion;
    }


    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public Date getFechaIngreso() {
        return fechaIngreso;
    }


    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }


    public String getCargoOcupacion() {
        return cargoOcupacion;
    }


    public void setCargoOcupacion(String cargoOcupacion) {
        this.cargoOcupacion = cargoOcupacion;
    }


    public Integer getSistemaPensiones() {
        return sistemaPensiones;
    }


    public void setSistemaPensiones(Integer sistemaPensiones) {
        this.sistemaPensiones = sistemaPensiones;
    }


    public Integer getSindicalizado() {
        return sindicalizado;
    }


    public void setSindicalizado(Integer sindicalizado) {
        this.sindicalizado = sindicalizado;
    }


    public Integer getGradoInstruccion() {
        return gradoInstruccion;
    }


    public void setGradoInstruccion(Integer gradoInstruccion) {
        this.gradoInstruccion = gradoInstruccion;
    }


    public String getNombreUni() {
        return nombreUni;
    }


    public void setNombreUni(String nombreUni) {
        this.nombreUni = nombreUni;
    }


    public String getCarrera() {
        return carrera;
    }


    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }


    public String getAnio_ingreso() {
        return anio_ingreso;
    }


    public void setAnio_ingreso(String anio_ingreso) {
        this.anio_ingreso = anio_ingreso;
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


}
