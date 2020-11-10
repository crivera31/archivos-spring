package com.sprinboot.servicios.app.empresa.villasol.dto;

import java.math.BigDecimal;
import java.util.List;


public class ConsistenciaDto {
	
	String origen;
	Integer vou;
	String mes;
	String comentario;
	BigDecimal totalDebeSoles;
	BigDecimal totalDebeDolares;
	BigDecimal totalHaberSoles;
	BigDecimal totalHaberDolares;
	
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public Integer getVou() {
		return vou;
	}
	public void setVou(Integer vou) {
		this.vou = vou;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public BigDecimal getTotalDebeSoles() {
		return totalDebeSoles;
	}
	public void setTotalDebeSoles(BigDecimal totalDebeSoles) {
		this.totalDebeSoles = totalDebeSoles;
	}
	public BigDecimal getTotalDebeDolares() {
		return totalDebeDolares;
	}
	public void setTotalDebeDolares(BigDecimal totalDebeDolares) {
		this.totalDebeDolares = totalDebeDolares;
	}
	public BigDecimal getTotalHaberSoles() {
		return totalHaberSoles;
	}
	public void setTotalHaberSoles(BigDecimal totalHaberSoles) {
		this.totalHaberSoles = totalHaberSoles;
	}
	public BigDecimal getTotalHaberDolares() {
		return totalHaberDolares;
	}
	public void setTotalHaberDolares(BigDecimal totalHaberDolares) {
		this.totalHaberDolares = totalHaberDolares;
	}
	
	

}

