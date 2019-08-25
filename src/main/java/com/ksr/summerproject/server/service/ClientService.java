package com.ksr.summerproject.server.service;

import com.ksr.summerproject.server.exceptions.ClientAlreadyExistsException;
import com.ksr.summerproject.server.exceptions.ClientNotExistsException;
import com.ksr.summerproject.server.exceptions.MoneyAccountOnDebtException;
import com.ksr.summerproject.server.model.Client;

import java.math.BigDecimal;

public interface ClientService {

    void addNewClient(Client client) throws ClientAlreadyExistsException;

    Client getClient(int id) throws ClientNotExistsException;

    void addMoney(int id, BigDecimal money) throws ClientNotExistsException;

    void substractMoney(int clientId, BigDecimal money) throws ClientNotExistsException, MoneyAccountOnDebtException;

    BigDecimal getCurrentBalance(int clientId) throws ClientNotExistsException;
}
