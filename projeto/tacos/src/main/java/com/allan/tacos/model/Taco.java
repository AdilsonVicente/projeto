package com.allan.tacos.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "taco")
public class Taco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "taco_ingredient", 
		joinColumns = { @JoinColumn(name = "taco") }, 
		inverseJoinColumns = {@JoinColumn(name = "ingredient")})
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	private List<Ingredient> ingredients = new ArrayList<>();
	
	@PrePersist
	public void createdAt() {
		this.createdAt = new Date();
	}
}
