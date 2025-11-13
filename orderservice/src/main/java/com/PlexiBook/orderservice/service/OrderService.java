package com.PlexiBook.orderservice.service;

import com.PlexiBook.bookingservice.event.BookingEvent;
import com.PlexiBook.orderservice.client.InventoryServiceClient;
import com.PlexiBook.orderservice.entity.Order;
import com.PlexiBook.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryServiceClient inventoryServiceClient;

    @Autowired
    public OrderService(final OrderRepository orderRepository,final InventoryServiceClient inventoryServiceClient)
    {
        this.orderRepository=orderRepository;
        this.inventoryServiceClient=inventoryServiceClient;
    }

    //this method is called automatically whenever it recieves a bookingevent from kafka
    @KafkaListener(topics = "booking", groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent) {
        log.info("Recieved order event: {}", bookingEvent);

        //create order obj for database
        Order order = createOrder(bookingEvent);
        orderRepository.saveAndFlush(order);
        //update inventory
        inventoryServiceClient.updateInventory(order.getEventId(),order.getTicketCount());
        log.info("Inventory Updated for event: {}, minus tickets: {}",order.getEventId(),order.getTicketCount());
    }

    private Order createOrder(BookingEvent bookingEvent)
    {
        return Order.builder()
                .customerId(bookingEvent.getUserId())
                .ticketCount(bookingEvent.getTicketCount())
                .eventId(bookingEvent.getEventId())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }

}
