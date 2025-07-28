package com.example.PlexiBook.inventoryservice.controller;

import java.util.List;

import com.example.PlexiBook.inventoryservice.response.VenueInventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.PlexiBook.inventoryservice.response.EventInventoryResponse;
import com.example.PlexiBook.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {


    private	InventoryService inventoryService;
	
    @Autowired
    public InventoryController(final InventoryService inventoryService)
    {
    	this.inventoryService=inventoryService;
    }
    
	@GetMapping("/inventory/events")
	public @ResponseBody List<EventInventoryResponse> inventoryGetAllEvents()
	{
		return inventoryService.getAllEvents();
	}

	@GetMapping("/inventory/venue/{venueId}")
	public VenueInventoryResponse inventoryByVenueId(@PathVariable Long venueId)
	{
		return inventoryService.getVenueById(venueId);
	}

	@GetMapping("/inventory/event/{eventId}")
	public @ResponseBody EventInventoryResponse inventoryForEvent(@PathVariable long eventId)
	{
		return inventoryService.getEventInventory(eventId);
	}

}