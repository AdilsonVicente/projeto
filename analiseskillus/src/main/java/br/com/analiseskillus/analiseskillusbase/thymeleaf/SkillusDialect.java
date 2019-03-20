package br.com.analiseskillus.analiseskillusbase.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import br.com.analiseskillus.analiseskillusbase.thymeleaf.processor.ClassForErrorAtributeTagProcessor;
import br.com.analiseskillus.analiseskillusbase.thymeleaf.processor.MenuAttributeTagProcessor;
import br.com.analiseskillus.analiseskillusbase.thymeleaf.processor.MessageElementTagProcessor;
import br.com.analiseskillus.analiseskillusbase.thymeleaf.processor.OrderElementTagProcessor;
import br.com.analiseskillus.analiseskillusbase.thymeleaf.processor.PaginatioElementTagProcessor;

@Component
public class SkillusDialect extends AbstractProcessorDialect{

	public SkillusDialect() {
		super("Analise Skillus", "skillus", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAtributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementTagProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginatioElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
		return processadores;
	}

}
