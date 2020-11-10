package com.sprinboot.servicios.app.otros.commons.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_cuenta")
public class TipoCuenta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue(strategy=GenerationType.SEQUENCE, generator =
	// "TIPOCUENTA_SEQ")
	// @SequenceGenerator(sequenceName = "tipoCuentaIdSeq", name = "TIPOCUENTA_SEQ",
	// allocationSize = 1, initialValue = 8)
	private Integer id;

	@Column(length = 11, name = "nom_tipo_cuenta")
	private String nomTipoCuenta;

	@Column(length = 1, name = "abre_nom_tipo_cuenta")
	private String abreNomTipoCuenta;

	public String getAbreNomTipoCuenta() {
		return abreNomTipoCuenta;
	}

	public void setAbreNomTipoCuenta(String abreNomTipoCuenta) {
		this.abreNomTipoCuenta = abreNomTipoCuenta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomTipoCuenta() {
		return nomTipoCuenta;
	}

	public void setNomTipoCuenta(String nomTipoCuenta) {
		this.nomTipoCuenta = nomTipoCuenta;
	}

	private static final long serialVersionUID = 1L;

}
