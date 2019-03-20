package br.com.analiseskillus.analiseskillusbase.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.analiseskillus.analiseskillusbase.model.Permissao;

public interface Permissoes extends JpaRepository<Permissao, Long>{

	public Optional<Permissao> findByNomeIgnoreCase(String nome);
}
