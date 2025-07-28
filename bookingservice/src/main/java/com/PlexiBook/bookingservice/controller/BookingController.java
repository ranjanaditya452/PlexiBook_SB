package com.PlexiBook.bookingservice.controller;

import com.PlexiBook.bookingservice.request.BookingRequest;
import com.PlexiBook.bookingservice.response.BookingResponse;
import com.PlexiBook.bookingservice.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(final BookingService bookingService)
    {
        this.bookingService=bookingService;
    }

    @PostMapping("/booking")
    public BookingResponse createBooking(@RequestBody BookingRequest request)
    {
        return bookingService.createBooking(request);
    }
}
