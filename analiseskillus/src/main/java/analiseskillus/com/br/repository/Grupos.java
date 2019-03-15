package analiseskillus.com.br.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import analiseskillus.com.br.model.Grupo;


public interface Grupos extends JpaRepository<Grupo, Long>{

	public Optional<Grupo> findByNomeIgnoreCase(String nome);
}
