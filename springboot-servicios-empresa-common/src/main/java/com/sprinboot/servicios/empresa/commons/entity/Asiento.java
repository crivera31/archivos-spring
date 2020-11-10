package com.sprinboot.servicios.empresa.commons.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="cont_asiento")
public class Asiento implements Serializable{
	
	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;	
	
	@Column(name="num_asiento")
	private Integer numAsiento;
	
	@Column(name="fecha_asiento")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date fechaAsiento;
	
	@Column(name="total_debe_soles",precision=19,scale=2)
	private BigDecimal totalDebeSoles;
	
	@Column(name="total_haber_soles",precision=19,scale=2)
	private BigDecimal totalHaberSoles;
	
	@Column(name="total_debe_dolares",precision=19,scale=2)
	private BigDecimal totalDebeDolares;
	
	@Column(name="total_haber_dolares",precision=19,scale=2)
	private BigDecimal totalHaberDolares;
	
	@Column(name="consistencia_soles",precision=19,scale=2)
	private BigDecimal consistenciaSoles;
	
	@Column(name="consistencia_dolares",precision=19,scale=2)
	private BigDecimal consistenciaDolares;
		
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@Column(name="cod_origen")
	private String codOrigen ;
	
	@Column(name="id_periodo")
	private Integer idPeriodo;
	
	@Column(name="anio")
	private String anio;
	
	@Column(name="codMes")
	private Integer codMes;
	
	@Column(name="username")
	private String username;
	
	@Column(name="estado_asiento")
	private Integer estadoAsiento;
	
	@Column(name="num_comprobante")
	private Integer numComprobante;
	
	
	@OneToMany(mappedBy="asiento",fetch = FetchType.EAGER,cascade = CascadeType.ALL )
	private List<Voucher> lstVoucher;

    public void prePersist()  {
    	createAt = new Date();
    }
	
	
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumAsiento() {
		return numAsiento;
	}

	public void setNumAsiento(Integer numAsiento) {
		this.numAsiento = numAsiento;
	}

	public Date getFechaAsiento() {
		return fechaAsiento;
	}

	public void setFechaAsiento(Date fechaAsiento) {
		this.fechaAsiento = fechaAsiento;
	}

	public BigDecimal getTotalDebeSoles() {
		return totalDebeSoles;
	}

	public void setTotalDebeSoles(BigDecimal totalDebeSoles) {
		this.totalDebeSoles = totalDebeSoles;
	}

	public BigDecimal getTotalHaberSoles() {
		return totalHaberSoles;
	}

	public void setTotalHaberSoles(BigDecimal totalHaberSoles) {
		this.totalHaberSoles = totalHaberSoles;
	}

	public BigDecimal getTotalDebeDolares() {
		return totalDebeDolares;
	}

	public void setTotalDebeDolares(BigDecimal totalDebeDolares) {
		this.totalDebeDolares = totalDebeDolares;
	}

	public BigDecimal getTotalHaberDolares() {
		return totalHaberDolares;
	}

	public void setTotalHaberDolares(BigDecimal totalHaberDolares) {
		this.totalHaberDolares = totalHaberDolares;
	}

	public BigDecimal getConsistenciaSoles() {
		return consistenciaSoles;
	}

	public void setConsistenciaSoles(BigDecimal consistenciaSoles) {
		this.consistenciaSoles = consistenciaSoles;
	}

	public BigDecimal getConsistenciaDolares() {
		return consistenciaDolares;
	}

	public void setConsistenciaDolares(BigDecimal consistenciaDolares) {
		this.consistenciaDolares = consistenciaDolares;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getCodOrigen() {
		return codOrigen;
	}

	public void setCodOrigen(String codOrigen) {
		this.codOrigen = codOrigen;
	}


	public Integer getIdPeriodo() {
		return idPeriodo;
	}


	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getEstadoAsiento() {
		return estadoAsiento;
	}

	public void setEstadoAsiento(Integer estadoAsiento) {
		this.estadoAsiento = estadoAsiento;
	}


	public List<Voucher> getLstVoucher() {
		return lstVoucher;
	}


	public void setLstVoucher(List<Voucher> lstVoucher) {
		this.lstVoucher = lstVoucher;
	}


	public String getAnio() {
		return anio;
	}


	public void setAnio(String anio) {
		this.anio = anio;
	}


	public Integer getCodMes() {
		return codMes;
	}


	public void setCodMes(Integer codMes) {
		this.codMes = codMes;
	}


	public Integer getNumComprobante() {
		return numComprobante;
	}


	public void setNumComprobante(Integer numComprobante) {
		this.numComprobante = numComprobante;
	}



	

}
