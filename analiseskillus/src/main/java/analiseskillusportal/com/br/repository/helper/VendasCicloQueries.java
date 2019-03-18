package analiseskillusportal.com.br.repository.helper;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import analiseskillusportal.com.br.dto.VendaCiclo;


public interface VendasCicloQueries {

	@Query(nativeQuery = true)
	public List<VendaCiclo> totalPorCiclo();
}
