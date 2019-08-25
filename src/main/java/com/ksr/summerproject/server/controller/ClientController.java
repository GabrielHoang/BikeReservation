package com.ksr.summerproject.server.controller;

import com.ksr.summerproject.server.exceptions.ClientAlreadyExistsException;
import com.ksr.summerproject.server.exceptions.ClientNotExistsException;
import com.ksr.summerproject.server.exceptions.MoneyAccountOnDebtException;
import com.ksr.summerproject.server.model.Client;
import com.ksr.summerproject.server.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/add")
    ResponseEntity<String> addClient(@RequestParam("client") Client client) {
        try {
            clientService.addNewClient(client);
            return ResponseEntity.ok("Client successfully added.");
        } catch (ClientAlreadyExistsException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    ResponseEntity getClient(@PathVariable("id") int id) {
        try {
            Client foundClient = clientService.getClient(id);
            return ResponseEntity.ok(foundClient);
        } catch (ClientNotExistsException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PatchMapping("/{id}/substractfunds/{amount}")
    ResponseEntity substractFunds(@PathVariable("id") int clientId,
                            @PathVariable("amount")BigDecimal money) {
        try {
            clientService.substractMoney(clientId, money);
            return ResponseEntity.ok("Amount of " + money + " has been succesfully substracted from the account.");

        } catch (ClientNotExistsException e) {
            return ResponseEntity.ok(e.getMessage());
        } catch (MoneyAccountOnDebtException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PatchMapping("/{id}/addfunds/{amount}")
    ResponseEntity addFunds(@PathVariable("id") int clientId,
                            @PathVariable("amount")BigDecimal money) {
        try {
            clientService.addMoney(clientId, money);
            return ResponseEntity.ok("Amount of " + money + "has been successfully added to the account.");

        } catch (ClientNotExistsException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }
}
