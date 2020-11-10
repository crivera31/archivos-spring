package com.sprinboot.servicios.app.admin.base.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprinboot.servicios.app.otros.commons.models.entity.TipoRegimen;

public interface ITipoRegimenDao  extends JpaRepository<TipoRegimen,Integer>{

}
