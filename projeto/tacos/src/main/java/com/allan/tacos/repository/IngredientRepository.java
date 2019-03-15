package com.allan.tacos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.allan.tacos.model.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {

}
