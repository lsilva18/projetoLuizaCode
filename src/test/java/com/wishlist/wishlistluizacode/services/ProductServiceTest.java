package com.wishlist.wishlistluizacode.services;

import com.wishlist.wishlistluizacode.entities.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    private Product createNewProduct(String name, String description) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        return productService.save(product);
    }

    @Test
    void shouldSaveProduct() {
        Product product = createNewProduct("shouldSaveProduct", "shouldSaveProduct");
        assertNotNull(product);
        assertNotNull(product.getId());
    }

    @Test
    void shouldFindAllProducts() {
        List<Product> before = productService.findAll();
        Product product = createNewProduct("shouldFindAllProducts", "shouldFindAllProducts");
        List<Product> after = productService.findAll();
        assertTrue(before.size() == after.size() - 1);
        assertTrue(after.contains(product));
    }

    @Test
    void shouldFindProductByName() {
        Product product = createNewProduct("shouldFindProductByName", "shouldFindProductByName");
        List<Product> productsByName = productService.findByName("shouldFindProductByName");
        assertTrue(productsByName.contains(product));
    }

    @Test
    void shouldFindProductById() {
        Product product = createNewProduct("shouldFindProductById", "shouldFindProductById");
        assertNotNull(product.getId());
        Optional<Product> productOptional = productService.findById(product.getId());
        assertTrue(productOptional.isPresent());
        assertEquals(product.getId(), productOptional.get().getId());
    }

    @Test
    void shouldDeleteProductById() {
        Product product = createNewProduct("shouldDeleteProductById", "shouldDeleteProductById");
        assertNotNull(product.getId());
        Optional<Product> productOptional = productService.findById(product.getId());
        assertTrue(productOptional.isPresent());
        assertEquals(product.getId(), productOptional.get().getId());
        productService.deleteById(product.getId());
        assertTrue(productService.findById(product.getId()).isEmpty());
    }
}