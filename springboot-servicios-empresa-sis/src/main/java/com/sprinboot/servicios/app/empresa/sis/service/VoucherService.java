package com.sprinboot.servicios.app.empresa.sis.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinboot.servicios.app.empresa.sis.dao.VoucherDao;
import com.sprinboot.servicios.empresa.commons.entity.Voucher;

@Service
public class VoucherService implements VoucherServiceInterface{
	
	@Autowired
	VoucherDao voucherDao;
	
	@Override
	@Transactional
	public Voucher save(Voucher voucher) {
		return voucherDao.save(voucher);
	}
	
	@Override
	@Transactional
	public Voucher saveManual(Voucher request) {		
		return voucherDao.save(request);
	}

	@Override
	@Transactional
	public Voucher obtenerVoucher(Integer id) {
		return voucherDao.obtenerId(id);
	}
	
	
		
}
