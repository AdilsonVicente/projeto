package br.com.analiseskillus.relatorioskillusportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.analiseskillus.relatorioskillusportal.model.RelatorioPortal;
import br.com.analiseskillus.relatorioskillusportal.repository.helper.VendasCicloQueries;

@Repository
public interface RelatoriosPortal extends JpaRepository<RelatorioPortal, Long>, VendasCicloQueries{

	
}
