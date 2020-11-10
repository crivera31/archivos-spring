package com.sprinboot.servicios.oriplan.commons.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public class ObjectService<E,R extends JpaRepository<E,Integer>> implements IObjectService<E> {

	@Autowired
	protected R repository;
	
	@Override
	@Transactional(readOnly = true)
	public Optional<E> findByIdOptionalCommon(Integer id) {
		return repository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public E findByIdCommon(Integer id) {
		return repository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional
	public E saveCommon(E entity) {
		return repository.save(entity);
	}

	@Override
	@Transactional
	public void deleteByIdCommon(Integer id) {
	     repository.deleteById(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<E> findAllCommon() {
		return repository.findAll();
	}

}
