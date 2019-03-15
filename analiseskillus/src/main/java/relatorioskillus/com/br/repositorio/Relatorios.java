package relatorioskillus.com.br.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import relatorioskillus.com.br.model.Relatorio;

@Repository
public interface Relatorios extends JpaRepository<Relatorio, Long>{

}
