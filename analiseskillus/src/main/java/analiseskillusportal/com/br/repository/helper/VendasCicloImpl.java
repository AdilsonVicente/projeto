package analiseskillusportal.com.br.repository.helper;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import analiseskillusportal.com.br.dto.VendaCiclo;


public class VendasCicloImpl implements VendasCicloQueries{

	@Autowired
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<VendaCiclo> totalPorCiclo() {
		return manager.createNamedQuery("Vendas.totalPorCiclo").getResultList();
	}

	
}
