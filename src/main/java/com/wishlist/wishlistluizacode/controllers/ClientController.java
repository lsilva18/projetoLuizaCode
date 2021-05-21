package com.wishlist.wishlistluizacode.controllers;

import com.wishlist.wishlistluizacode.entities.Client;
import com.wishlist.wishlistluizacode.services.ClientService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @ApiOperation(value= "Add new client")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "A new client was added", response = Response.class),
            @ApiResponse(code=400, message = "Bad request", response = Response.class)
    })
    @PostMapping("/client")
    public Client saveClient(@RequestBody Client client) {
        return clientService.save(client);
    }

    @ApiOperation(value= "Get all clients")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "Those are the clients!", response = Response.class),
            @ApiResponse(code=400, message = "Bad request", response = Response.class)
    })
    @GetMapping("/client")
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @ApiOperation(value= "Get a client by its name")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "This is the client you've requested!", response = Response.class),
            @ApiResponse(code=400, message = "Bad request", response = Response.class)
    })
    @GetMapping("/client/name/{name}")
    public List<Client> getClient(@PathVariable(value = "name") String name) {
        return clientService.findByName(name);
    }

    @ApiOperation(value= "Find a client by ID")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "This is the Client requested", response = Response.class),
            @ApiResponse(code=400, message = "Bad request", response = Response.class)
    })
    @GetMapping("/client/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        Optional<Client> optional = clientService.findById(id);
        if (optional.isPresent())
            return ResponseEntity.ok().body(optional.get());
        else
            return ResponseEntity.notFound().build();
    }

    @ApiOperation(value= "Delete a client by its ID")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "The client was deleted", response = Response.class),
            @ApiResponse(code=400, message = "Bad request", response = Response.class)
    })
    @DeleteMapping("/client/{id}")
    public void deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
    }
}
