package com.example.PlexiBook.inventoryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PlexiBook.inventoryservice.response.EventInventoryResponse;

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
		
	}

}
