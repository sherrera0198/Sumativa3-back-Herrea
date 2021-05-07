package com.everis.sumativa.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.everis.sumativa.models.Usuario;
import com.everis.sumativa.reporsitories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}
 
	public Usuario saveUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
	
		Long cantidadUsuarios =  usuarioRepository.count() ;
		if (cantidadUsuarios>0) {
			return this.saveUser(usuario);
		}else {
			return this.saveAdmin(usuario);
		}
		
		 
	}
	
	public Usuario saveAdmin(Usuario usuario) {
		// TODO Auto-generated method stub
		
		 String hashed = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
		 usuario.setPassword(hashed);
		 usuario.setRol("ROL_ADMIN");
		return usuarioRepository.save(usuario);
	}
	
	
	public Usuario saveUser(Usuario usuario) {
		// TODO Auto-generated method stub
		
		 String hashed = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
		 usuario.setPassword(hashed);
		 usuario.setRol("ROL_USER");
		return usuarioRepository.save(usuario);
	}

	
	public Optional<Usuario> findById(Long usuarioID) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(usuarioID);
	}
	public Usuario findByEmail(String email) {
		
		return usuarioRepository.findByCorreo(email);
		
	}
	public boolean autentication(String email, String password) {
		
		Usuario usuario = usuarioRepository.findByCorreo(email);
		if (usuario == null) {
			return false;
		}else {
			
			if(BCrypt.checkpw(password, usuario.getPassword())){
				return true;
			}else {
				return false;
			}
		}
	}
	
	
}
