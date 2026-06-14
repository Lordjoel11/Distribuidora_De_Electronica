package com.Districto_Tech.distribuidora.features.products.dto;

import com.Districto_Tech.distribuidora.features.products.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {

    private Long id;
    private String name;
    private String description;
    private Integer stock;
    private Double unitPrice;
    private Category category;
}