package com.mishajib.ecommerce.controllers;

import com.mishajib.ecommerce.dtos.CategoryDto;
import com.mishajib.ecommerce.mappers.CategoryMapper;
import com.mishajib.ecommerce.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> view(@PathVariable Byte id) {
        var category =  categoryRepository.findById(id).orElse(null);

        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(categoryMapper.toDto(category));
    }

    @PostMapping
    public ResponseEntity<?> store(
            @RequestBody CategoryDto categoryDto,
            UriComponentsBuilder uriBuilder
    ) {
        if (categoryRepository.existsByName(categoryDto.getName())) {
            return ResponseEntity.badRequest().body(
                    Map.of("name", "Category with this name already exists!")
            );
        }


        var category = categoryMapper.toEntity(categoryDto);
        categoryRepository.save(category);

        categoryDto.setId(category.getId());

        var uri = uriBuilder.path("/products/{id}").buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(uri).body(categoryDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Byte id,
            @RequestBody CategoryDto categoryDto
    ) {
        var category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        // Check for name uniqueness
        if (categoryRepository.existsByNameAndIdNot(categoryDto.getName(), id)) {
            return ResponseEntity.badRequest().body(
                    Map.of("name", "Category with this name already exists!")
            );
        }

        categoryMapper.update(categoryDto, category);
        categoryRepository.save(category);

        categoryDto.setId(category.getId());

        return ResponseEntity.ok(categoryMapper.toDto(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Byte id) {
        var category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }

        categoryRepository.delete(category);

        return ResponseEntity.noContent().build();
    }
}
