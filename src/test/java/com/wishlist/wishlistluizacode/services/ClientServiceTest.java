package com.wishlist.wishlistluizacode.services;

import com.wishlist.wishlistluizacode.entities.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    private Client createNewClient(String name, String cpf) {
        Client client = new Client();
        client.setName(name);
        client.setCpf(cpf);
        return clientService.save(client);
    }

    @Test
    void shouldSaveClient() {
        Client client = createNewClient("shouldSaveClient", "1234");
        assertNotNull(client);
        assertNotNull(client.getId());
    }

    @Test
    void shouldFindAllClients() {
        List<Client> before = clientService.findAll();
        Client client = createNewClient("shouldFindAllClients", "1234");
        List<Client> after = clientService.findAll();
        assertTrue(before.size() < after.size());
        assertTrue(after.contains(client));
    }

    @Test
    void shouldFindClientByName() {
        Client client = createNewClient("shouldFindClientByName", "1234");
        List<Client> clientsByName = clientService.findByName("shouldFindClientByName");
        assertTrue(clientsByName.contains(client));
    }

    @Test
    void shouldFindClientById() {
        Client client = createNewClient("shouldFindClientById", "1234");
        assertNotNull(client.getId());
        Optional<Client> clientOptional = clientService.findById(client.getId());
        assertTrue(clientOptional.isPresent());
        assertEquals(client.getId(), clientOptional.get().getId());
    }

    @Test
    void shouldDeleteClientById() {
        Client client = createNewClient("shouldDeleteClientById", "1234");
        assertNotNull(client.getId());
        Optional<Client> clientOptional = clientService.findById(client.getId());
        assertTrue(clientOptional.isPresent());
        assertEquals(client.getId(), clientOptional.get().getId());
        clientService.deleteById(client.getId());
        assertTrue(clientService.findById(client.getId()).isEmpty());
    }
}