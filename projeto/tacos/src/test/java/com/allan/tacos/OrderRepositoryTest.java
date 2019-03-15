package com.allan.tacos;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.allan.tacos.model.Order;
import com.allan.tacos.model.User;
import com.allan.tacos.repository.OrderRepository;
import com.allan.tacos.repository.UserRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Test
	public void getByUserTest() {
		Optional<User> userOptional = userRepository.findByUsername("allanbraga.dvlp@gmail.com");
		
		assertTrue(userOptional.isPresent());
		
		Pageable pageable = PageRequest.of(0, 20);
		List<Order> oders = orderRepository.findByUserOrderByPlacedAtDesc(userOptional.get(), pageable);
		System.out.println(oders);
	}
	
}
