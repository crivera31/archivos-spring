package com.sprinboot.servicios.app.admin.base.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprinboot.servicios.app.otros.commons.models.entity.TipoCuenta;

public interface ITipoCuenta extends JpaRepository<TipoCuenta, Integer>{

}
