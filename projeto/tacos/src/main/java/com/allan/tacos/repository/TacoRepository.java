package com.allan.tacos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allan.tacos.model.Taco;
import com.allan.tacos.repository.taco.TacoRepositoryQuery;

@Repository
public interface TacoRepository extends JpaRepository<Taco, Long>, TacoRepositoryQuery {

}
