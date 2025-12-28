package com.uerles.marketplace_api.domain.dtos;

import com.uerles.marketplace_api.domain.Category;
import jakarta.validation.constraints.NotBlank;


public record CategoryDTO(
        Long id,
        @NotBlank String title,
        @NotBlank String description
) {
    public static CategoryDTO fromEntity(Category category){
        return new CategoryDTO(
                category.getId(),
                category.getTitle(),
                category.getDescription()
        );
    }
}
