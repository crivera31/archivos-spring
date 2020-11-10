package com.sprinboot.servicios.app.otros.commons.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sprinboot.servicios.usuario.common.models.entity.Role;

@Entity
@Table(name="unidad_negocio")
public class UnidadNegocio implements Serializable{

	private static final long serialVersionUID = 5656689569400315504L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 10, name="codigo_unidad_negocio")
	private String codigoUnidadNegocio;
	
	@Column(length = 200, name="nombre_unidad")
	private String nombUnidad;
	
	
	@Column(name="have_consorcio")
	private Integer haveConsorcio;
	
	@Column(length = 200, name="nombre_consorcio")
	private String nomConsorcio;
	
	
	@Column(length = 10, name="amarre_debe")
	private String amarreDebe;
	
	@Column(length = 10, name="amarre_haber")
	private String amarreHaber;
	
	@Column(name="id_amarre_debe")
	private String idAmarreDebe;
	
	@Column(name="id_amarre_haber")
	private String idAmarreHaber;
	
	@Column(name="enabled")
	private Integer enabled;
	
	@Column(name="is_obra")
	private Integer isObra;
	
	@OneToOne()
	@JoinColumn(name = "periodo_anio_id")
	private PeriodoAnio periodoAnio ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigoUnidadNegocio() {
		return codigoUnidadNegocio;
	}

	public void setCodigoUnidadNegocio(String codigoUnidadNegocio) {
		this.codigoUnidadNegocio = codigoUnidadNegocio;
	}

	public String getNombUnidad() {
		return nombUnidad;
	}

	public void setNombUnidad(String nombUnidad) {
		this.nombUnidad = nombUnidad;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getAmarreDebe() {
		return amarreDebe;
	}

	public void setAmarreDebe(String amarreDebe) {
		this.amarreDebe = amarreDebe;
	}

	public String getAmarreHaber() {
		return amarreHaber;
	}

	public void setAmarreHaber(String amarreHaber) {
		this.amarreHaber = amarreHaber;
	}

	public String getIdAmarreDebe() {
		return idAmarreDebe;
	}

	public void setIdAmarreDebe(String idAmarreDebe) {
		this.idAmarreDebe = idAmarreDebe;
	}

	public String getIdAmarreHaber() {
		return idAmarreHaber;
	}

	public void setIdAmarreHaber(String idAmarreHaber) {
		this.idAmarreHaber = idAmarreHaber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PeriodoAnio getPeriodoAnio() {
		return periodoAnio;
	}

	public void setPeriodoAnio(PeriodoAnio periodoAnio) {
		this.periodoAnio = periodoAnio;
	}

	public Integer getHaveConsorcio() {
		return haveConsorcio;
	}

	public void setHaveConsorcio(Integer haveConsorcio) {
		this.haveConsorcio = haveConsorcio;
	}

	public String getNomConsorcio() {
		return nomConsorcio;
	}

	public void setNomConsorcio(String nomConsorcio) {
		this.nomConsorcio = nomConsorcio;
	}

	public Integer getIsObra() {
		return isObra;
	}

	public void setIsObra(Integer isObra) {
		this.isObra = isObra;
	}


	


}
