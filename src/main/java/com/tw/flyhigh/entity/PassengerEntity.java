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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "passenger")
public class PassengerEntity extends AbstractEntity {
    @Column(name = "ticket_order_id")
    private Long orderId;
    private String ticketNo;
    private String name;
    private Integer baggageWeight;
    private String mobile;
    private String identificationNumber;
    private String ageType;
    private Integer price;
    private String insuranceId;
    private String insuranceName;
    private Integer insurancePrice;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "passenger_id")
    private PlaneTicketEntity ticket;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, updatable = false)
    private TicketOrderEntity order;
}
