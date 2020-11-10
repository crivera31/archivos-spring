package com.sprinboot.servicios.app.empresa.constructores.jsons;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class HojaTrabajoRest {

	@JsonProperty("nomEmpresa")
	private String nomEmpresa;
	
	@JsonProperty("direccion")
	private String direccion;
	
	@JsonProperty("anio")
	private String anio;
	
	@JsonProperty("codRuc")
	private String codRuc;
	
	@JsonProperty("fechaActual")
	private String fechaActual;
	
	@JsonProperty("listHojaTrabajoDet")
	private List<HojaTrabajoDetRest> listHojaTrabajoDet;
	
	@JsonProperty("totales")
	private HojaTrabajoTotalesRest totales;
	
	@JsonProperty("perdidaEjercicio")
	private HojaTrabajoPerdidaRest perdidaEjercicio;
	
	@JsonProperty("sumasIguales")
	private HojaTrabajoSumasRest sumasIguales;

	public String getNomEmpresa() {
		return nomEmpresa;
	}

	public void setNomEmpresa(String nomEmpresa) {
		this.nomEmpresa = nomEmpresa;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getCodRuc() {
		return codRuc;
	}

	public void setCodRuc(String codRuc) {
		this.codRuc = codRuc;
	}

	public String getFechaActual() {
		return fechaActual;
	}

	public void setFechaActual(String fechaActual) {
		this.fechaActual = fechaActual;
	}

	public List<HojaTrabajoDetRest> getListHojaTrabajoDet() {
		return listHojaTrabajoDet;
	}

	public void setListHojaTrabajoDet(List<HojaTrabajoDetRest> listHojaTrabajoDet) {
		this.listHojaTrabajoDet = listHojaTrabajoDet;
	}

	public HojaTrabajoTotalesRest getTotales() {
		return totales;
	}

	public void setTotales(HojaTrabajoTotalesRest totales) {
		this.totales = totales;
	}

	public HojaTrabajoPerdidaRest getPerdidaEjercicio() {
		return perdidaEjercicio;
	}

	public void setPerdidaEjercicio(HojaTrabajoPerdidaRest perdidaEjercicio) {
		this.perdidaEjercicio = perdidaEjercicio;
	}

	public HojaTrabajoSumasRest getSumasIguales() {
		return sumasIguales;
	}

	public void setSumasIguales(HojaTrabajoSumasRest sumasIguales) {
		this.sumasIguales = sumasIguales;
	}
	
	
}
