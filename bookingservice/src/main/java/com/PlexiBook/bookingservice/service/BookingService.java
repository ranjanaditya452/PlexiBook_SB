package com.PlexiBook.bookingservice.service;

import com.PlexiBook.bookingservice.client.InventoryServiceClient;
import com.PlexiBook.bookingservice.entity.Customer;
import com.PlexiBook.bookingservice.event.BookingEvent;
import com.PlexiBook.bookingservice.repository.CustomerRepository;
import com.PlexiBook.bookingservice.request.BookingRequest;
import com.PlexiBook.bookingservice.response.BookingResponse;
import com.PlexiBook.bookingservice.response.InventoryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class BookingService {

    private final CustomerRepository customerRepository;
    private final InventoryServiceClient inventoryServiceClient;
    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;

    @Autowired
    public BookingService(final CustomerRepository customerRepository, final InventoryServiceClient inventoryServiceClient,
                          final KafkaTemplate<String, BookingEvent> kafkaTemplate) {
        this.customerRepository = customerRepository;
        this.inventoryServiceClient=inventoryServiceClient;
        this.kafkaTemplate=kafkaTemplate;
    }

    public BookingResponse createBooking(final BookingRequest request) {
        //checking if the customer exists
        final Customer customer = customerRepository.findById(request.getUserId()).orElse(null);
        if (customer == null) {
            throw new RuntimeException("User Not Found");
        }
        //check inventory space
        final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(request.getEventId());
        log.info("Inventory Response: {}", inventoryResponse);
        if(inventoryResponse.getCapacity()<request.getTicketCount() )
        {
            throw new RuntimeException("Not enough inventory");
        }
        //if cond passed create booking
        final BookingEvent bookingEvent = createBookingEvent(request,customer,inventoryResponse);
        //send booking event to OrderService on kafka topic
        kafkaTemplate.send("booking",bookingEvent);
        log.info("Booking sent to Kafka: {}", bookingEvent);

        return BookingResponse.builder()
                .userId(bookingEvent.getUserId())
                .ticketCount(bookingEvent.getTicketCount())
                .ticketPrice(bookingEvent.getTotalPrice())
                .eventId(bookingEvent.getEventId())
                .build();
    }

    private BookingEvent createBookingEvent(final BookingRequest request,final Customer customer,final InventoryResponse inventoryResponse) {

        return BookingEvent.builder()
                .userId(customer.getId())
                .eventId(request.getEventId())
                .ticketCount(request.getTicketCount())
                .totalPrice(inventoryResponse.getTicketPrice().multiply(BigDecimal.valueOf(request.getTicketCount())))
                .build();
    }

}