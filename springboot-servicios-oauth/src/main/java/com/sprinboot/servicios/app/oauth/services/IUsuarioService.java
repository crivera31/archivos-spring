package com.sprinboot.servicios.app.oauth.services;

import com.sprinboot.servicios.usuario.common.models.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsername(String username);
	
	public Usuario update(Usuario usuario, Long id);
}
