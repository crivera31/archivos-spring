package com.sprinboot.servicios.oriplan.commons.entity;

import java.io.Serializable;
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
@Table(name = "tareo_cab")
public class TareoCab implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;


	@Column(name = "username")
	private String username;

	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	
	@Column(name = "mes")
	private String mes;
	
	@Column(name = "id_datos_generales")
	private Integer idDatosGenerales;
	
	@Column(name = "periodo_semanal")
	private Integer periodo_semanal;
	
	@Column(name = "nom_periodo_semanal")
	private String nomPeriodoSemanal;
	
	@Column(name = "anio")
	private String anio;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Column(name = "fecha_lunes")
	private Date fechaLunes;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Column(name = "fecha_martes")
	private Date fechaMartes;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Column(name = "fecha_miercoles")
	private Date fechaMiercoles;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Column(name = "fecha_jueves")
	private Date fechaJueves;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Column(name = "fecha_viernes")
	private Date fechaViernes;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Column(name = "fecha_sabado")
	private Date fechaSabado;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	@Column(name = "fecha_domingo")
	private Date fechaDomingo;

	
	@Column(name = "enabled")
	private Integer enabled;
	
	
	@OneToMany(mappedBy="tareoCab",fetch = FetchType.EAGER,cascade = CascadeType.ALL )
	private List<TareoDet> lstTareoDet;

	
	  public void prePersist()  {
	    	createAt = new Date();
	    }

	  

		

		public Date getFechaLunes() {
		return fechaLunes;
	}





	public void setFechaLunes(Date fechaLunes) {
		this.fechaLunes = fechaLunes;
	}





	public Integer getIdDatosGenerales() {
		return idDatosGenerales;
	}





	public void setIdDatosGenerales(Integer idDatosGenerales) {
		this.idDatosGenerales = idDatosGenerales;
	}





	public Date getFechaMartes() {
		return fechaMartes;
	}





	public void setFechaMartes(Date fechaMartes) {
		this.fechaMartes = fechaMartes;
	}





	public Date getFechaMiercoles() {
		return fechaMiercoles;
	}





	public void setFechaMiercoles(Date fechaMiercoles) {
		this.fechaMiercoles = fechaMiercoles;
	}





	public Date getFechaJueves() {
		return fechaJueves;
	}





	public void setFechaJueves(Date fechaJueves) {
		this.fechaJueves = fechaJueves;
	}





	public Date getFechaViernes() {
		return fechaViernes;
	}





	public void setFechaViernes(Date fechaViernes) {
		this.fechaViernes = fechaViernes;
	}





	public Date getFechaSabado() {
		return fechaSabado;
	}





	public void setFechaSabado(Date fechaSabado) {
		this.fechaSabado = fechaSabado;
	}





	public Date getFechaDomingo() {
		return fechaDomingo;
	}





	public void setFechaDomingo(Date fechaDomingo) {
		this.fechaDomingo = fechaDomingo;
	}





		public Integer getEnabled() {
			return enabled;
		}


		public void setEnabled(Integer enabled) {
			this.enabled = enabled;
		}
		
		
	public List<TareoDet> getLstTareoDet() {
		return lstTareoDet;
	}




	public void setLstTareoDet(List<TareoDet> lstTareoDet) {
		this.lstTareoDet = lstTareoDet;
	}




	public String getNomPeriodoSemanal() {
		return nomPeriodoSemanal;
	}





	public void setNomPeriodoSemanal(String nomPeriodoSemanal) {
		this.nomPeriodoSemanal = nomPeriodoSemanal;
	}





	public Integer getId() {
		return id;
	}




	public void setId(Integer id) {
		this.id = id;
	}




	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}




	public Date getCreateAt() {
		return createAt;
	}




	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}




	public String getMes() {
		return mes;
	}




	public void setMes(String mes) {
		this.mes = mes;
	}




	public Integer getPeriodo_semanal() {
		return periodo_semanal;
	}




	public void setPeriodo_semanal(Integer periodo_semanal) {
		this.periodo_semanal = periodo_semanal;
	}




	public String getAnio() {
		return anio;
	}




	public void setAnio(String anio) {
		this.anio = anio;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	private static final long serialVersionUID = 1L;

}
