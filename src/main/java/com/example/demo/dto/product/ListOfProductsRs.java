package com.example.demo.dto.product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ListOfProductsRs {
    List<ProductRs> productRsList;

    @Data
    @Builder
    public static class ProductRs{
        private String name;

        private String description;

        private String shortDescription;

        private BigDecimal price;

        private String link;
    }
}
