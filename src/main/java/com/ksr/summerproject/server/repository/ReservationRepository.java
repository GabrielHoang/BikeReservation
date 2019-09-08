package com.ksr.summerproject.server.repository;

import com.ksr.summerproject.server.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Reservation findByClient_idAndBicycle_id(int clientId, int bicycleId);

}
