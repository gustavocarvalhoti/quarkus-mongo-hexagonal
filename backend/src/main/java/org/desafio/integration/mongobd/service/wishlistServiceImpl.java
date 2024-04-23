package org.desafio.integration.mongobd.service;

import org.desafio.application.service.WishlistService;
import org.desafio.integration.mongobd.mapper.WishlistMapper;
import org.desafio.integration.mongobd.repository.WishlistRepository;
import org.desafio.model.domain.Wishlist;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

@RequestScoped
public class wishlistServiceImpl implements WishlistService {

    @Inject
    private WishlistRepository wishlistRepository;

    private final WishlistMapper wishlistMapper = new WishlistMapper();

    @Override
    public List<Wishlist> getAll() {
        var tasks = wishlistRepository.getAll();
        return wishlistMapper.toWishlist(tasks);
    }

    @Override
    public List<Wishlist> GetByCustomerAndProduct(String customerId, String productId) {
        var tasks = wishlistRepository.getByCustomerAndProduct(customerId, productId);
        return wishlistMapper.toWishlist(tasks);
    }

    @Override
    public void addWishlist(Wishlist wishlist) throws Exception {
        // Verificando se já existe antes de salvar
        var result = wishlistRepository.getByCustomerAndProduct(wishlist.getCustomerId(), wishlist.getProductId());
        if (Objects.isNull(result) || result.isEmpty()) {
            // Verificando se o customer possui menos de 20 itens na Wishlist
            result = wishlistRepository.getByCustomerAndProduct(wishlist.getCustomerId(), null);
            if (Objects.isNull(result) || result.size() < 20) {
                wishlistRepository.addWishlist(wishlist);
            } else {
                throw new Exception("O limite máximo para a lista de desejos é 20.");
            }
        }
    }

    @Override
    public void delete(String customerId, String productId) {
        wishlistRepository.delete(customerId, productId);
    }
}
