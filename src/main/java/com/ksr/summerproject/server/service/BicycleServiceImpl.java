package com.ksr.summerproject.server.service;

import com.ksr.summerproject.server.exceptions.*;
import com.ksr.summerproject.server.model.Bicycle;
import com.ksr.summerproject.server.repository.BicycleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(timeout = 5)
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
    }

    @Override
    public Bicycle rentBicycle(int id) throws BicycleNotFoundException, BicycleDeactivatedException, BicycleOccupiedException {
        Optional<Bicycle> foundBicycle = bicycleRepository.findById(id);
        if (foundBicycle.isPresent()) {
            if (foundBicycle.get().getStatus().equals(DEACTIVATED)) {
                throw new BicycleDeactivatedException("Bicycle of id: " + id + " is not available for rent.");
            } else if (foundBicycle.get().getStatus().equals(OCCUPIED)) {
                throw new BicycleOccupiedException("Bicycle of id: " + id + " already occupied");
            } else {
                foundBicycle.get().setStatus(OCCUPIED);
                return foundBicycle.get();
            }
        } else {
            throw new BicycleNotFoundException("Bicycle of id: " + id + " was not found.");
        }
    }

    @Override
    public void returnBicycle(int id) throws BicycleNotFoundException, BicycleNotOccupiedException {
        Optional<Bicycle> bicycle = bicycleRepository.findById(id);
        if (bicycle.isPresent()) {
            if (!bicycle.get().getStatus().equals(OCCUPIED)) {
                throw new BicycleNotOccupiedException("Bicycle has been already returned.");
            }
            bicycle.get().setStatus(ACTIVE);
        } else {
            throw new BicycleNotFoundException("Bicycle of id: " + id + " was not found.");
        }
    }

    @Override
    public void deactivateBicycle(int id) throws BicycleNotFoundException {
        Optional<Bicycle> bicycle = bicycleRepository.findById(id);
        if(bicycle.isPresent()) {
            bicycle.get().setStatus(DEACTIVATED);
        } else {
            throw new BicycleNotFoundException("Bicycle of id: " + id + " does not exist");
        }
    }

    @Override
    public Bicycle getBicycle(int id) throws BicycleNotFoundException {
        Optional<Bicycle> bicycle = bicycleRepository.findById(id);
        if (bicycle.isPresent()) {
            return bicycle.get();
        } else {
            throw new BicycleNotFoundException("Bicycle of id: " + id + " not found.");
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
