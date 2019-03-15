package analiseskillus.com.br.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import analiseskillus.com.br.model.Permissao;

public interface Permissoes extends JpaRepository<Permissao, Long>{

	public Optional<Permissao> findByNomeIgnoreCase(String nome);
}
