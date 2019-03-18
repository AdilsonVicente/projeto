package analiseskillus.testes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.transaction.annotation.Transactional;

@RunWith(JUnit4.class)
public class JpaMultipleTeste {

	@Test
	@Transactional("mysql")
	public void testar() {
		
	}
	
	@Test
	@Transactional("oracle")
	public void testar2() {
		
	}
	
}
