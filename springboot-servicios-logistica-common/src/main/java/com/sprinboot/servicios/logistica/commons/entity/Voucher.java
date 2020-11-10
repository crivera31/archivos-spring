package com.sprinboot.servicios.logistica.commons.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="cont_voucher")
public class Voucher implements Serializable{

private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;	
	
	@Column(name="id_plan")
	private Integer idPlan;
	
	
	@Column(name="cod_plan")
	private String codPlan;
	
	@Column(name="nom_plan")
	private String nomPlan;
	
	@Column(name="debe",precision=19,scale=2)
	private BigDecimal debe;
	
	@Column(name="haber",precision=19,scale=2)
	private BigDecimal haber;
	
	@Column(name="abre_nom_moneda")
	private String abreNomMoneda;
	
	@Column(name="tipoCambio")
	private String tipoCambio;
	
	@Column(name="equivalente",precision=19,scale=2)
	private BigDecimal equivalente;
	
	@Column(name="cod_documento")
	private String codDocumento;
	
	@Column(name="serie_numero")
	private String serieNumero;
	
	@Column(name="cod_ruc",length = 11)
	private String codRuc;
	
	@Column(name="razon_social",length = 150)
	private String razonSocial;
	
	@Column(name="cod_unidad_negocio")
	private String codUnidadNegocio;
	
	
	@Column(name="id_unidad_negocio")
	private Integer idUnidadNegocio;
	

	@Column(name="glosario",length = 300)
	private String glosario;
	

	@Column(name="fecha_emision")
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date fechaEmision;
	
	@Column(name="fecha_vencimiento")
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date fechaVencimiento;
	
	@Column(name="cod_tipo_doc")
	private String codTipoDoc;
	
	@Column(name="estado_voucher")
	private Integer estadoVoucher;
	
	@Column(name="username")
	private String username;
	
	//@JoinColumn(name = "cont_voucher_ref_id")
	@OneToOne(mappedBy = "voucher",cascade = {CascadeType.ALL})
	private VoucherRef voucherRef;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "cont_asiento_id")
	//@ManyToOne(optional=false)
	private Asiento asiento;

	@OneToOne(mappedBy = "voucher",cascade = {CascadeType.ALL})
	private RegistroDocumentos registroDocumento;

	@OneToOne(mappedBy = "voucher",cascade = {CascadeType.ALL})
	private RegistroLibros registroLibro;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getCodPlan() {
		return codPlan;
	}

	public void setCodPlan(String codPlan) {
		this.codPlan = codPlan;
	}

	public String getNomPlan() {
		return nomPlan;
	}

	public void setNomPlan(String nomPlan) {
		this.nomPlan = nomPlan;
	}

	public BigDecimal getDebe() {
		return debe;
	}

	public void setDebe(BigDecimal debe) {
		this.debe = debe;
	}

	public BigDecimal getHaber() {
		return haber;
	}

	public void setHaber(BigDecimal haber) {
		this.haber = haber;
	}

	

	public String getAbreNomMoneda() {
		return abreNomMoneda;
	}

	public void setAbreNomMoneda(String abreNomMoneda) {
		this.abreNomMoneda = abreNomMoneda;
	}

	public String getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(String tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public BigDecimal getEquivalente() {
		return equivalente;
	}

	public void setEquivalente(BigDecimal equivalente) {
		this.equivalente = equivalente;
	}

	public String getCodDocumento() {
		return codDocumento;
	}

	public void setCodDocumento(String codDocumento) {
		this.codDocumento = codDocumento;
	}

	public String getSerieNumero() {
		return serieNumero;
	}

	public void setSerieNumero(String serieNumero) {
		this.serieNumero = serieNumero;
	}

	public String getCodRuc() {
		return codRuc;
	}

	public void setCodRuc(String codRuc) {
		this.codRuc = codRuc;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCodUnidadNegocio() {
		return codUnidadNegocio;
	}

	public void setCodUnidadNegocio(String codUnidadNegocio) {
		this.codUnidadNegocio = codUnidadNegocio;
	}

	public String getGlosario() {
		return glosario;
	}

	public void setGlosario(String glosario) {
		this.glosario = glosario;
	}

	
	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public String getCodTipoDoc() {
		return codTipoDoc;
	}

	public void setCodTipoDoc(String codTipoDoc) {
		this.codTipoDoc = codTipoDoc;
	}

	public Integer getEstadoVoucher() {
		return estadoVoucher;
	}

	public void setEstadoVoucher(Integer estadoVoucher) {
		this.estadoVoucher = estadoVoucher;
	}



	public VoucherRef getVoucherRef() {
		return voucherRef;
	}

	public void setVoucherRef(VoucherRef voucherRef) {
		this.voucherRef = voucherRef;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	public RegistroDocumentos getRegistroDocumento() {
		return registroDocumento;
	}

	public void setRegistroDocumento(RegistroDocumentos registroDocumento) {
		this.registroDocumento = registroDocumento;
	}

	public RegistroLibros getRegistroLibro() {
		return registroLibro;
	}

	public void setRegistroLibro(RegistroLibros registroLibro) {
		this.registroLibro = registroLibro;
	}

	public Integer getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(Integer idPlan) {
		this.idPlan = idPlan;
	}
	
	public Integer getIdUnidadNegocio() {
		return idUnidadNegocio;
	}

	public void setIdUnidadNegocio(Integer idUnidadNegocio) {
		this.idUnidadNegocio = idUnidadNegocio;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
	
	
}
