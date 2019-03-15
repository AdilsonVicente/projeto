package analiseskillus.com.br.service.event.usuario;

import org.springframework.util.StringUtils;

import analiseskillus.com.br.model.Usuario;

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
