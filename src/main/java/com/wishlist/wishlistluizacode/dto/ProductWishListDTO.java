package com.wishlist.wishlistluizacode.dto;

import javax.validation.constraints.NotNull;

public class ProductWishListDTO {

    @NotNull
    private Long clientId;
    @NotNull
    private Long productId;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
