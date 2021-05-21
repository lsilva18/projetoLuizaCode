package com.wishlist.wishlistluizacode.config;

import com.wishlist.wishlistluizacode.entities.Client;
import com.wishlist.wishlistluizacode.entities.Product;
import com.wishlist.wishlistluizacode.services.ClientService;
import com.wishlist.wishlistluizacode.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (productService.findByName("Aspirador Robô").isEmpty())
            productService.save(new Product("Aspirador Robô", "Aspirador Robô"));

        if (productService.findByName("Lava-Louça").isEmpty())
            productService.save(new Product("Lava-Louça", "Lava-Louça"));

        if (clientService.findByName("Carla").isEmpty())
            clientService.save(new Client("Carla", "1234561"));

        if (clientService.findByName("Lara").isEmpty())
            clientService.save(new Client("Lara", "1234562"));

        if (clientService.findByName("Lívia").isEmpty())
            clientService.save(new Client("Lívia", "1234563"));

        if (clientService.findByName("Patrícia").isEmpty())
            clientService.save(new Client("Patrícia", "1234564"));
    }
}