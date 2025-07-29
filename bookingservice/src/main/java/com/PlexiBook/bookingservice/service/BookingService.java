package com.PlexiBook.bookingservice.service;

import com.PlexiBook.bookingservice.client.InventoryServiceClient;
import com.PlexiBook.bookingservice.entity.Customer;
import com.PlexiBook.bookingservice.repository.CustomerRepository;
import com.PlexiBook.bookingservice.request.BookingRequest;
import com.PlexiBook.bookingservice.response.BookingResponse;
import com.PlexiBook.bookingservice.response.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final CustomerRepository customerRepository;
    private final InventoryServiceClient inventoryServiceClient;

    @Autowired
    public BookingService(final CustomerRepository customerRepository, final InventoryServiceClient inventoryServiceClient) {
        this.customerRepository = customerRepository;
        this.inventoryServiceClient=inventoryServiceClient;
    }

    public BookingResponse createBooking(final BookingRequest request) {
        //checking if the customer exists
        final Customer customer = customerRepository.findById(request.getUserId()).orElse(null);
        if (customer == null) {

            throw new RuntimeException("User Not Found");
        }
        //check inventory space
        final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(request.getEventId());
        if(inventoryResponse.getCapacity()<request.getTicketCount() )
        {
            throw new RuntimeException("Not enough inventory");
        }
        //if cond passed create booking


        return BookingResponse.builder().build();
    }
}