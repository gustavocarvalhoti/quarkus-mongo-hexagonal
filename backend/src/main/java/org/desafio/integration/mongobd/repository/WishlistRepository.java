package org.desafio.integration.mongobd.repository;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import org.desafio.integration.mongobd.entity.WishlistEntity;
import org.desafio.integration.mongobd.mapper.WishlistMapper;
import org.desafio.model.domain.Wishlist;

import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequestScoped
public class WishlistRepository {

    private final WishlistMapper wishlistMapper = new WishlistMapper();

    public void addWishlist(Wishlist wishlist) {
        updateWishlist(wishlistMapper.toWishlistEntity(wishlist));
    }

    public void updateWishlist(WishlistEntity wishlistEntity) {
        wishlistEntity.persistOrUpdate();
    }

    public void delete(String customerId, String productId) {
        var result = getByCustomerAndProduct(customerId, productId);
        if (Objects.nonNull(result)) {
            result.forEach(PanacheMongoEntityBase::delete);
        }
    }

    public List<WishlistEntity> getAll() {
        return WishlistEntity.findAll().list();
    }

    public List<WishlistEntity> getByCustomerAndProduct(String customerId, String productId) {

        List<WishlistEntity> result = WishlistEntity.find("customerId", customerId).list();
        if (Objects.nonNull(productId) && !productId.isBlank()) {
            return result.stream()
                    .filter(r -> r.getProductId().equalsIgnoreCase(productId))
                    .collect(Collectors.toList());
        }

        return result;
    }

}