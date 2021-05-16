package com.wishlist.wishlistluizacode.services;

import com.wishlist.wishlistluizacode.entities.Client;
import com.wishlist.wishlistluizacode.entities.WishList;
import com.wishlist.wishlistluizacode.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private ClientService clientService;

    public WishList save(WishList wishList) {
        return wishListRepository.save(wishList);
    }

    public List<WishList> findAll() {
        Iterable<WishList> iterable = wishListRepository.findAll();
        List<WishList> wishLists = new ArrayList<>();
        iterable.forEach(wishLists::add);
        return wishLists;
    }

    public Optional<WishList> findById(Long id) {
        return wishListRepository.findById(id);
    }

    public List<WishList> findByClientName(String name) {
        List<Client> clients = clientService.findByName(name);
        List<WishList> wishLists = new ArrayList<>();
        for (Client client : clients) {
            WishList wishList = findByClientId(client.getId());
            if (wishList != null)
                wishLists.add(wishList);
        }
        return wishLists;
    }

    public WishList findByClientId(Long id) {
        Optional<Client> optionalClient = clientService.findById(id);
        if (optionalClient.isPresent()) {
            List<WishList> wishListList = wishListRepository.findByClient(optionalClient.get());
            if (!wishListList.isEmpty())
                return wishListList.get(0);
        }
        return null;
    }

    public void deleteById(Long id) {
        wishListRepository.deleteById(id);
    }
}
