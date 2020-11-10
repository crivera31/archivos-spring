package com.sprinboot.servicios.logistica.commons.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cont_all_persona")
public class ContAllPerson implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 15, name = "codigo_tributario")
	private String cod_tributario;

	@Column(name = "dias_venc_facturas")
	private Integer dias_venc_facturas;

	@Column(name = "enabled")
	private Integer enabled;

	@Column(length = 150, name = "rsocial_pers_juridica")
	private String rsocial;
	
	@NotEmpty(message = "El campo Código no puede ser vacio")
	@Size(min = 4, max = 12, message = "El tamaño de Código tiene que tener entre 4 y 12 Caracteres")
	@Column(length = 11, name = "codigo_ruc", unique = true)
	private String codigo;

	@Column(length = 45, name = "codigo_tipo_doc")
	private String codigoTipoDoc;
	
	@OneToOne()
	@JoinColumn(name = "tipo_all_persona_id")
	private TipoAllPersona contTipoAllper;

	
	public String getRsocial() {
		return rsocial;
	}

	public void setRsocial(String rsocial) {
		this.rsocial = rsocial;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public TipoAllPersona getContTipoAllper() {
		return contTipoAllper;
	}

	public void setContTipoAllper(TipoAllPersona contTipoAllper) {
		this.contTipoAllper = contTipoAllper;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCod_tributario() {
		return cod_tributario;
	}

	public void setCod_tributario(String cod_tributario) {
		this.cod_tributario = cod_tributario;
	}

	public Integer getDias_venc_facturas() {
		return dias_venc_facturas;
	}

	public void setDias_venc_facturas(Integer dias_venc_facturas) {
		this.dias_venc_facturas = dias_venc_facturas;
	}
	
	

	public String getCodigoTipoDoc() {
		return codigoTipoDoc;
	}

	public void setCodigoTipoDoc(String codigoTipoDoc) {
		this.codigoTipoDoc = codigoTipoDoc;
	}



	private static final long serialVersionUID = 1L;

}
