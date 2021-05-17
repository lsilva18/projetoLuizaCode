package com.wishlist.wishlistluizacode.services;

import  com.wishlist.wishlistluizacode.entities.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {

    @Autowired
    ClientService clientServiceTest;

    Client client;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setName("Nome1");
        client.setCpf("12345678912");
    }

    @Test
    void shouldSaveClient() {
        Client save = clientServiceTest.save(client);

        assertEquals(client.getName(),client.getCpf());

    }

    @Test
    void shouldFindAllClients() {
    }

    @Test
    void shouldFindClientByName() {
    }

    @Test
    void shouldFindClientById() {
    }

    @Test
    void shouldDeleteClientById() {
    }
}