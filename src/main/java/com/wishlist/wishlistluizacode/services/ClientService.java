package com.wishlist.wishlistluizacode.services;


import com.wishlist.wishlistluizacode.entities.Client;
import com.wishlist.wishlistluizacode.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Add client
    public Client save(Client client){
        return clientRepository.save(client);
    }

    // Find all
    public List<Client> findAll(){
        Iterable<Client> iterable = clientRepository.findAll();
        List<Client> clients = new ArrayList<>();
        iterable.forEach(clients::add);
        return clients;
    }

    // Find by name
    public List<Client> findByName(String name){
        return clientRepository.findByName(name);
    }

    // Delete
    public void delete(Client client){
        clientRepository.delete(client);
    }
}
