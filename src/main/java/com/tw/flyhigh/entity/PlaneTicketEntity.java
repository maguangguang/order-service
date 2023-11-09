package com.tw.flyhigh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OrderBy;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "plane_ticket")
public class PlaneTicketEntity extends AbstractEntity {
    private Long passengerId;
    private String ticketNo;
    private String eTicketNo;
    private Integer sn;
    private String flight;
    private String destination;
    private Date boardingDate;
    private String seatType;
    private Integer price;
    private String seat;
    private String gate;
    private String boardingTime;

    @OneToOne(orphanRemoval = true, mappedBy = "ticket")
    @MapsId
    @JoinColumn(name = "passenger", nullable = false, updatable = false)
    private PassengerEntity passenger;

    @OneToMany(mappedBy = "ticket", orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy(clause = "createdAt DESC")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PlaneTicketEventEntity> events = newArrayList();
}
