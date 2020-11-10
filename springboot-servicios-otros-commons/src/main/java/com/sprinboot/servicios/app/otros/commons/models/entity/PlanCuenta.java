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

import com.sprinboot.servicios.usuario.common.models.entity.Role;

@Entity
@Table(name = "PLAN_CUENTA")
public class PlanCuenta implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 10, name = "cod_cuenta")
	private String codCuenta;

	@Column(length = 500, name = "nombre_cuenta")
	private String nombreCuenta;

	@Column(length = 10, name = "cuenta_amarre_debe")
	private String cuentaAmarreDebe;

	@Column(length = 10, name = "cuenta_amarre_haber")
	private String cuentaAmarreHaber;

	@Column(length = 45, name = "ent_financiera")
	private String entFinanciera;

	@Column(length = 45, name = "num_cuenta")
	private String num_cuenta;

	@Column(length = 1, name = "enabled")
	private Integer enabled;

	@OneToOne()
	@JoinColumn(name = "centro_costo_id")
	private CentroCosto centroCosto;

	@OneToOne()
	@JoinColumn(name = "plan_cuenta_id")
	private TipoCuenta tipoCuenta;

	@OneToOne()
	@JoinColumn(name = "nivel_cuenta_id")
	private NivelCuenta nivelCuenta;

	@OneToOne()
	@JoinColumn(name = "tipo_analisis_id")
	private TipoAnalisis tipoAnalisis;

	@OneToOne()
	@JoinColumn(name = "opcion_plan_cuenta_id")
	private OpcionPlanCuenta opcionPlanCuenta;

	@OneToOne()
	@JoinColumn(name = "moneda_id")
	private Moneda moneda;

	@OneToOne()
	@JoinColumn(name = "periodo_anio_id")
	private PeriodoAnio periodoAnio ;

	@OneToOne()
	@JoinColumn(name = "clasificacion_bienes_id")
	private ClasificacionBienes clasificacionBienes ;
	
	@Column(name = "id_cuenta_amarre_debe")
	private Integer idCuentaAmarreDebe;

	@Column(name = "id_cuenta_amarre_haber")
	private Integer idCuentaAmarreHaber;

	
	public OpcionPlanCuenta getOpcionPlanCuenta() {
		return opcionPlanCuenta;
	}

	public void setOpcionPlanCuenta(OpcionPlanCuenta opcionPlanCuenta) {
		this.opcionPlanCuenta = opcionPlanCuenta;
	}

	public NivelCuenta getNivelCuenta() {
		return nivelCuenta;
	}

	public void setNivelCuenta(NivelCuenta nivelCuenta) {
		this.nivelCuenta = nivelCuenta;
	}

	public TipoAnalisis getTipoAnalisis() {
		return tipoAnalisis;
	}

	public void setTipoAnalisis(TipoAnalisis tipoAnalisis) {
		this.tipoAnalisis = tipoAnalisis;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodCuenta() {
		return codCuenta;
	}

	public void setCodCuenta(String codCuenta) {
		this.codCuenta = codCuenta;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public String getCuentaAmarreDebe() {
		return cuentaAmarreDebe;
	}

	public void setCuentaAmarreDebe(String cuentaAmarreDebe) {
		this.cuentaAmarreDebe = cuentaAmarreDebe;
	}

	public String getCuentaAmarreHaber() {
		return cuentaAmarreHaber;
	}

	public void setCuentaAmarreHaber(String cuentaAmarreHaber) {
		this.cuentaAmarreHaber = cuentaAmarreHaber;
	}

	public String getEntFinanciera() {
		return entFinanciera;
	}

	public void setEntFinanciera(String entFinanciera) {
		this.entFinanciera = entFinanciera;
	}

	public String getNum_cuenta() {
		return num_cuenta;
	}

	public void setNum_cuenta(String num_cuenta) {
		this.num_cuenta = num_cuenta;
	}

	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public CentroCosto getCentroCosto() {
		return centroCosto;
	}

	public void setCentroCosto(CentroCosto centroCosto) {
		this.centroCosto = centroCosto;
	}


	public PeriodoAnio getPeriodoAnio() {
		return periodoAnio;
	}

	public void setPeriodoAnio(PeriodoAnio periodoAnio) {
		this.periodoAnio = periodoAnio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	public Integer getIdCuentaAmarreDebe() {
		return idCuentaAmarreDebe;
	}

	public void setIdCuentaAmarreDebe(Integer idCuentaAmarreDebe) {
		this.idCuentaAmarreDebe = idCuentaAmarreDebe;
	}


	public Integer getIdCuentaAmarreHaber() {
		return idCuentaAmarreHaber;
	}

	public void setIdCuentaAmarreHaber(Integer idCuentaAmarreHaber) {
		this.idCuentaAmarreHaber = idCuentaAmarreHaber;
	}

	public ClasificacionBienes getClasificacionBienes() {
		return clasificacionBienes;
	}

	public void setClasificacionBienes(ClasificacionBienes clasificacionBienes) {
		this.clasificacionBienes = clasificacionBienes;
	}



	private static final long serialVersionUID = 1L;

}
