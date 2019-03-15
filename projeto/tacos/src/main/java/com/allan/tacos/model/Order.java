package com.allan.tacos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

@Data
@Entity
@Table(name = "taco_order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "placed_at")
	private Date placedAt;
	
	@NotBlank(message = "Name is required")
	@Column(name = "delivery_name")
	private String name;

	@NotBlank(message = "Street is required")
	@Column(name = "delivery_street")
	private String street;

	@NotBlank(message = "City is required")
	@Column(name = "delivery_city")
	private String city;

	@NotBlank(message = "State is required")
	@Column(name = "delivery_state")
	private String state;

	@NotBlank(message = "Zip code is required")
	@Column(name = "delivery_zip")
	private String zip;

	@CreditCardNumber(message = "Not a valid credit card number")
	@Column(name = "cc_number")
	private String ccNumber;

	@Pattern(regexp = "^(0[1-9]|1[0-2])([\\\\/])([1-9][0-9])$",
			 message= "Must be formated MM/YY")
	private String ccExpiration;

	@Digits(integer = 3, fraction = 0, message = "Invalid CCV")
	@Column(name = "cc_cvv")
	private String ccCVV;
	
	@ManyToOne
	@JoinColumn(name = "user")
	private User user;
	
	@ManyToMany
	@JoinTable(name="taco_order_tacos", 
			  joinColumns = { @JoinColumn(name="taco_order") }, 
			  inverseJoinColumns = { @JoinColumn(name="taco") })
	private List<Taco> tacos = new ArrayList<>();
	
	public void addDesign(Taco taco) {
		tacos.add(taco);
	}
	
	@PrePersist
	void placedAt() {
		this.placedAt = new Date();
	}
}
