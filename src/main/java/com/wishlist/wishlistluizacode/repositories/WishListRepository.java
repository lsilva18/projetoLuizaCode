package com.wishlist.wishlistluizacode.repositories;

import com.wishlist.wishlistluizacode.entities.Product;
import com.wishlist.wishlistluizacode.entities.WishList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WishListRepository extends CrudRepository<WishList, Long> {

    Product findByName(String name);

}
