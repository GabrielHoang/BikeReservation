package com.ksr.summerproject.server.service;

import com.ksr.summerproject.server.exceptions.*;

public interface ReservationService {

    void create(int clientId, int bicycleId) throws ClientNotExistsException, BicycleOccupiedException, BicycleDeactivatedException, BicycleNotFoundException, ClientAlreadyHasReservation;

    void finish(int clientId, int bicycleId) throws BicycleNotOccupiedException, BicycleNotFoundException, ClientNotExistsException, ClientHasNoReservationException;
}
