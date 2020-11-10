package com.sprinboot.servicios.app.admin.base.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprinboot.servicios.app.otros.commons.models.entity.MedioDePago;

public interface IMedioDePago extends JpaRepository<MedioDePago, Integer>{

}
