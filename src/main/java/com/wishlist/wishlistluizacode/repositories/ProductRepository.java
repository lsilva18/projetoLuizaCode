package com.wishlist.wishlistluizacode.repositories;

import com.wishlist.wishlistluizacode.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findByName(String name);

}
