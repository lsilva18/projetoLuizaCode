package com.wishlist.wishlistluizacode.controllers;

import com.wishlist.wishlistluizacode.entities.WishList;
import com.wishlist.wishlistluizacode.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/wishlist")
public class WishListController {

    @Autowired
    private WishListRepository wishListRepository;

    //post wishlist
    @PostMapping("/wishlistPost")
    public WishList save(@RequestBody WishList wishList) {
        return wishListRepository.save(wishList);
    }

    //get all wishlist
    @GetMapping("/wishlistGetAll")
    public ResponseEntity<List<WishList>> GetAll() {
        return (ResponseEntity<List<WishList>>) wishListRepository.findAll();
    }

    //delete wishlist
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        wishListRepository.deleteById(id);
    }
}
