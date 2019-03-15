package com.allan.tacos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.allan.tacos.model.Ingredient;
import com.allan.tacos.model.Type;
import com.allan.tacos.repository.IngredientRepository;
 
@Service
public class IngredientService {
	
	@Autowired
	private IngredientRepository ingredientRepository;

	public Ingredient[] filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream().filter(i -> i.getType() == type).toArray(Ingredient[]::new);
	}
	
	public void addIngredientsInModelByTypes(Model model) {
		List<Ingredient> ingredients = ingredientRepository.findAll();
		Type[] types = Type.values();
		for(Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),  filterByType(ingredients, type));
		}
	}
}
