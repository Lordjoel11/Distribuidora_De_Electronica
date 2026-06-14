package com.Districto_Tech.distribuidora.features.products.dto;

import com.Districto_Tech.distribuidora.features.products.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private Integer stock;
    private Float unitPrice;
    private Category category;
}