package com.example.PlexiBook.inventoryservice.response;

import com.example.PlexiBook.inventoryservice.entity.Venue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventInventoryResponse {

	private  Long eventId;
	private String event;
	private Long capacity;
	private Venue  venue;
	private BigDecimal ticketPrice;

}
