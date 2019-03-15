package com.allan.tacos.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.allan.tacos.model.Taco;
import com.allan.tacos.repository.TacoRepository;
import com.allan.tacos.repository.filter.TacoFilter;

@RestController
@RequestMapping("/design")
public class DesignTacoController {

	@Autowired
	private TacoRepository tacoRepository;

	@GetMapping
	public Page<Taco> list(TacoFilter tacoFilter, Pageable pageable) {
		return tacoRepository.filter(tacoFilter, pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Taco> findById(@PathVariable("id") Long id) {
		Taco taco = tacoRepository.findById(id).orElse(null);
		return taco != null ? ResponseEntity.ok(taco) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@Valid @RequestBody Taco taco) {
		return tacoRepository.save(taco);
	}
}
