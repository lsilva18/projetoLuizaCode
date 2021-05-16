package com.wishlist.wishlistluizacode.repositories;

import com.wishlist.wishlistluizacode.entities.Client;
import com.wishlist.wishlistluizacode.entities.WishList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WishListRepository extends CrudRepository<WishList, Long> {
    List<WishList> findByClient(Client client);
}
