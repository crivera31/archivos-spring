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
@Table(name="cont_registro_doc")
public class RegistroDocumentos implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;	

	@Column(name="monto_total_inicial",precision=19,scale=2)
	private BigDecimal montoTotalInicial;
	
	@Column(name="monto_faltante",precision=19,scale=2)
	private BigDecimal montoFaltante;
	
	@Column(name="cancelado")
	private Integer cancelado;
	
    @Column(name="estado_reg_doc")
	private Integer estadoRegDoc;
    
    @Column(name="duplicado")
 	private Integer duplicado;
	
	@Column(name="username")
	private String username;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "cont_voucher_id")
	private Voucher voucher;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEstadoRegDoc() {
		return estadoRegDoc;
	}

	public void setEstadoRegDoc(Integer estadoRegDoc) {
		this.estadoRegDoc = estadoRegDoc;
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

	public BigDecimal getMontoTotalInicial() {
		return montoTotalInicial;
	}

	public void setMontoTotalInicial(BigDecimal montoTotalInicial) {
		this.montoTotalInicial = montoTotalInicial;
	}

	public BigDecimal getMontoFaltante() {
		return montoFaltante;
	}

	public void setMontoFaltante(BigDecimal montoFaltante) {
		this.montoFaltante = montoFaltante;
	}

	public Integer getCancelado() {
		return cancelado;
	}

	public void setCancelado(Integer cancelado) {
		this.cancelado = cancelado;
	}

	public Integer getDuplicado() {
		return duplicado;
	}

	public void setDuplicado(Integer duplicado) {
		this.duplicado = duplicado;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
}
