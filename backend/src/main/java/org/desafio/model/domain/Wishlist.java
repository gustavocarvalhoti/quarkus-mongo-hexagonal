package org.desafio.model.domain;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wishlist implements Serializable {

    private String id;
    private String customerId;
    private String productId;
    private String productName;
    private BigDecimal productValue;
    private Integer quantity;

}
