package analiseskillus.com.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import analiseskillus.com.br.model.Permissao;
import analiseskillus.com.br.repository.Permissoes;
import analiseskillus.com.br.service.event.PermissaoSalvaEvent;

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
