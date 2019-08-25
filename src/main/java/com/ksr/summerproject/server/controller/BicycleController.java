package com.ksr.summerproject.server.controller;

import com.ksr.summerproject.server.exceptions.BicycleDeactivatedException;
import com.ksr.summerproject.server.exceptions.BicycleNotFoundException;
import com.ksr.summerproject.server.exceptions.BicycleOccupiedException;
import com.ksr.summerproject.server.service.BicycleService;
import com.ksr.summerproject.server.service.BicycleServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bicycle")
public class BicycleController {

    private final BicycleService bicycleService;

    public BicycleController(BicycleServiceImpl bicycleService) {
        this.bicycleService = bicycleService;
    }

    @PostMapping("/rent/{id}")
    public ResponseEntity<String> rentBicycle(@PathVariable("id") int id) {
        try {
            bicycleService.rentBicycle(id);
            return ResponseEntity.status(HttpStatus.OK).body("Succesfully rented bicycle of id: " + id);
        } catch (BicycleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        } catch (BicycleDeactivatedException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        } catch (BicycleOccupiedException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }


}
