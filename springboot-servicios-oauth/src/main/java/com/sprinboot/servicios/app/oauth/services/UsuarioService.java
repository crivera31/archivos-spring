package com.sprinboot.servicios.app.oauth.services;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sprinboot.servicios.app.oauth.clients.UsuarioFeignClient;
import com.sprinboot.servicios.usuario.common.models.entity.Role;
import com.sprinboot.servicios.usuario.common.models.entity.Usuario;

import brave.Tracer;
import feign.FeignException;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

	private Logger log = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioFeignClient client;
	
	@Autowired
	private Tracer tracer;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			
			String[] parts = username.split("_JOIN_");
			String nombreuser = parts[0];
			String modulo = parts[1];
			System.out.println("MODULO="+modulo);
			Usuario usuario = client.findByUsername(nombreuser);
			
			if(!usuarioHabilitadoEnEstaEmpresa(usuario, modulo)) {
				String error = "Error en login, el  usuario : '" + username + "' no esta habilitado para este sistema";
				log.error(error);
				tracer.currentSpan().tag("error.mensaje", error );
				throw new UsernameNotFoundException(error);
			}
			
			List<GrantedAuthority> authorities = usuario.getRoles().stream()
					.map(role -> new SimpleGrantedAuthority(role.getNombre()))
					.peek(authority -> log.info("Role:" + authority.getAuthority())).collect(Collectors.toList());
			log.info("usuario autenticado" + username);
			return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
					authorities);
		} catch (FeignException e) {
			String error = "Error en login, no existe el Usuario '" + username + "' en el sistema";
			log.error(error);
			tracer.currentSpan().tag("error.mensaje", error + ": " + e.getMessage());
			throw new UsernameNotFoundException(error);
		}
	}

	
	private Predicate<Role> filtroModuloEmpresa(String codEmpresa){
		return(Role r) -> {
			return r.getNombre().equals(codEmpresa);
		};
	}
	
	
	private boolean usuarioHabilitadoEnEstaEmpresa(Usuario usuario , String modulo) {
	 List<Role> resultado = new ArrayList<Role>();
		try {
			resultado = usuario.getRoles()
					  .stream()
					  .filter(filtroModuloEmpresa(modulo))
					  .collect(Collectors.toList()) ;
		} catch (Exception e) {
			return false;
		}
		 
		return resultado.size()>0 ;
	}
	
	@Override
	public Usuario findByUsername(String username) {
		return client.findByUsername(username);
	}

	@Override
	public Usuario update(Usuario usuario, Long id) {
		return client.update(usuario, id);
	}

}
