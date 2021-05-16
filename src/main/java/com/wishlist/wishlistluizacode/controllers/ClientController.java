package com.wishlist.wishlistluizacode.controllers;

public class ClientController {

    // Add client
    @Autowired
    private ClientService clientService;

    @PostMapping("/client")
    public Client saveClient(@RequestBody Client client){
        return clientService.save(client);
    }

    // Find all



    // Find by name
    @GetMapping("/client/{name}")
    public Client getClient(@PathVariable(value = "name") String name){
        return clienteService.get(name);
    }

    // Delete

    @RequestMapping( value=delete/{id}, method = RequestMethod.POST)
    public void delete(@RequestParam("id")Long id) {

        Convidado client = clientRepository.findOne(id);

        clientRepository.delete(client);

    }
    }












}
