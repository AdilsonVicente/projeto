package analiseskillus.com.br.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


import analiseskillus.com.br.model.Usuario;
import analiseskillus.com.br.repository.Usuarios;
import analiseskillus.com.br.service.event.usuario.UsuarioSalvaEvent;
import analiseskillus.com.br.service.exception.EmailUsuarioJaCadastradoException;
import analiseskillus.com.br.service.exception.ImpossivelApagarUsuarioException;
import analiseskillus.com.br.service.exception.SenhaObrigatoriaUsuarioException;
import analiseskillus.com.br.storage.FotoStorage;

@Service
public class CadastroUsuarioService {

	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private FotoStorage fotoStorage;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarios.findByEmail(usuario.getEmail());
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new EmailUsuarioJaCadastradoException("E-mail já cadastrado");
		}
		
		if(usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("Senha é obrigatória para um novo usuário");
		}
		
		if(usuario.isNovo() || !StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		}else if(StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(usuarioExistente.get().getSenha());
			
		}
		
		usuario.setConfirmacaoSenha(usuario.getSenha());
		
		if(!usuario.isNovo() && usuario.getAtivo() != null) {
			usuario.setAtivo(usuarioExistente.get().getAtivo());
		}
		
		usuarios.save(usuario);
		
		publisher.publishEvent(new UsuarioSalvaEvent(usuario));
}

	@Transactional
	public void alteraStatus(Long[] codigos, StatusUsuario statusUsuario) {
		statusUsuario.executar(codigos, usuarios);
		
	}
	
	@Transactional
	public void excluir(Usuario usuario) {
		try {
			String foto = usuario.getFoto();
			usuarios.delete(usuario);
			usuarios.flush();
			fotoStorage.excluir(foto);
		}catch(PersistenceException e) {
			throw new ImpossivelApagarUsuarioException("impossível apagar usuário!");
		}
	}
}
