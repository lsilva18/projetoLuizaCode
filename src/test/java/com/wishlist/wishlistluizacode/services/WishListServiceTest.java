package com.wishlist.wishlistluizacode.services;

import com.wishlist.wishlistluizacode.entities.Client;
import com.wishlist.wishlistluizacode.entities.Product;
import com.wishlist.wishlistluizacode.entities.WishList;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
class WishListServiceTest {

    @Autowired
    private WishListService wishListService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    private WishList createNewWishList(Client client, List<Product> products) {
        WishList wishList = new WishList();
        wishList.setClient(client);
        wishList.setProducts(products);
        return wishListService.save(wishList);
    }

    private Client createOrGetClient(String name, String cpf) {
        List<Client> clientList = clientService.findByName(name);
        if (clientList.isEmpty()) {
            Client client = new Client(null, name, cpf);
            return clientService.save(client);
        } else
            return clientList.get(0);
    }

    private List<Product> createOrGetProduct(String name, String description) {
        List<Product> productList = productService.findByName(name);
        if (productList.isEmpty()) {
            Product product = new Product(name, description);
            productService.save(product);
        } return productList;
    }

    @Test
    void shouldSaveWishList() {
        WishList wishList = createNewWishList(
                createOrGetClient("createOrGetClient", "123456"),
                createOrGetProduct("createOrGetProduct", "createOrGetProduct"));
        assertNotNull(wishList);
        assertNotNull(wishList.getId());
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByClientName() {
    }

    @Test
    void findByClientId() {
    }

    @Test
    void deleteById() {
    }
}