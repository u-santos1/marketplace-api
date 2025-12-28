package com.uerles.marketplace_api.Controller;


import com.uerles.marketplace_api.config.Service.ProductService;
import com.uerles.marketplace_api.domain.dtos.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> listById(@PathVariable Long id){
        ProductDTO productDTO = service.findById(id);

        return ResponseEntity.ok(productDTO);
    }
    @PutMapping("{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody @Valid ProductDTO data){
        ProductDTO updateProduct = service.update(id, data);
        return ResponseEntity.ok(updateProduct);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
