package com.sprinboot.servicios.app.otros.commons.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "opcion_plan_cuentas")
public class OpcionPlanCuenta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100, name = "nom_opcion_plan_cuenta")
	private String nomOpcionPlanCuenta;

	@Column(length = 1, name = "abre_nom_opcion_plan_cuenta")
	private String abreNomOpcionPlanCuenta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomOpcionPlanCuenta() {
		return nomOpcionPlanCuenta;
	}

	public void setNomOpcionPlanCuenta(String nomOpcionPlanCuenta) {
		this.nomOpcionPlanCuenta = nomOpcionPlanCuenta;
	}

	public String getAbreNomOpcionPlanCuenta() {
		return abreNomOpcionPlanCuenta;
	}

	public void setAbreNomOpcionPlanCuenta(String abreNomOpcionPlanCuenta) {
		this.abreNomOpcionPlanCuenta = abreNomOpcionPlanCuenta;
	}

	private static final long serialVersionUID = 1L;

}
