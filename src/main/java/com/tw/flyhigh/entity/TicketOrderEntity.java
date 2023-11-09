package com.tw.flyhigh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket_order")
public class TicketOrderEntity extends AbstractEntity {
    private Long userId;
    private String flight;
    private String classType;
    private String contactMobile;
    private String contactName;
    private String status;

    @OneToMany(mappedBy = "order", orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("createdAt DESC")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<TicketOrderEventEntity> events = newArrayList();

    @OneToMany(mappedBy = "order", orphanRemoval = true, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<PassengerEntity> passengers = newArrayList();
}
