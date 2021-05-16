package com.wishlist.wishlistluizacode.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Client client;

    @ManyToMany
    private List<Product> products = new ArrayList<>();

    public WishList() {
    }

    public WishList(Long id, Client client, List<Product> products) {
        this.id = id;
        this.client = client;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
            return true;
        }
        return false;
    }

    public boolean removeProduct(Product product) {
        if (products.contains(product)) {
            products.remove(product);
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WishList wishList = (WishList) o;

        return id != null ? id.equals(wishList.id) : wishList.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
