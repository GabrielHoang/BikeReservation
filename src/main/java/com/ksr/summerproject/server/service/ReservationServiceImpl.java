package com.ksr.summerproject.server.service;

import com.ksr.summerproject.server.exceptions.*;
import com.ksr.summerproject.server.model.Bicycle;
import com.ksr.summerproject.server.model.Client;
import com.ksr.summerproject.server.model.Reservation;
import com.ksr.summerproject.server.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ClientService clientService;
    private final BicycleService bicycleService;
    private final ReservationRepository reservationRepository;


    public ReservationServiceImpl(ClientService clientService, BicycleService bicycleService, ReservationRepository reservationRepository) {
        this.clientService = clientService;
        this.bicycleService = bicycleService;
        this.reservationRepository = reservationRepository;
    }


    @Override
    public void create(int clientId, int bicycleId) throws ClientNotExistsException, BicycleOccupiedException, BicycleDeactivatedException, BicycleNotFoundException {
        Client foundClient = clientService.getClient(clientId);
        Bicycle foundBicycle = bicycleService.rentBicycle(bicycleId);

        Reservation reservation = new Reservation(java.sql.Timestamp.valueOf(LocalDateTime.now()),
                foundClient, foundBicycle);

        reservationRepository.save(reservation);
    }

    @Override
    public void finish(int clientId, int bicycleId) throws BicycleNotOccupiedException, BicycleNotFoundException, ClientNotExistsException {
        bicycleService.returnBicycle(bicycleId);

        Reservation foundReservation = reservationRepository.findByClient_idAndBicycle_id(clientId, bicycleId);
        reservationRepository.delete(foundReservation);
    }
}
