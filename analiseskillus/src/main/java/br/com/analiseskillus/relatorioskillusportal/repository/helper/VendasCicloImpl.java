package br.com.analiseskillus.relatorioskillusportal.repository.helper;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.analiseskillus.relatorioskillusportal.dto.VendaCiclo;


public class VendasCicloImpl implements VendasCicloQueries{

	
	private EntityManager manager;
	
	@Override
	public List<VendaCiclo> totalPorCiclo() {
		return manager.createNamedQuery("Vendas.totalPorCiclo", VendaCiclo.class).getResultList();
	}

	
}
