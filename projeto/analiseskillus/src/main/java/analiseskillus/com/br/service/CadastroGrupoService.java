package analiseskillus.com.br.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import analiseskillus.com.br.model.Grupo;
import analiseskillus.com.br.repository.Grupos;
import analiseskillus.com.br.service.event.GrupoSalvaEvent;

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
