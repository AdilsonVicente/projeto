package br.com.analiseskillus.analiseskillusbase.service.event.usuario;

import org.springframework.util.StringUtils;

import br.com.analiseskillus.analiseskillusbase.model.Usuario;

public class UsuarioSalvaEvent {

	private Usuario usuario;
	
	public UsuarioSalvaEvent(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public boolean temFoto() {
		return !StringUtils.isEmpty(usuario.getFoto());
	}
	
	public boolean isNovaFoto() {
		return usuario.isNovaFoto();
	}
}
