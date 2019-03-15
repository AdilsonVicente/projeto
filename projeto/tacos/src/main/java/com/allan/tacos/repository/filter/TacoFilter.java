package com.allan.tacos.repository.filter;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TacoFilter {

	private String name;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date createdAtFrom;
	
	@DateTimeFormat(pattern ="yyyy-MM-dd")
	private Date createdAtUntil;
	
}
