package com.wishlist.wishlistluizacode.services;

import com.wishlist.wishlistluizacode.entities.Client;
import com.wishlist.wishlistluizacode.entities.Product;
import com.wishlist.wishlistluizacode.entities.WishList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
        }
        return productList;
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
    void findAllWishlists() {
        List<WishList> before = wishListService.findAll();
        WishList wishList = createNewWishList(
                createOrGetClient("createOrGetClient", "123456"),
                createOrGetProduct("createOrGetProduct", "createOrGetProduct"));
        List<WishList> after = wishListService.findAll();
        assertTrue(before.size() == after.size() - 1);
        assertTrue(after.contains(wishList));
    }

    @Test
    void findWishlistById() {
        WishList wishList = createNewWishList(
                createOrGetClient("createOrGetClient", "123456"),
                createOrGetProduct("createOrGetProduct", "createOrGetProduct"));
        assertNotNull(wishList.getId());
        Optional<WishList> wishListOptional = wishListService.findById(wishList.getId());
        assertTrue(wishListOptional.isPresent());
        assertEquals(wishList.getId(), wishListOptional.get().getId());
    }

    @Test
    void findByClientName() {
        Client client = createOrGetClient("createOrGetClient", "123456");
        WishList wishList = createNewWishList(
                client,
                createOrGetProduct("createOrGetProduct", "createOrGetProduct"));
        assertNotNull(wishList.getId());

        List<WishList> wishLists = wishListService.findByClientName(client.getName());

        assertEquals(wishLists.size(), 1);
        assertEquals(wishLists.get(0).getClient().getId(), client.getId());
    }

    @Test
    void findByClientId() {
        Client client = createOrGetClient("createOrGetClient", "123456");
        WishList wishList = createNewWishList(
                client,
                createOrGetProduct("createOrGetProduct", "createOrGetProduct"));
        assertNotNull(wishList.getId());

        wishList = wishListService.findByClientId(client.getId());
        assertEquals(wishList.getClient().getId(), client.getId());
        // client not found
        assertNull(wishListService.findByClientId(-1L));
    }

    @Test
    void deleteById() {
        WishList wishList = createNewWishList(
                createOrGetClient("createOrGetClient", "123456"),
                createOrGetProduct("createOrGetProduct", "createOrGetProduct"));
        assertNotNull(wishList.getId());
        Optional<WishList> wishListOptional = wishListService.findById(wishList.getId());
        assertTrue(wishListOptional.isPresent());
        assertEquals(wishList.getId(), wishListOptional.get().getId());
        wishListService.deleteById(wishList.getId());
        assertTrue(wishListService.findById(wishList.getId()).isEmpty());
    }
}