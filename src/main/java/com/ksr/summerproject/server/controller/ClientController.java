package com.ksr.summerproject.server.controller;

import com.ksr.summerproject.server.exceptions.ClientAlreadyExistsException;
import com.ksr.summerproject.server.exceptions.ClientNotExistsException;
import com.ksr.summerproject.server.exceptions.MoneyAccountOnDebtException;
import com.ksr.summerproject.server.model.Client;
import com.ksr.summerproject.server.service.ClientService;
import com.ksr.summerproject.server.service.ClientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/add")
    ResponseEntity<String> addClient(@RequestBody Client client) {
        try {
            clientService.addNewClient(client);
            return ResponseEntity.ok("Client successfully added.");
        } catch (ClientAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    ResponseEntity getClient(@PathVariable("id") int id) {
        try {
            Client foundClient = clientService.getClient(id);
            return ResponseEntity.ok(foundClient);
        } catch (ClientNotExistsException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/substractfunds/{amount}")
    ResponseEntity substractFunds(@PathVariable("id") int clientId,
                            @PathVariable("amount")BigDecimal money) {
        try {
            clientService.substractMoney(clientId, money);
            return ResponseEntity.ok("Amount of " + money + " has been succesfully substracted from the account.");

        } catch (ClientNotExistsException | MoneyAccountOnDebtException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/addfunds/{amount}")
    ResponseEntity addFunds(@PathVariable("id") int clientId,
                            @PathVariable("amount")BigDecimal money) {
        try {
            clientService.addMoney(clientId, money);
            return ResponseEntity.ok("Amount of " + money + " has been successfully added to the account.");

        } catch (ClientNotExistsException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }

    @GetMapping("/{id}/getfunds")
    ResponseEntity getFunds(@PathVariable("id") int clientId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body("Current balance: " + clientService.getCurrentBalance(clientId));
        } catch (ClientNotExistsException e) {
            return ResponseEntity.status(HttpStatus.OK).body(e.getMessage());
        }
    }
}
