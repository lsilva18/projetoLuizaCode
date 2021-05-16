package com.wishlist.wishlistluizacode.controllers;

import com.wishlist.wishlistluizacode.dto.ProductWishListDTO;
import com.wishlist.wishlistluizacode.entities.Client;
import com.wishlist.wishlistluizacode.entities.Product;
import com.wishlist.wishlistluizacode.entities.WishList;
import com.wishlist.wishlistluizacode.services.ClientService;
import com.wishlist.wishlistluizacode.services.ProductService;
import com.wishlist.wishlistluizacode.services.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class WishListController {

    private static final int MAXSIZE = 20;

    @Autowired
    private WishListService wishListService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @PostMapping("/wishlist")
    public WishList save(@RequestBody WishList wishList) {
        return wishListService.save(wishList);
    }

    @GetMapping("/wishlist")
    public List<WishList> GetAll() {
        Iterable<WishList> iterable = wishListService.findAll();
        List<WishList> wishLists = new ArrayList<>();
        iterable.forEach(wishLists::add);
        return wishLists;
    }

    @GetMapping("/wishlist/{id}")
    public ResponseEntity<WishList> findById(@PathVariable Long id){
        Optional<WishList> optional = wishListService.findById(id);
        if(optional.isPresent())
            return ResponseEntity.ok().body(optional.get());
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/wishlist/nameClient/{name}")
    public ResponseEntity<List<WishList>> findByClientName(@PathVariable String name){
        return ResponseEntity.ok().body(wishListService.findByClientName(name));
    }

    @GetMapping("/wishlist/idClient/{id}")
    public ResponseEntity<WishList> findByClientId(@PathVariable Long id){
        return ResponseEntity.ok().body(wishListService.findByClientId(id));
    }

    @DeleteMapping("/wishlist/{id}")
    public void delete(@PathVariable long id) {
        wishListService.deleteById(id);
    }

    @PostMapping("/wishlist/product")
    public ResponseEntity addProduct(@Valid @RequestBody ProductWishListDTO dto){
        Optional<Client> optionalClient = clientService.findById(dto.getClientId());
        if (optionalClient.isEmpty())
            return ResponseEntity.notFound().build();

        Optional<Product> optionalProduct = productService.findById(dto.getProductId());
        if(!optionalProduct.isPresent())
            return ResponseEntity.notFound().build();

        WishList wishList = wishListService.findByClientId(dto.getClientId());
        if (wishList == null){
            wishList = new WishList();
            wishList.setClient(optionalClient.get());
        }
        if(!wishList.addProduct(optionalProduct.get()))
            return ResponseEntity.badRequest().body("The product is already in the list");

        if(wishList.getProducts().size() >= MAXSIZE)
            return ResponseEntity.badRequest().body("Your list is already full");

        wishList = wishListService.save(wishList);
        return ResponseEntity.ok().body(wishList);
    }

    @DeleteMapping("/wishlist/product")
    public ResponseEntity removeProduct(@Valid @RequestBody ProductWishListDTO dto){
        Optional<Client> optionalClient = clientService.findById(dto.getClientId());
        if (optionalClient.isEmpty())
            return ResponseEntity.notFound().build();

        WishList wishList = wishListService.findByClientId(dto.getClientId());
        if (wishList == null)
            return ResponseEntity.notFound().build();

        Optional<Product> optionalProduct = productService.findById(dto.getProductId());
        if (!optionalProduct.isPresent())
            return ResponseEntity.notFound().build();

        if(!wishList.removeProduct(optionalProduct.get()))
            return ResponseEntity.badRequest().body("Product does not exists in the wishlist");

        wishList = wishListService.save(wishList);
        return ResponseEntity.ok().body(wishList);

    }
}
