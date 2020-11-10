package com.sprinboot.servicios.logistica.commons.services;

import java.util.Optional;

public interface IObjectService<E> {

	public Optional<E> findByIdOptionalCommon(Integer id);
	public E findByIdCommon(Integer id);
	public E saveCommon (E entity);
	public void deleteByIdCommon(Integer id); 
	public Iterable<E> findAllCommon() ;
}
