package com.wishlist.wishlistluizacode.repositories;

import com.wishlist.wishlistluizacode.entities.Product;
import com.wishlist.wishlistluizacode.entities.WishList;
import org.springframework.data.repository.CrudRepository;

public interface WishListRepository extends CrudRepository<WishList, Long> {

    Product findByName(String name);

}
