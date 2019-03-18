package analiseskillusportal.com.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import analiseskillusportal.com.br.model.RelatorioPortal;
import analiseskillusportal.com.br.repository.helper.VendasCicloQueries;

@Repository
public interface RelatoriosPortal extends JpaRepository<RelatorioPortal, Long>, VendasCicloQueries{

	
}
