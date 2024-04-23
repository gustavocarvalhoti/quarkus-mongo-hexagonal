package org.desafio.application.service;


import org.desafio.model.domain.Wishlist;

import java.util.List;

public interface WishlistService {

    List<Wishlist> getAll();

    List<Wishlist> GetByCustomerAndProduct(String customerId, String productId);

    void addWishlist(Wishlist wishlist) throws Exception;

    void delete(String customerId, String productId);

}
