package com.tw.flyhigh.repository;

import com.tw.flyhigh.entity.PlaneTicketEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneTicketEventRepository extends JpaRepository<PlaneTicketEventEntity, Long> {
}
