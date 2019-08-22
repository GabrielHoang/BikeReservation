package com.ksr.summerproject.server.service;

import com.ksr.summerproject.server.exceptions.BicycleDeactivatedException;
import com.ksr.summerproject.server.exceptions.BicycleNotFoundException;
import com.ksr.summerproject.server.exceptions.BicyclesNotFoundInLocationException;
import com.ksr.summerproject.server.model.Bicycle;
import com.ksr.summerproject.server.repository.BicycleRepository;

import java.util.List;
import java.util.Optional;

public class BicycleServiceImpl implements BicycleService {

    private final BicycleRepository bicycleRepository;

    public BicycleServiceImpl(BicycleRepository bicycleRepository) {
        this.bicycleRepository = bicycleRepository;
    }

    @Override
    public void addNewBicycle(int location, String status) {
        Bicycle bicycle = new Bicycle();
        bicycle.setStatus(ACTIVE);
        bicycle.setLocation(location);

        bicycleRepository.save(bicycle);
    }

    @Override
    public Bicycle rentBicycle(int id) throws BicycleNotFoundException, BicycleDeactivatedException {
        Optional<Bicycle> foundBicycle = bicycleRepository.findById(id);
        if (foundBicycle.isPresent()) {
            if (foundBicycle.get().getStatus().equals(DEACTIVATED)) {
                throw new BicycleDeactivatedException("Bicycle of id: " + id + " is not available for rent.");
            } else {
                foundBicycle.get().setStatus(OCCUPIED);
                bicycleRepository.save(foundBicycle.get());
                return foundBicycle.get();
            }
        } else {
            throw new BicycleNotFoundException("Bicycle of id: " + id + " was not found.");
        }
    }

    @Override
    public void returnBicycle(int id) throws BicycleNotFoundException {
        Optional<Bicycle> bicycle = bicycleRepository.findById(id);
        if (bicycle.isPresent()) {
            bicycle.get().setStatus(ACTIVE);
            bicycleRepository.save(bicycle.get());
        } else {
            throw new BicycleNotFoundException("Bicycle of id: " + id + " was not found.");
        }
    }

    @Override
    public void deactivateBicycle(int id) throws BicycleNotFoundException {
        Optional<Bicycle> bicycle = bicycleRepository.findById(id);
        if(bicycle.isPresent()) {
            bicycle.get().setStatus(DEACTIVATED);
            bicycleRepository.save(bicycle.get());
        } else {
            throw new BicycleNotFoundException("Bicycle of id: " + id + " does not exist");
        }
    }

    @Override
    public List<Bicycle> getBicyclesInArea(int location) throws BicyclesNotFoundInLocationException {
        List<Bicycle> bicycleList = bicycleRepository.findBicyclesByLocationBetween(location-1,location+1);
        if (bicycleList.size() == 0) {
            throw new BicyclesNotFoundInLocationException("No bicycles were found around.");
        } else {
            return bicycleList;
        }
    }
}
