package com.wishlist.wishlistluizacode.repositories;

import com.wishlist.wishlistluizacode.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    List<Client> findByName(String name);
    Client save(Client client);

}
