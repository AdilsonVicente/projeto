package br.com.analiseskillus.analiseskillusbase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.analiseskillus.analiseskillusbase.model.Permissao;
import br.com.analiseskillus.analiseskillusbase.repository.Permissoes;
import br.com.analiseskillus.analiseskillusbase.service.event.PermissaoSalvaEvent;

@Service
public class CadastroPermissaoService {

	@Autowired
	private Permissoes permissoes;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void salvar(Permissao permissao) {
		permissoes.save(permissao);
		
		publisher.publishEvent(new PermissaoSalvaEvent(permissao));
	}

}
