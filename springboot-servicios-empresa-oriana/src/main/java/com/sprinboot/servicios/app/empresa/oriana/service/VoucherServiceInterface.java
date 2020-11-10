package com.sprinboot.servicios.app.empresa.oriana.service;

import java.util.List;

import com.sprinboot.servicios.empresa.commons.entity.Voucher;

public interface VoucherServiceInterface {
	public Voucher save(Voucher voucher);
	public Voucher saveManual(Voucher voucher) ;
	public Voucher obtenerVoucher(Integer id) ;

}
