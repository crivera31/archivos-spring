package com.sprinboot.servicios.app.empresa.sis.dto;

public class CuentaDetalladaDto {

	private String codCuenta;
	private String nomCuenta;
	private String nivelCuenta;
	private String tipoCuenta;
	
	
	public String getNivelCuenta() {
		return nivelCuenta;
	}
	public void setNivelCuenta(String nivelCuenta) {
		this.nivelCuenta = nivelCuenta;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public String getCodCuenta() {
		return codCuenta;
	}
	public void setCodCuenta(String codCuenta) {
		this.codCuenta = codCuenta;
	}
	public String getNomCuenta() {
		return nomCuenta;
	}
	public void setNomCuenta(String nomCuenta) {
		this.nomCuenta = nomCuenta;
	}
	
}
