package com.allan.tacos.repository.taco;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.allan.tacos.model.QTaco;
import com.allan.tacos.model.Taco;
import com.allan.tacos.repository.filter.TacoFilter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

public class TacoRepositoryQueryImpl extends QuerydslRepositorySupport implements TacoRepositoryQuery {

	public TacoRepositoryQueryImpl() {
		super(Taco.class);
	}

	@Override
	public Page<Taco> filter(TacoFilter tacoFilter, Pageable page) {
		QTaco taco = QTaco.taco;
		BooleanBuilder builder = new BooleanBuilder();
		JPQLQuery<Taco> query = from(taco);
		
		// Apply Name
		Optional.ofNullable(tacoFilter.getName()).ifPresent(name -> builder.and(taco.name.like("%" + tacoFilter.getName() + "%")));
		
		// Apply From
		Optional.ofNullable(tacoFilter.getCreatedAtFrom()).ifPresent(from -> builder.and(taco.createdAt.after(from)));
		
		// Apply Until
		Optional.ofNullable(tacoFilter.getCreatedAtUntil()).ifPresent(until -> builder.and(taco.createdAt.before(until)));
		
		// Apply Where
		query.where(builder);
		
		List<Taco> resultList = getQuerydsl().applyPagination(page, query).fetch();
		return new PageImpl<>(resultList, page, query.fetchCount());
	}
}
