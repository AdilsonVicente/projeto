package relatorioskillusportal.repositorio.helper;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaContext;

import relatorioskillusportal.dto.VendaCiclo;

public interface VendasQueries {
	
	public List<VendaCiclo> totalPorCiclo(JpaContext context);
	
	//public BigDecimal totalVendaPorCiclo();

}
