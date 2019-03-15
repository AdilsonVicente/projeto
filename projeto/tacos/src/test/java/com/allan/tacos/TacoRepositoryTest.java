package com.allan.tacos;

import static org.junit.Assert.assertNotNull;

import javax.transaction.Transactional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.allan.tacos.model.Ingredient;
import com.allan.tacos.model.Taco;
import com.allan.tacos.model.Type;
import com.allan.tacos.repository.TacoRepository;
import com.allan.tacos.repository.filter.TacoFilter;

@SuppressWarnings("static-access")
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class TacoRepositoryTest {

	@Autowired
	private TacoRepository tacoRepository;
	protected static Long entityId;
	
	
	@Test
	public void test_A_insertTaco() {
		Taco taco = new Taco();
		taco.getIngredients().add(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
		taco.setName("Test Junit");
		tacoRepository.save(taco);
		this.entityId = taco.getId();
		assertNotNull(taco.getId());
	}
	
	@Test
	public void test_B_editTaco() {
		Taco taco = tacoRepository.getOne(entityId);
		taco.setName("Test Junit Edit");
		tacoRepository.save(taco);
	}	
	
	@Test
	public void test_C_getWithPaginationAndFilter() {
		TacoFilter tacoFilter = new TacoFilter();
		tacoFilter.setName("Test Junit");
		Page<Taco> result = tacoRepository.filter(tacoFilter, PageRequest.of(0, 10));
		assertNotNull(result);
	}
	
	@Test
	public void test_D_deleteTaco() {
		Taco taco = tacoRepository.getOne(entityId);
		tacoRepository.delete(taco);
	}	
}
