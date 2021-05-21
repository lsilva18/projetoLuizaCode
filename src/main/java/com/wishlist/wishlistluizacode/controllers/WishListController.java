package com.wishlist.wishlistluizacode.controllers;

import com.wishlist.wishlistluizacode.dto.ProductWishListDTO;
import com.wishlist.wishlistluizacode.entities.Client;
import com.wishlist.wishlistluizacode.entities.Product;
import com.wishlist.wishlistluizacode.entities.WishList;
import com.wishlist.wishlistluizacode.services.ClientService;
import com.wishlist.wishlistluizacode.services.ProductService;
import com.wishlist.wishlistluizacode.services.WishListService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
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

    @ApiOperation(value= "Add new wishlist")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "A new wishlist was added", response = Response.class),
            @ApiResponse(code=400, message = "Bad request", response = Response.class)
    })
    @PostMapping("/wishlist")
    public WishList save(@RequestBody WishList wishList) {
        return wishListService.save(wishList);
    }

    @ApiOperation(value= "Get all wishlists product")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "Those are all the wishlists available", response = Response.class),
            @ApiResponse(code=400, message = "Bad request", response = Response.class)
    })
    @GetMapping("/wishlist")
    public List<WishList> GetAll() {
        Iterable<WishList> iterable = wishListService.findAll();
        List<WishList> wishLists = new ArrayList<>();
        iterable.forEach(wishLists::add);
        return wishLists;
    }

    @ApiOperation(value= "Find a wishlist by ID")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "This is the wishlist requested", response = Response.class),
            @ApiResponse(code=400, message = "Bad request", response = Response.class)
    })
    @GetMapping("/wishlist/{id}")
    public ResponseEntity<WishList> findById(@PathVariable Long id) {
        Optional<WishList> optional = wishListService.findById(id);
        if (optional.isPresent())
            return ResponseEntity.ok().body(optional.get());
        else
            return ResponseEntity.notFound().build();
    }

    @ApiOperation(value= "Find a wishlist by Client name")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "This is the wishlist requested thru Client Name", response = Response.class),
            @ApiResponse(code=400, message = "Bad request", response = Response.class)
    })
    @GetMapping("/wishlist/client/name/{name}")
    public ResponseEntity<List<WishList>> findByClientName(@PathVariable String name) {
        List<WishList> wishLists = wishListService.findByClientName(name);
        if (CollectionUtils.isEmpty(wishLists))
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(wishLists);
    }

    @ApiOperation(value= "Find a wishlist by Client ID")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "This is the wishlist requested thru Client ID", response = Response.class),
            @ApiResponse(code=400, message = "Bad request", response = Response.class)
    })
    @GetMapping("/wishlist/client/id/{id}")
    public ResponseEntity<WishList> findByClientId(@PathVariable Long id) {
        WishList wishList = wishListService.findByClientId(id);
        if (wishList == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(wishList);
    }

    @ApiOperation(value= "Delete a wishlist by its ID")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "The wishlist was deleted", response = Response.class),
            @ApiResponse(code=400, message = "Bad request", response = Response.class)
    })
    @DeleteMapping("/wishlist/{id}")
    public void delete(@PathVariable long id) {
        wishListService.deleteById(id);
    }

    @ApiOperation(value= "Check if a Client has a product at its wishlist")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "There is this product at this client wishlist", response = Response.class),
            @ApiResponse(code=400, message = "Bad request", response = Response.class),
            @ApiResponse(code= 404, message = "There is no product with this id at this Client wishlist", response = Response.class)
    })
    @GetMapping("/wishlist/client/{clientId}/product/{productId}")
    public ResponseEntity<Boolean> checkProductExistsByClientAndProduct(@PathVariable Long clientId, @PathVariable Long productId){
        WishList wishList = wishListService.findByClientId(clientId);
        if (wishList == null)
            return ResponseEntity.notFound().build();

        Optional<Product> optionalProduct = productService.findById(productId);
        if (!optionalProduct.isPresent())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(wishList.hasProduct(optionalProduct.get()));
    }

    @ApiOperation(value= "Add a product at Clients wishlist")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "The product was successfully added", response = Response.class),
            @ApiResponse(code=400, message = "The product is already at the wishlist or your wishlist is already full", response = Response.class),
    })
    @PostMapping("/wishlist/product")
    public ResponseEntity addProduct(@Valid @RequestBody ProductWishListDTO dto) {
        Optional<Client> optionalClient = clientService.findById(dto.getClientId());
        if (optionalClient.isEmpty())
            return ResponseEntity.notFound().build();

        Optional<Product> optionalProduct = productService.findById(dto.getProductId());
        if (!optionalProduct.isPresent())
            return ResponseEntity.notFound().build();

        WishList wishList = wishListService.findByClientId(dto.getClientId());
        if (wishList == null) {
            wishList = new WishList();
            wishList.setClient(optionalClient.get());
        }
        if (!wishList.addProduct(optionalProduct.get()))
            return ResponseEntity.badRequest().body("The product is already in the list");

        if (wishList.getProducts().size() >= MAXSIZE)
            return ResponseEntity.badRequest().body("Your list is already full");

        wishList = wishListService.save(wishList);
        return ResponseEntity.ok().body(wishList);
    }

    @ApiOperation(value= "Delete a product of a wishlist")
    @ApiResponses(value= {
            @ApiResponse(code=200, message = "The product was successfully deleted", response = Response.class),
            @ApiResponse(code=400, message = "The product does not exist at this wishlist", response = Response.class),
    })
    @DeleteMapping("/wishlist/product")
    public ResponseEntity removeProduct(@Valid @RequestBody ProductWishListDTO dto) {
        Optional<Client> optionalClient = clientService.findById(dto.getClientId());
        if (optionalClient.isEmpty())
            return ResponseEntity.notFound().build();

        WishList wishList = wishListService.findByClientId(dto.getClientId());
        if (wishList == null)
            return ResponseEntity.notFound().build();

        Optional<Product> optionalProduct = productService.findById(dto.getProductId());
        if (!optionalProduct.isPresent())
            return ResponseEntity.notFound().build();

        if (!wishList.removeProduct(optionalProduct.get()))
            return ResponseEntity.badRequest().body("Product does not exists in the wishlist");

        wishList = wishListService.save(wishList);
        return ResponseEntity.ok().body(wishList);

    }
}
