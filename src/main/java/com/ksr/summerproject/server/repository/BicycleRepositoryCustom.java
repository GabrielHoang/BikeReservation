package com.ksr.summerproject.server.repository;

import com.ksr.summerproject.server.model.Bicycle;

import java.util.List;

public interface BicycleRepositoryCustom {

    List<Bicycle> findBicyclesByLocationBetween(int startLocation, int endLocation);
}
