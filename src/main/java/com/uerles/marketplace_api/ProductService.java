package com.uerles.marketplace_api;

import com.uerles.marketplace_api.domain.Product;
import com.uerles.marketplace_api.domain.ProductRepository;
import com.uerles.marketplace_api.domain.dtos.ProductDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
}
