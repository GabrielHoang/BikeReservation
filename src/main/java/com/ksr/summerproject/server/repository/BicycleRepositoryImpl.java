package com.ksr.summerproject.server.repository;

import com.ksr.summerproject.server.model.Bicycle;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BicycleRepositoryImpl implements BicycleRepositoryCustom {

    private final BicycleRepository bicycleRepository;

    public BicycleRepositoryImpl(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    @Override
    public List<Bicycle> findBicyclesByLocationBetween(int startLocation, int endLocation) {
        return bicycleRepository.findBicyclesByLocationBetween(startLocation,endLocation);
    }
}
