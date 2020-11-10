package com.sprinboot.servicios.app.oriplan.sis.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TrabajadorDto {

    private Integer id;


    private String dni;


    private String fechaNacimiento;


    private String nombresCompletos;


    private String sexo;


    private String estadoCivil;


    private String celular;


    private String correo;


    private String direccion;


    private String fechaIngreso;


    private String fecha_egreso;


    private String cargoOcupacion;


    private Integer sistemaPensiones;


    private Integer sindicalizado;


    private Integer gradoInstruccion;


    private String nombreUni;


    private String carrera;


    private Integer activo;


    private String anio_ingreso;


    private String entidadFinanciera;


    private String numCuentaBancaria;


    private String cci;

    private BigDecimal sueldo;

    private String cussp;

    private Integer numhijos;


    private String tipoComision;


    private Date createAt;


    private String username;

    private Integer enabled;

    private Integer planilla;

    private BigDecimal condicionTrabajo;


    public String getCussp() {
        return cussp;
    }


    public void setCussp(String cussp) {
        this.cussp = cussp;
    }


    public Integer getNumhijos() {
        return numhijos;
    }


    public BigDecimal getSueldo() {
        return sueldo;
    }


    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
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


    public Integer getPlanilla() {
        return planilla;
    }


    public void setPlanilla(Integer planilla) {
        this.planilla = planilla;
    }


    public Integer getEnabled() {
        return enabled;
    }


    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
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


    public String getFechaNacimiento() {
        return fechaNacimiento;
    }


    public void setFechaNacimiento(String fechaNacimiento) {
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


    public String getFechaIngreso() {
        return fechaIngreso;
    }


    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }


    public String getFecha_egreso() {
        return fecha_egreso;
    }


    public void setFecha_egreso(String fecha_egreso) {
        this.fecha_egreso = fecha_egreso;
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


    public Integer getActivo() {
        return activo;
    }


    public void setActivo(Integer activo) {
        this.activo = activo;
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

    public BigDecimal getCondicionTrabajo() {
        return condicionTrabajo;
    }

    public void setCondicionTrabajo(BigDecimal condicionTrabajo) {
        this.condicionTrabajo = condicionTrabajo;
    }
}
