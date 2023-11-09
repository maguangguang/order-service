package com.tw.flyhigh.repository;

import com.tw.flyhigh.entity.PassengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassengerRepository extends JpaRepository<PassengerEntity, Long> {
    List<PassengerEntity> findByOrderId(Long orderId);
}
