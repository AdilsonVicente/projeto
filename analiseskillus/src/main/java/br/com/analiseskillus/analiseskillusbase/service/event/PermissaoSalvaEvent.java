package br.com.analiseskillus.analiseskillusbase.service.event;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.analiseskillus.analiseskillusbase.model.Permissao;

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
