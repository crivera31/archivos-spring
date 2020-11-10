package com.sprinboot.servicios.app.admin.base.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sprinboot.servicios.app.otros.commons.models.entity.CentroCosto;

public interface ICentroCostoDao extends JpaRepository<CentroCosto, Integer> {

}
