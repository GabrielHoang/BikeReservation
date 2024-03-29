package com.ksr.summerproject.server.service;

import com.ksr.summerproject.server.exceptions.ClientAlreadyExistsException;
import com.ksr.summerproject.server.exceptions.ClientNotExistsException;
import com.ksr.summerproject.server.exceptions.MoneyAccountOnDebtException;
import com.ksr.summerproject.server.model.Client;
import com.ksr.summerproject.server.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional(timeout = 5)
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void addNewClient(Client client) throws ClientAlreadyExistsException {
        if (clientRepository.findClientByEmail(client.getEmail()) != null) {
            throw new ClientAlreadyExistsException("Email already in use.");
        }
    }

    @Override
    public Client getClient(int id) throws ClientNotExistsException{
        Optional<Client> foundClient = clientRepository.findById(id);
        if (!foundClient.isPresent()) {
            throw new ClientNotExistsException("No client of such id.");
        }

        return clientRepository.getOne(id);
    }

    @Override
    public void addMoney(int clientId, BigDecimal money) throws ClientNotExistsException {
        Client client = getClient(clientId);
        BigDecimal clientsMoney = client.getMoney().add(money);
        client.setMoney(clientsMoney);
    }

    @Override
    public void substractMoney(int clientId, BigDecimal money) throws ClientNotExistsException, MoneyAccountOnDebtException {
        Client client = getClient(clientId);
        BigDecimal clientsMoney;

        clientsMoney = client.getMoney().subtract(money);
        client.setMoney(clientsMoney);

        if (clientsMoney.doubleValue() < 0) {
            throw new MoneyAccountOnDebtException("Account balance in debt!");
        }
    }

    @Override
    public BigDecimal getCurrentBalance(int clientId) throws ClientNotExistsException {
        Client client = getClient(clientId);
        return client.getMoney();
    }
}
