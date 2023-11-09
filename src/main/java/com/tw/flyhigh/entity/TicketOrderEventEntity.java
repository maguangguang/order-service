package com.tw.flyhigh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticket_order_event")
public class TicketOrderEventEntity extends AbstractEntity {
    @Column(name = "ticket_order_id")
    private Long orderId;
    private String status;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    private TicketOrderEntity order;
}
