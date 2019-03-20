package br.com.analiseskillus.analiseskillusbase.service.event;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.analiseskillus.analiseskillusbase.model.Grupo;

public class GrupoSalvaEvent {

	@Autowired
	private Grupo grupo;
	
	public GrupoSalvaEvent(Grupo grupo) {
		this.grupo = grupo;
	}

	public Grupo getGrupo() {
		return grupo;
	}
	
	
}
