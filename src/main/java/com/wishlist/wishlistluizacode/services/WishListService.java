package com.wishlist.wishlistluizacode.services;

import com.wishlist.wishlistluizacode.entities.Client;
import com.wishlist.wishlistluizacode.entities.Product;
import com.wishlist.wishlistluizacode.entities.WishList;
import com.wishlist.wishlistluizacode.repositories.ProductRepository;
import com.wishlist.wishlistluizacode.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    // Add wishlist
    public WishList save(WishList wishList){
        return wishListRepository.save(wishList);
    }

    // Find all
    public List<WishList> findAll(){
        Iterable<WishList> iterable = wishListRepository.findAll();
        List<WishList> wishLists = new ArrayList<>();
        iterable.forEach(wishLists::add);
        return wishLists;
    }

    // Find by name
//    public List<Client> findById(String name){
//        return wishListRepository.findByName(name);
//    }

    // Delete
    public void delete(WishList wishList){
        wishListRepository.delete(wishList);
    }

    // Save product

    // Delete product

    // Find all products of the list

    // Find product of the list by name

}
