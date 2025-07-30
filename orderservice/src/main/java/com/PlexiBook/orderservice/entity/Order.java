package com.PlexiBook.orderservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "`order`")
public class Order {

    @Id
    private long id;

    @Column(name = "total")
    private BigDecimal totalPrice;

    @Column(name = "quantity")
    private Long ticketCount;

    @CreationTimestamp
    @Column(name = "placed_at",updatable = false,nullable = false)
    private LocalDateTime placedAt;

    @Column(name="customer_id")
    private Long customerId;

    @Column(name="event_id")
    private Long eventId;
}
