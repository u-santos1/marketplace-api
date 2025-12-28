package com.uerles.marketplace_api.Controller;

import com.uerles.marketplace_api.config.Service.CategoryService;
import com.uerles.marketplace_api.domain.Category;
import com.uerles.marketplace_api.domain.dtos.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService service;
    public CategoryController(CategoryService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody @Valid CategoryDTO data){
        CategoryDTO newCategory = service.create(data);

        return ResponseEntity.ok(newCategory);
    }
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
