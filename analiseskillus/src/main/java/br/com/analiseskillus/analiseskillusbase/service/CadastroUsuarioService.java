package br.com.analiseskillus.analiseskillusbase.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.analiseskillus.analiseskillusbase.model.Usuario;
import br.com.analiseskillus.analiseskillusbase.repository.Usuarios;
import br.com.analiseskillus.analiseskillusbase.service.event.usuario.UsuarioSalvaEvent;
import br.com.analiseskillus.analiseskillusbase.service.exception.EmailUsuarioJaCadastradoException;
import br.com.analiseskillus.analiseskillusbase.service.exception.ImpossivelApagarUsuarioException;
import br.com.analiseskillus.analiseskillusbase.service.exception.SenhaObrigatoriaUsuarioException;
import br.com.analiseskillus.analiseskillusbase.storage.FotoStorage;

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
