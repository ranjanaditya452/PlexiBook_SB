package com.example.PlexiBook.inventoryservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.PlexiBook.inventoryservice.response.EventInventoryResponse;
import com.example.PlexiBook.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {

	@Autowired
    private	InventoryService inventoryService;
	
	@GetMapping("/inventory/events")
	public @ResponseBody List<EventInventoryResponse>()
	{
		return inventoryService.getAllEvents();
	}



}