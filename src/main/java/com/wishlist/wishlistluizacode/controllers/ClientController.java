package com.wishlist.wishlistluizacode.controllers;

import com.wishlist.wishlistluizacode.entities.Client;
import com.wishlist.wishlistluizacode.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @PostMapping("/client")
    public Client saveClient(@RequestBody Client client) {
        return clientService.save(client);
    }

    @GetMapping("/client")
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/client/{name}")
    public List<Client> getClient(@PathVariable(value = "name") String name) {
        return clientService.findByName(name);
    }

    @DeleteMapping("/client/{id}")
    public void deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
    }
}
