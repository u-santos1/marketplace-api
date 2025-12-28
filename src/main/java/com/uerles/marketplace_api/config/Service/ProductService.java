package com.uerles.marketplace_api.config.Service;

import com.uerles.marketplace_api.Repository.CategoryRepository;
import com.uerles.marketplace_api.domain.Category;
import com.uerles.marketplace_api.domain.Product;
import com.uerles.marketplace_api.Repository.ProductRepository;
import com.uerles.marketplace_api.domain.dtos.ProductDTO;
import com.uerles.marketplace_api.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    ProductService(ProductRepository repository, CategoryRepository categoryRepository){
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    @CacheEvict(value = "products", allEntries = true)
    public ProductDTO create(ProductDTO data){
        Category category = categoryRepository.findById(data.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria nao encontrada"));

        Product newProduct = new Product();
        newProduct.setTitle(data.title());
        newProduct.setDescription(data.description());
        newProduct.setPrice(data.price());
        newProduct.setQuantityStock(data.quantityStock());
        newProduct.setActive(true);

        newProduct.setCategory(category);

        repository.save(newProduct);
        return ProductDTO.fromEntity(newProduct);
    }
    public ProductDTO findById(Long id){
        Product product = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Id nao encotrado"));
        return ProductDTO.fromEntity(product);
    }
    @Transactional
    public ProductDTO update(Long id, ProductDTO data){
        Product entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto nao encotrado"));

        entity.setTitle(data.title());
        entity.setDescription(data.description());
        entity.setPrice(data.price());
        entity.setQuantityStock(data.quantityStock());
        repository.save(entity);

        return ProductDTO.fromEntity(entity);
    }
    @Transactional
    public void delete(Long id){
        Product entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto nao encotrado"));
        entity.setActive(false);
        repository.save(entity);
    }
}
