package com.sprinboot.servicios.logistica.commons.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name="cont_registro_libros")
public class RegistroLibros implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;	
	
	@Column(name="base_imp_ope",precision=19,scale=2)
	private BigDecimal baseImpOpe;
	
	@Column(name="base_imp_adq",precision=19,scale=2)
	private BigDecimal baseImpAdq;
	
	@Column(name="adq_sin_der",precision=19,scale=2)
	private BigDecimal adqSinDer;
	
	@Column(name="adq_nov_grav",precision=19,scale=2)
	private BigDecimal adqNoGrav;
	
	@Column(name="isc",precision=19,scale=2)
	private BigDecimal isc;
	
	@Column(name="igv_base_imp_ope",precision=19,scale=2)
	private BigDecimal igvBaseImpOpe;
	
	@Column(name="igv_base_imp_adq",precision=19,scale=2)
	private BigDecimal igvBaseImpAdq;
	
	@Column(name="igv_base_sin_der",precision=19,scale=2)
	private BigDecimal igvBaseAdqSinDer;
	
	@Column(name="otros_tributos",precision=19,scale=2)
	private BigDecimal otrosTributos;
	
	@Column(name="valor_exportacion",precision=19,scale=2)
	private BigDecimal valorExportacion;
	
	@Column(name="exonerado",precision=19,scale=2)
	private BigDecimal exonerado;
	
	@Column(name="inafecto",precision=19,scale=2)
	private BigDecimal inafecto;

	@Column(name="estado_reg_lib")
	private Integer estadoRegLib;

	@NumberFormat(style= Style.NUMBER , pattern="#,###.##")
	@Column(name="total_monto",precision=19,scale=2)
	private BigDecimal totalMonto;
	
	@Column(name="username")
	private String username;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "cont_voucher_id")
	private Voucher voucher;

	@OneToOne()
	@JoinColumn(name = "cont_libro_id")
	private Libro libro;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEstadoRegLib() {
		return estadoRegLib;
	}

	public void setEstadoRegLib(Integer estadoRegLib) {
		this.estadoRegLib = estadoRegLib;
	}

	public BigDecimal getTotalMonto() {
		return totalMonto;
	}

	public void setTotalMonto(BigDecimal totalMonto) {
		this.totalMonto = totalMonto;
	}

	public Voucher getVoucher() {
		return voucher;
	}

	public void setVoucher(Voucher voucher) {
		this.voucher = voucher;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public BigDecimal getBaseImpOpe() {
		return baseImpOpe;
	}

	public void setBaseImpOpe(BigDecimal baseImpOpe) {
		this.baseImpOpe = baseImpOpe;
	}

	public BigDecimal getBaseImpAdq() {
		return baseImpAdq;
	}

	public void setBaseImpAdq(BigDecimal baseImpAdq) {
		this.baseImpAdq = baseImpAdq;
	}

	public BigDecimal getAdqSinDer() {
		return adqSinDer;
	}

	public void setAdqSinDer(BigDecimal adqSinDer) {
		this.adqSinDer = adqSinDer;
	}

	
	public BigDecimal getIsc() {
		return isc;
	}

	public void setIsc(BigDecimal isc) {
		this.isc = isc;
	}

	public BigDecimal getIgvBaseImpOpe() {
		return igvBaseImpOpe;
	}

	public void setIgvBaseImpOpe(BigDecimal igvBaseImpOpe) {
		this.igvBaseImpOpe = igvBaseImpOpe;
	}

	public BigDecimal getIgvBaseImpAdq() {
		return igvBaseImpAdq;
	}

	public void setIgvBaseImpAdq(BigDecimal igvBaseImpAdq) {
		this.igvBaseImpAdq = igvBaseImpAdq;
	}

	public BigDecimal getIgvBaseAdqSinDer() {
		return igvBaseAdqSinDer;
	}

	public void setIgvBaseAdqSinDer(BigDecimal igvBaseAdqSinDer) {
		this.igvBaseAdqSinDer = igvBaseAdqSinDer;
	}

	public BigDecimal getOtrosTributos() {
		return otrosTributos;
	}

	public void setOtrosTributos(BigDecimal otrosTributos) {
		this.otrosTributos = otrosTributos;
	}

	public BigDecimal getAdqNoGrav() {
		return adqNoGrav;
	}

	public void setAdqNoGrav(BigDecimal adqNoGrav) {
		this.adqNoGrav = adqNoGrav;
	}

	public BigDecimal getValorExportacion() {
		return valorExportacion;
	}

	public void setValorExportacion(BigDecimal valorExportacion) {
		this.valorExportacion = valorExportacion;
	}

	public BigDecimal getExonerado() {
		return exonerado;
	}

	public void setExonerado(BigDecimal exonerado) {
		this.exonerado = exonerado;
	}

	public BigDecimal getInafecto() {
		return inafecto;
	}

	public void setInafecto(BigDecimal inafecto) {
		this.inafecto = inafecto;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
