package com.ksr.summerproject.server.repository;

import com.ksr.summerproject.server.model.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BicycleRepository extends JpaRepository<Bicycle, Integer> {
}
