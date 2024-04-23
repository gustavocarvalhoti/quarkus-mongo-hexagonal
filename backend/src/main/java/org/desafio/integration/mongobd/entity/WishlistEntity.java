package org.desafio.integration.mongobd.entity;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.math.BigDecimal;

@MongoEntity(collection = "wishlist")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishlistEntity extends PanacheMongoEntity {

    @BsonProperty("_id")
    private ObjectId id;
    private String customerId;
    private String productId;
    private String productName;
    private BigDecimal productValue;
    private Integer quantity;

}