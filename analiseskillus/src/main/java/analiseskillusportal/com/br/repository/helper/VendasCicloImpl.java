package analiseskillusportal.com.br.repository.helper;

import java.util.List;

import javax.persistence.EntityManager;

import analiseskillusportal.com.br.dto.VendaCiclo;


public class VendasCicloImpl implements VendasCicloQueries{

	
	private EntityManager manager;
	
	@Override
	public List<VendaCiclo> totalPorCiclo() {
		return manager.createNamedQuery("Vendas.totalPorCiclo", VendaCiclo.class).getResultList();
	}

	
}
