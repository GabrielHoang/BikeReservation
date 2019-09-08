package com.ksr.summerproject.server.controller;

import com.ksr.summerproject.server.exceptions.*;
import com.ksr.summerproject.server.service.ReservationService;
import com.ksr.summerproject.server.service.ReservationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/create")
    ResponseEntity createReservation(@RequestParam("clientId") int clientId,
                                     @RequestParam("bicycleId") int bicycleId) {
        try {
            reservationService.create(clientId, bicycleId);
            return ResponseEntity.ok("Reservation successfull.");
        } catch (ClientNotExistsException | BicycleOccupiedException | BicycleDeactivatedException | BicycleNotFoundException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @DeleteMapping("/finish")
    ResponseEntity finishReservation(@RequestParam("clientId") int clientId,
                                     @RequestParam("bicycleId") int bicycleId) {
        try {
            reservationService.finish(clientId, bicycleId);
            return ResponseEntity.ok("Reservation successfully finished.");
        } catch (BicycleNotOccupiedException | BicycleNotFoundException | ClientNotExistsException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

}
