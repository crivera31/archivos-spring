package com.sprinboot.servicios.app.empresa.villasol.dto;

import java.math.BigDecimal;
import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;

public class LEDiarioVucherDetDto {

		//id del voucher
		private Integer id;
		
		private String codPlan;

		private String nomPlan;
		
		private BigDecimal debe;
		
		private BigDecimal haber;
		
		private String abreNomMoneda;
		
		private String tipoCambio;
		
		private BigDecimal equivalente;
		
		private String codDocumento;
		
		private String serie;
		
		private String NumeroComprobante;
		
		private String codTipoDoc;
		
		private String codRuc;
		
		private String razonSocial;
		
		private String codUnidadNegocio;
		
		private String glosario;
		
		//@DateTimeFormat(pattern ="yyyy-MM-dd")
		private String fechaEmision;
		
		//@DateTimeFormat(pattern ="yyyy-MM-dd")
		private String fechaVencimiento;
		
		private Integer estadoVoucher;

		
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

		
		public String getSerie() {
			return serie;
		}

		public void setSerie(String serie) {
			this.serie = serie;
		}

		public String getNumeroComprobante() {
			return NumeroComprobante;
		}

		public void setNumeroComprobante(String numeroComprobante) {
			NumeroComprobante = numeroComprobante;
		}

		public String getCodTipoDoc() {
			return codTipoDoc;
		}

		public void setCodTipoDoc(String codTipoDoc) {
			this.codTipoDoc = codTipoDoc;
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

	

		public String getFechaEmision() {
			return fechaEmision;
		}

		public void setFechaEmision(String fechaEmision) {
			this.fechaEmision = fechaEmision;
		}

		public String getFechaVencimiento() {
			return fechaVencimiento;
		}

		public void setFechaVencimiento(String fechaVencimiento) {
			this.fechaVencimiento = fechaVencimiento;
		}

		public Integer getEstadoVoucher() {
			return estadoVoucher;
		}

		public void setEstadoVoucher(Integer estadoVoucher) {
			this.estadoVoucher = estadoVoucher;
		}
		
}
