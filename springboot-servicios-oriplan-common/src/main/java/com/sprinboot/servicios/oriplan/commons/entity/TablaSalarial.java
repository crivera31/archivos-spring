package com.sprinboot.servicios.oriplan.commons.entity;

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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "tabla_salarial")
public class TablaSalarial implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100,name = "periodo_anio")
	private String periodoAnio;
	
	@Column(name = "categoria")
	private String categoria;
	
	@Column(name = "jornal_basico")
	private BigDecimal jornalBasico ;
	
	@Column(name = "buc")
	private BigDecimal buc ;
	
	@Column(name = "movilidad")
	private BigDecimal movilidad ;
	
	@Column(name = "dominical")
	private BigDecimal dominical ;
	
	@Column(name = "vacaciones")
	private BigDecimal  vacaciones;

	@Column(name = "cts")
	private BigDecimal cts ;
	
	@Column(name = "fiestas_navidenas")
	private BigDecimal fiestas_navidenas  ;

	@Column(name = "fiestas_patrias")
	private BigDecimal  fiestasPatrias;
	
	@Column(name = "asignacion_escolar")
	private BigDecimal  asignacionEscolar;
	
	@Column(name = "feriados_descansados")
	private BigDecimal feriadosDescansados ;
	
	@Column(name = "descanso_medico")
	private BigDecimal  descansoMedico;
	
	@Column(name = "horas_extras_sesenta")
	private BigDecimal  horas_extras_sesenta;
	
	@Column(name = "horas_extras_cien")
	private BigDecimal  horasExtrasCien;
	
	@Column(name = "horas_extras_fijas")
	private BigDecimal  horasExtrasFijas;
	
	@Column(name = "contacto_agua")
	private BigDecimal contactoAgua ;
	
	@Column(name = "aguas_hervidas")
	private BigDecimal  aguasHervidas;

	@Column(name = "bonificacion_altura")
	private BigDecimal bonificacionAltura;
	
	@Column(name = "bonificacion_altitud")
	private BigDecimal  bonificacionAltitud;
	
	@Column(name = "horario_nocturno")
	private BigDecimal  horarioNocturno;

	@Column(name = "essalud_vida")
	private BigDecimal  essalud_vida;
	
	@Column(name = "conafovicer")
	private BigDecimal  conafovicer;

	
	@Column(name = "enabled")
	private Integer enabled;
	
	




	private static final long serialVersionUID = 1L;






	public Integer getId() {
		return id;
	}






	public void setId(Integer id) {
		this.id = id;
	}






	public String getPeriodoAnio() {
		return periodoAnio;
	}






	public void setPeriodoAnio(String periodoAnio) {
		this.periodoAnio = periodoAnio;
	}






	public String getCategoria() {
		return categoria;
	}






	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}






	public BigDecimal getJornalBasico() {
		return jornalBasico;
	}






	public void setJornalBasico(BigDecimal jornalBasico) {
		this.jornalBasico = jornalBasico;
	}






	public BigDecimal getBuc() {
		return buc;
	}






	public void setBuc(BigDecimal buc) {
		this.buc = buc;
	}






	public BigDecimal getMovilidad() {
		return movilidad;
	}






	public void setMovilidad(BigDecimal movilidad) {
		this.movilidad = movilidad;
	}






	public BigDecimal getDominical() {
		return dominical;
	}






	public void setDominical(BigDecimal dominical) {
		this.dominical = dominical;
	}






	public BigDecimal getVacaciones() {
		return vacaciones;
	}






	public void setVacaciones(BigDecimal vacaciones) {
		this.vacaciones = vacaciones;
	}






	public BigDecimal getCts() {
		return cts;
	}






	public void setCts(BigDecimal cts) {
		this.cts = cts;
	}






	public BigDecimal getFiestas_navidenas() {
		return fiestas_navidenas;
	}






	public void setFiestas_navidenas(BigDecimal fiestas_navidenas) {
		this.fiestas_navidenas = fiestas_navidenas;
	}






	public BigDecimal getFiestasPatrias() {
		return fiestasPatrias;
	}






	public void setFiestasPatrias(BigDecimal fiestasPatrias) {
		this.fiestasPatrias = fiestasPatrias;
	}






	public BigDecimal getAsignacionEscolar() {
		return asignacionEscolar;
	}






	public void setAsignacionEscolar(BigDecimal asignacionEscolar) {
		this.asignacionEscolar = asignacionEscolar;
	}






	public BigDecimal getFeriadosDescansados() {
		return feriadosDescansados;
	}






	public void setFeriadosDescansados(BigDecimal feriadosDescansados) {
		this.feriadosDescansados = feriadosDescansados;
	}






	public BigDecimal getDescansoMedico() {
		return descansoMedico;
	}






	public void setDescansoMedico(BigDecimal descansoMedico) {
		this.descansoMedico = descansoMedico;
	}






	public BigDecimal getHoras_extras_sesenta() {
		return horas_extras_sesenta;
	}






	public void setHoras_extras_sesenta(BigDecimal horas_extras_sesenta) {
		this.horas_extras_sesenta = horas_extras_sesenta;
	}






	public BigDecimal getHorasExtrasCien() {
		return horasExtrasCien;
	}






	public void setHorasExtrasCien(BigDecimal horasExtrasCien) {
		this.horasExtrasCien = horasExtrasCien;
	}






	public BigDecimal getHorasExtrasFijas() {
		return horasExtrasFijas;
	}






	public void setHorasExtrasFijas(BigDecimal horasExtrasFijas) {
		this.horasExtrasFijas = horasExtrasFijas;
	}






	public BigDecimal getContactoAgua() {
		return contactoAgua;
	}






	public void setContactoAgua(BigDecimal contactoAgua) {
		this.contactoAgua = contactoAgua;
	}






	public BigDecimal getAguasHervidas() {
		return aguasHervidas;
	}






	public void setAguasHervidas(BigDecimal aguasHervidas) {
		this.aguasHervidas = aguasHervidas;
	}










	





	public BigDecimal getBonificacionAltura() {
		return bonificacionAltura;
	}






	public void setBonificacionAltura(BigDecimal bonificacionAltura) {
		this.bonificacionAltura = bonificacionAltura;
	}






	public BigDecimal getBonificacionAltitud() {
		return bonificacionAltitud;
	}






	public void setBonificacionAltitud(BigDecimal bonificacionAltitud) {
		this.bonificacionAltitud = bonificacionAltitud;
	}






	public BigDecimal getHorarioNocturno() {
		return horarioNocturno;
	}






	public void setHorarioNocturno(BigDecimal horarioNocturno) {
		this.horarioNocturno = horarioNocturno;
	}






	public BigDecimal getEssalud_vida() {
		return essalud_vida;
	}






	public void setEssalud_vida(BigDecimal essalud_vida) {
		this.essalud_vida = essalud_vida;
	}






	public BigDecimal getConafovicer() {
		return conafovicer;
	}






	public void setConafovicer(BigDecimal conafovicer) {
		this.conafovicer = conafovicer;
	}






	public Integer getEnabled() {
		return enabled;
	}






	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}






	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
