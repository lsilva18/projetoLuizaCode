package com.wishlist.wishlistluizacode.repositories;

import com.wishlist.wishlistluizacode.entities.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Client findByName(String name);

}
