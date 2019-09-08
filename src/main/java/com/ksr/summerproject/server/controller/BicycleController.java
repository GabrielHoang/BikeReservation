package com.ksr.summerproject.server.controller;

import com.ksr.summerproject.server.exceptions.*;
import com.ksr.summerproject.server.service.BicycleService;
import com.ksr.summerproject.server.service.BicycleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bicycle")
public class BicycleController {

    private final BicycleService bicycleService;

    public BicycleController(BicycleServiceImpl bicycleService) {
        this.bicycleService = bicycleService;
    }

    @PostMapping("/rent")
    public ResponseEntity<String> rentBicycle(@RequestParam("id") int id) {
        try {
            bicycleService.rentBicycle(id);
            return ResponseEntity.status(HttpStatus.OK).body("Succesfully rented bicycle of id: " + id);
        } catch (BicycleNotFoundException | BicycleDeactivatedException | BicycleOccupiedException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }

    @PatchMapping("/return")
    public ResponseEntity<String> returnBicycle(@RequestParam("id") int id) {
        try {
            bicycleService.returnBicycle(id);
            return ResponseEntity.status(HttpStatus.OK).body("Bicycle of id: " + id + " successfully returned.");
        } catch (BicycleNotFoundException | BicycleNotOccupiedException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }

    @GetMapping("/getnearby")
    public ResponseEntity getNearbyBicycles(@RequestParam("location") int location) {
        try {
            return ResponseEntity.ok(bicycleService.getBicyclesInArea(location));
        } catch (BicyclesNotFoundInLocationException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }

}
