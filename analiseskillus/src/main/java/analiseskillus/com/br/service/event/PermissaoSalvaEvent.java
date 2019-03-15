package analiseskillus.com.br.service.event;

import org.springframework.beans.factory.annotation.Autowired;

import analiseskillus.com.br.model.Permissao;

public class PermissaoSalvaEvent {

	@Autowired
	private Permissao permissao;
	
	public PermissaoSalvaEvent(Permissao permissao) {
		this.permissao = permissao;
	}

	public Permissao getPermisao() {
		return permissao;
	}

	
}
