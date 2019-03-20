package br.com.analiseskillus.analiseskillusbase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.analiseskillus.analiseskillusbase.model.Grupo;
import br.com.analiseskillus.analiseskillusbase.repository.Grupos;
import br.com.analiseskillus.analiseskillusbase.service.event.GrupoSalvaEvent;

@Service
public class CadastroGrupoService {

	@Autowired
	private Grupos grupos;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void salvar(Grupo grupo) {
		grupos.save(grupo);
		
		publisher.publishEvent(new GrupoSalvaEvent(grupo));
	}

}
