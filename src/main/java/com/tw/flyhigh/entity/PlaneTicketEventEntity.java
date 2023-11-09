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
@Table(name = "plane_ticket_event")
public class PlaneTicketEventEntity extends AbstractEntity {
    @Column(name = "plane_ticket_id")
    private Long ticketId;
    private String status;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false, updatable = false)
    private PlaneTicketEntity ticket;
}
