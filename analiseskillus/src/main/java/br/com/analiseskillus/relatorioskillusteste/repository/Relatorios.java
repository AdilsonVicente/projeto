package br.com.analiseskillus.relatorioskillusteste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.analiseskillus.relatorioskillusteste.model.Relatorio;

@Repository
public interface Relatorios extends JpaRepository<Relatorio, Long>{

}
