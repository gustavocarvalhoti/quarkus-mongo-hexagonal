package org.desafio.application;

import org.desafio.application.service.WishlistService;
import org.desafio.model.domain.Wishlist;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static org.jboss.resteasy.resteasy_jaxrs.i18n.LogMessages.LOGGER;

@Path("/wishlist")
public class WishlistController {

    @Inject
    private WishlistService wishlistService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Wishlist> getList() {
        LOGGER.info("Get list:");

        return wishlistService.getAll();
    }

    @GET
    @Path("/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Wishlist> getByCustomer(@PathParam("customerId") String customerId) {
        var message = String.format(
                "Pesquisando por cutomerId: %s",
                customerId
        );
        LOGGER.info(message);

        return wishlistService.GetByCustomerAndProduct(customerId, null);
    }

    @GET
    @Path("/{customerId}/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Wishlist> getByCustomerAndProduct(@PathParam("customerId") String customerId, @PathParam("productId") String productId) {
        var message = String.format(
                "Pesquisando por cutomerId: %s and productId: %s",
                customerId, productId
        );
        LOGGER.info(message);

        return wishlistService.GetByCustomerAndProduct(customerId, productId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addWishlist(Wishlist wishlist) throws Exception {
        var message = String.format(
                "Wishlist adicionado: cutomerId: %s and productId: %s",
                wishlist.getCustomerId(), wishlist.getProductId()
        );
        LOGGER.info(message);

        wishlistService.addWishlist(wishlist);
    }

    @DELETE
    @Path("/{customerId}/{productId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("customerId") String customerId, @PathParam("productId") String productId) {
        var message = String.format(
                "Wishlist deletado: cutomerId: %s and productId: %s",
                customerId, productId
        );
        LOGGER.info(message);

        wishlistService.delete(customerId, productId);
    }

}