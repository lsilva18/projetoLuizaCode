package com.wishlist.wishlistluizacode.controllers;

import com.wishlist.wishlistluizacode.entities.Product;
import com.wishlist.wishlistluizacode.services.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value= "Add new product")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "A new product was added", response = Response.class),
            @ApiResponse(code=400, message = "Bad request", response = Response.class)
    })
    @PostMapping("/product")
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @ApiOperation(value= "Find all products")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "Those are the products added", response = Response.class),
            @ApiResponse(code=400, message = "Bad request", response = Response.class)
    })
    @GetMapping("/product")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @ApiOperation(value= "Find a product by its name")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "This is the product you've requested", response = Response.class),
            @ApiResponse(code=400, message = "Bad request", response = Response.class)
    })
    @GetMapping("/product/name/{name}")
    public List<Product> findByName(@PathVariable(value = "name") String name) {
        return productService.findByName(name);
    }

    @ApiOperation(value= "Find a product by its ID")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "This is the product you've requested", response = Response.class),
            @ApiResponse(code=400, message = "Bad request", response = Response.class)
    })
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Optional<Product> optional = productService.findById(id);
        if (optional.isPresent())
            return ResponseEntity.ok().body(optional.get());
        else
            return ResponseEntity.notFound().build();
    }

    @ApiOperation(value= "Delete a product by ID")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "The product was deleted", response = Response.class),
            @ApiResponse(code=400, message = "Bad request", response = Response.class)
    })
    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

}

