package com.example.PlexiBook.inventoryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.PlexiBook.inventoryservice.response.EventInventoryResponse;
import com.example.PlexiBook.inventoryservice.response.VenueInventoryReponse;
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
	public VenueInventoryReponse inventoryByVenueId(@PathVariable Long venueId)
	{
		return inventoryService.getVenueById(venueId);
	}

}