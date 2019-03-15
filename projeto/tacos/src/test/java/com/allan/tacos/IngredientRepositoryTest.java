package com.allan.tacos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.allan.tacos.repository.IngredientRepository;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class IngredientRepositoryTest {

	@Autowired
	private IngredientRepository repository;
	
	@Test
	public void testFindAll() {
		System.out.println(repository.findAll());
	}
}
