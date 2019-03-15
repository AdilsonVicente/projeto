package com.allan.tacos.repository.taco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.allan.tacos.model.Taco;
import com.allan.tacos.repository.filter.TacoFilter;

public interface TacoRepositoryQuery {
	
	Page<Taco> filter(TacoFilter tacoFilter, Pageable pageable);
	
}
