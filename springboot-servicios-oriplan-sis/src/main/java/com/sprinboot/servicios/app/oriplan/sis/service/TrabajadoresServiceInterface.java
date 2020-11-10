package com.sprinboot.servicios.app.oriplan.sis.service;

import java.util.Date;
import java.util.List;

import com.sprinboot.servicios.app.oriplan.sis.dao.TrabajadoresDao;
import com.sprinboot.servicios.oriplan.commons.entity.TasasPensiones;
import com.sprinboot.servicios.oriplan.commons.entity.Trabajadores;
import com.sprinboot.servicios.oriplan.commons.services.IObjectService;

public interface TrabajadoresServiceInterface extends IObjectService<Trabajadores> {

    public List<Trabajadores> listarTrabajadoresActivos();

    public List<Trabajadores> listarTrabajadoresBajas();

    public boolean buscarSinRepeticion(String dni, Date fechaIngreso);

    public List<Trabajadores> buscar(String dni, Date fechaIngreso);

    public boolean buscarTrabajadorActivo(String dni);

    public Trabajadores editarTrabajador(Trabajadores trabajadores);
}
