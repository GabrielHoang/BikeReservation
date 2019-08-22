package com.ksr.summerproject.server.repository;

import com.ksr.summerproject.server.model.Bicycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BicycleRepository extends JpaRepository<Bicycle, Integer>, BicycleRepositoryCustom {
}
