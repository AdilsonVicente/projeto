package com.allan.tacos.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.allan.tacos.model.Order;
import com.allan.tacos.model.User;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
	
	List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
	
}
