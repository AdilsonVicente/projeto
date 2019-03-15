package com.allan.tacos.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.allan.tacos.model.Order;
import com.allan.tacos.model.Taco;
import com.allan.tacos.repository.TacoRepository;
import com.allan.tacos.service.IngredientService;

import lombok.extern.slf4j.Slf4j;

 
@Slf4j
@Controller
@RequestMapping("/designmpa")
@SessionAttributes("order")
public class DesignTacoControllerMpa {
	
	@Autowired
	private IngredientService ingredientService;
	
	@Autowired
	private TacoRepository tacoRepository;
	
	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		ingredientService.addIngredientsInModelByTypes(model);
		model.addAttribute("design", new Taco());
		return "design";
	}
	
	@PostMapping
	public String processDesign(@Valid @ModelAttribute("design") Taco design, @ModelAttribute("order") Order order, Errors errors) {
		if (errors.hasErrors()) {
			return "design";
		}
		Taco saved = tacoRepository.save(design);
		order.addDesign(saved);
		log.info("Design: ", design);
		return "redirect:/orders/current";
	}
}
