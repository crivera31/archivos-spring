package com.sprinboot.servicios.app.empresa.constructores.dto;

public class ValidarLibroSPDao {
	
	private Integer num_asiento;
	private Integer cod_mes;
	private String cod_origen;
	private String anio;

	public Integer getNum_asiento() {
		return num_asiento;
	}

	public void setNum_asiento(Integer num_asiento) {
		this.num_asiento = num_asiento;
	}



	public Integer getCod_mes() {
		return cod_mes;
	}

	public void setCod_mes(Integer cod_mes) {
		this.cod_mes = cod_mes;
	}

	public String getCod_origen() {
		return cod_origen;
	}

	public void setCod_origen(String cod_origen) {
		this.cod_origen = cod_origen;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

}
