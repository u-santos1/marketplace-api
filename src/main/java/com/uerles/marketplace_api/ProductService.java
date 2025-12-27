package com.uerles.marketplace_api;

import com.uerles.marketplace_api.domain.Product;
import com.uerles.marketplace_api.domain.ProductRepository;
import com.uerles.marketplace_api.domain.dtos.ProductDTO;
import com.uerles.marketplace_api.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    ProductService(ProductRepository repository){
        this.repository = repository;
    }

    @Transactional
    public ProductDTO create(ProductDTO data){
        Product newProduct = new Product();
        newProduct.setTitle(data.title());
        newProduct.setDescription(data.description());
        newProduct.setPrice(data.price());
        newProduct.setQuantityStock(data.quantityStock());
        newProduct.setActive(true);

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
