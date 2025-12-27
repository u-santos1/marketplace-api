package com.uerles.marketplace_api;


import com.uerles.marketplace_api.domain.dtos.ProductDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;
    public ProductController(ProductService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductDTO data){
        ProductDTO newProduct = service.create(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }
    @GetMapping
    public ResponseEntity<List<ProductDTO>> listAll(){
        List<ProductDTO> list = service.findAll();

        return ResponseEntity.ok(list);
    }
}
