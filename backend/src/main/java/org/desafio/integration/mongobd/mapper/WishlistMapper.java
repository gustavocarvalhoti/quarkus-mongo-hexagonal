package org.desafio.integration.mongobd.mapper;

import org.desafio.integration.mongobd.entity.WishlistEntity;
import org.desafio.model.domain.Wishlist;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WishlistMapper {

    public List<Wishlist> toWishlist(List<WishlistEntity> entities) {
        if (Objects.isNull(entities) || entities.isEmpty()) {
            return null;
        }

        return entities.stream()
                .map(taskEnt ->
                        Wishlist.builder()
                                .id(taskEnt.getId().toString())
                                .customerId(taskEnt.getCustomerId())
                                .productId(taskEnt.getProductId())
                                .productName(taskEnt.getProductName())
                                .productValue(taskEnt.getProductValue())
                                .quantity(taskEnt.getQuantity())
                                .build()
                )
                .collect(Collectors.toList());

    }

    public WishlistEntity toWishlistEntity(Wishlist wishlist) {
        if (Objects.isNull(wishlist)) {
            return null;
        }

        return WishlistEntity.builder()
                .customerId(wishlist.getCustomerId())
                .productId(wishlist.getProductId())
                .productName(wishlist.getProductName())
                .productValue(wishlist.getProductValue())
                .quantity(wishlist.getQuantity())
                .build();
    }

}
