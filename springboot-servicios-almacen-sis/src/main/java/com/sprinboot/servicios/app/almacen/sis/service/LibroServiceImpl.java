package com.sprinboot.servicios.app.almacen.sis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprinboot.servicios.app.almacen.sis.dao.LibroDao;
import com.sprinboot.servicios.empresa.commons.entity.Libro;

@Service
public class LibroServiceImpl implements LibroServiceInterface {

	@Autowired
	private LibroDao libroDao;
	
	@Override
	public List<Libro> getLibros() {
		return libroDao.findAllEnabeld();
	}

}
