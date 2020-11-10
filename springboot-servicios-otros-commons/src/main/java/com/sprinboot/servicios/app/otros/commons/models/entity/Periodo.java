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
import javax.validation.Valid;

@Entity
@Table(name = "periodo")
public class Periodo implements Serializable {

	private static final long serialVersionUID = -5350423630658124206L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne()
	@JoinColumn(name = "mes_to_job_id")
	@Valid
	private MesToJob mesToJob;

	@OneToOne()
	@JoinColumn(name = "periodo_anio_id")
	@Valid
	private PeriodoAnio periodoAnio;

	
	@Column(length = 1, name = "estado")
	private Integer estado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MesToJob getMesToJob() {
		return mesToJob;
	}

	public void setMesToJob(MesToJob mesToJob) {
		this.mesToJob = mesToJob;
	}

	public PeriodoAnio getPeriodoAnio() {
		return periodoAnio;
	}

	public void setPeriodoAnio(PeriodoAnio periodoAnio) {
		this.periodoAnio = periodoAnio;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}


}
