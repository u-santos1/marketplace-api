package com.uerles.marketplace_api.config.Service;

import com.uerles.marketplace_api.Repository.CategoryRepository;
import com.uerles.marketplace_api.domain.Category;
import com.uerles.marketplace_api.domain.dtos.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;
    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }
    public CategoryDTO create(CategoryDTO data){
        Category category = new Category();
        category.setTitle(data.title());
        category.setDescription(data.description());

        repository.save(category);

        return CategoryDTO.fromEntity(category);
    }
    public List<CategoryDTO> findAll(){
        return repository.findAll()
                .stream().map(CategoryDTO::fromEntity).toList();
    }
}
