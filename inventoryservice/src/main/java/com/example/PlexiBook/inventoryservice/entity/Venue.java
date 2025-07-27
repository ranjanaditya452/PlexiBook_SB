package com.example.PlexiBook.inventoryservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="venue")
public class Venue {

	@Id
	@Column(name ="id")
	private Long id;
	
	@Column(name ="name")
	private String name;
	
	@Column(name ="address")
	private String address;

	@Column(name ="total_capacity")
	private Long totalCapacity;
}