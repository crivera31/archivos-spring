package com.sprinboot.servicios.app.admin.base.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprinboot.servicios.app.otros.commons.models.entity.Moneda;

public interface IMonedaDao extends JpaRepository<Moneda, Integer> {

}
