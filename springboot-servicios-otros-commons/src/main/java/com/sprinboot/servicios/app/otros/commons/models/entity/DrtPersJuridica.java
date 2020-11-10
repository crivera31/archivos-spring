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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.sprinboot.servicios.app.otros.commons.models.entity.TipoDocumento;
@Entity
@Table(name = "drt_pers_juridica")
public class DrtPersJuridica implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty(message = "El campo Código no puede ser vacio")
	@Size(min = 4, max = 12, message = "El tamaño de Código tiene que tener entre 4 y 12 Caracteres")
	@Column(length = 11, name = "codigo_ruc", unique = true)
	private String codigo;

	@Column(length = 150, name = "rsocial_pers_juridica")
	private String rsocial;

	@NotEmpty
	@NotEmpty(message = "El campo Dirección no puede ser vacio")
	@Size(min = 10, max = 150, message = "El tamaño de Dirección tiene que tener entre 10 y 150 Caracteres")
	@Column(length = 150, name = "direccion_pers_juridica")
	private String direccion;

	@Column(length = 150, name = "atencion")
	private String atencion;

	@Column(length = 50, name = "aval")
	private String aval;

	@Column(length = 80, name = "direccion_aval")
	private String direccion_aval;

	@Column(length = 50, name = "codigo_aval")
	private String codigo_aval;

	@Pattern(regexp = "[0-9.\\-+ ]*", message = "El teléfono requiere caracteres alfanuméricos válidos")
	@Column(length = 10, name = "fono_aval")
	private String fono_aval;

	@Pattern(regexp = "[0-9.\\-+ ]*", message = "El teléfono requiere caracteres alfanuméricos válidos")
	@Column(length = 10, name = "num_fono_juridica")
	private String num_fono;

	//@Pattern(regexp = "^[A-Za-z \\\\s\\\\-]*$", message = "El campo Nombre solo acepta letras")
	@Column(length = 50, name = "nombre")
	private String nombres;

	@Column(length = 50, name = "apellido_parterno")
	private String ape_paterno;

	//@Pattern(regexp = "^[A-Za-z \\\\s\\\\-]*$", message = "El campo Apellido Materno solo acepta letras")
	@Column(length = 50, name = "apellido_materno")
	private String ape_materno;

	@Column(name = "enabled")
	private Integer enabled;

	@OneToOne()
	@JoinColumn(name = "tipo_documento_id")
	private TipoDocumento tipoDocumento;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getRsocial() {
		return rsocial;
	}

	public void setRsocial(String rsocial) {
		this.rsocial = rsocial;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApe_paterno() {
		return ape_paterno;
	}

	public void setApe_paterno(String ape_paterno) {
		this.ape_paterno = ape_paterno;
	}

	public String getApe_materno() {
		return ape_materno;
	}

	public void setApe_materno(String ape_materno) {
		this.ape_materno = ape_materno;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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

	public String getAtencion() {
		return atencion;
	}

	public void setAtencion(String atencion) {
		this.atencion = atencion;
	}

	public String getAval() {
		return aval;
	}

	public void setAval(String aval) {
		this.aval = aval;
	}

	public String getDireccion_aval() {
		return direccion_aval;
	}

	public void setDireccion_aval(String direccion_aval) {
		this.direccion_aval = direccion_aval;
	}

	public String getCodigo_aval() {
		return codigo_aval;
	}

	public void setCodigo_aval(String codigo_aval) {
		this.codigo_aval = codigo_aval;
	}

	public String getFono_aval() {
		return fono_aval;
	}

	public void setFono_aval(String fono_aval) {
		this.fono_aval = fono_aval;
	}

	public String getNum_fono() {
		return num_fono;
	}

	public void setNum_fono(String num_fono) {
		this.num_fono = num_fono;
	}

	private static final long serialVersionUID = 1L;
}
