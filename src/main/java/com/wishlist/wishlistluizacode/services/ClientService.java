package com.wishlist.wishlistluizacode.services;


import com.wishlist.wishlistluizacode.entities.Client;
import com.wishlist.wishlistluizacode.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        Iterable<Client> iterable = clientRepository.findAll();
        List<Client> clients = new ArrayList<>();
        iterable.forEach(clients::add);
        return clients;
    }

    public List<Client> findByName(String name) {
        return clientRepository.findByName(name);
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
