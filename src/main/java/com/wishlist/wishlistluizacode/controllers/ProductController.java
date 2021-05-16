package com.wishlist.wishlistluizacode.controllers;
import com.wishlist.wishlistluizacode.entities.Product;
import com.wishlist.wishlistluizacode.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/product")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/product/{name}")
    public List<Product> findByName(@PathVariable(value = "name") String name) {
        return productService.findByName(name);
    }

    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

}

