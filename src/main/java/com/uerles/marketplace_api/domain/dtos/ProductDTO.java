package com.uerles.marketplace_api.domain.dtos;

import com.uerles.marketplace_api.domain.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductDTO(
        Long id,

        @NotBlank(message = "O titulo nao pode estar vazio")
        String title,

        String description,

        @NotNull(message = "O preco e obrigatorio")
        @Positive(message = "O preco deve ser maior que zero ")
        BigDecimal price,

        @NotNull
        @Positive
        Integer quantityStock,

        Boolean active,

        @NotNull(message = "A categoria e obrigatoria")
        Long categoryId

) {
    public static ProductDTO fromEntity(Product product){
        return new ProductDTO(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantityStock(),
                product.getActive(),
                product.getCategory() !=
                        null ? product.getCategory().getId() :
                        null
        );
    }
}
