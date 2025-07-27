package com.example.PlexiBook.inventoryservice.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PlexiBook.inventoryservice.entity.Event;
import com.example.PlexiBook.inventoryservice.entity.Venue;
import com.example.PlexiBook.inventoryservice.repository.EventRepository;
import com.example.PlexiBook.inventoryservice.repository.VenueRepository;
import com.example.PlexiBook.inventoryservice.response.EventInventoryResponse;
import com.example.PlexiBook.inventoryservice.response.VenueInventoryReponse;

@Service
public class InventoryService {

	private final EventRepository eventRepository;
	private final VenueRepository venueRepository;
	
	@Autowired
	public InventoryService(final EventRepository eventRepository, final VenueRepository venueRepository)
	{
		this.eventRepository=eventRepository;
		this.venueRepository=venueRepository;
	}
	
	public List<EventInventoryResponse> getAllEvents() {
		final List<Event> events = eventRepository.findAll(); 
		 
		return events.stream().map(event -> EventInventoryResponse.builder()
				.event(event.getName())
				.capacity(event.getLeftCapacity())
				.venue(event.getVenue())
				.build()).collect(Collectors.toList());
				     
				
	}

	public VenueInventoryReponse getVenueById(final Long venueId) {
		final Venue venue = venueRepository.findById(venueId).orElse(null);
		
		return VenueInventoryReponse.builder()
				.venueId(venue.getId())
				.venueName(venue.getName())
				.totalCapacity(venue.getTotalCapacity()).build();
		
		
	}

}
