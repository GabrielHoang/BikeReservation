package com.ksr.summerproject.server.service;

import com.ksr.summerproject.server.exceptions.*;
import com.ksr.summerproject.server.model.Bicycle;

import java.util.List;

public interface BicycleService {
        String ACTIVE = "ACTIVE";
        String INACTIVE = "INACTIVE";
        String DEACTIVATED = "DEACTIVATED";
        String OCCUPIED = "OCCUPIED";
        String OUT_OF_ORDER = "OUT_OF_ORDER";


    void addNewBicycle(int location, String status);

    Bicycle rentBicycle(int id) throws BicycleNotFoundException, BicycleDeactivatedException, BicycleOccupiedException;

    void returnBicycle(int id) throws BicycleNotFoundException, BicycleNotOccupiedException;

    void deactivateBicycle(int id) throws BicycleNotFoundException;

    Bicycle getBicycle(int id) throws BicycleNotFoundException;

    List<Bicycle> getBicyclesInArea(int location) throws BicyclesNotFoundInLocationException;
}
