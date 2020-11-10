package com.sprinboot.servicios.app.admin.base.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprinboot.servicios.app.otros.commons.models.entity.OpcionPlanCuenta;

public interface IOpcionCuentaDao extends JpaRepository<OpcionPlanCuenta, Integer>{

}
