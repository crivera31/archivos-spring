package com.sprinboot.servicios.app.oriplan.sis.dto;

import java.math.BigDecimal;

public class TrabajadorAfpDto {

    private String dni;

    private BigDecimal totalAfecto;

    private String cussp;

    private String nombresCompletos;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public BigDecimal getTotalAfecto() {
        return totalAfecto;
    }

    public void setTotalAfecto(BigDecimal totalAfecto) {
        this.totalAfecto = totalAfecto;
    }

    public String getCussp() {
        return cussp;
    }

    public void setCussp(String cussp) {
        this.cussp = cussp;
    }

    public String getNombresCompletos() {
        return nombresCompletos;
    }

    public void setNombresCompletos(String nombresCompletos) {
        this.nombresCompletos = nombresCompletos;
    }
}
