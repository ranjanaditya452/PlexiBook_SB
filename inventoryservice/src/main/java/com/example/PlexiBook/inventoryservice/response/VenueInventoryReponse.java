package com.example.PlexiBook.inventoryservice.response;

import com.example.PlexiBook.inventoryservice.entity.Venue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor 
public class VenueInventoryReponse {

	private Long venueId;
	private Long totalCapacity;
	private String venueName;	
}
