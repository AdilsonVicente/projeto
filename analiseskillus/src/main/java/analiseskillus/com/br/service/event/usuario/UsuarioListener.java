package analiseskillus.com.br.service.event.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import analiseskillus.com.br.storage.FotoStorage;

@Component
public class UsuarioListener {

	@Autowired
	private FotoStorage fotoStorage;
	
	@EventListener(condition = "#event.temFoto() and #event.novaFoto()")
	public void usuarioSalva(UsuarioSalvaEvent event) {
		System.out.println(">>> novo usuario salvo" + event.getUsuario().getNome());
		fotoStorage.salvar(event.getUsuario().getFoto());
	}
}
