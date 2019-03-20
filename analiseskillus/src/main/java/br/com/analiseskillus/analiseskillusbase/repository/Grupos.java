package br.com.analiseskillus.analiseskillusbase.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.analiseskillus.analiseskillusbase.model.Grupo;


public interface Grupos extends JpaRepository<Grupo, Long>{

	public Optional<Grupo> findByNomeIgnoreCase(String nome);
}
