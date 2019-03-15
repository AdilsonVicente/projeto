package analiseskillus.com.br.service.event;

import org.springframework.beans.factory.annotation.Autowired;

import analiseskillus.com.br.model.Grupo;

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
