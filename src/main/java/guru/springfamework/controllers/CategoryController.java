package guru.springfamework.controllers;

import guru.springfamework.domain.Category;
import guru.springfamework.payload.CategoryListDTO;
import guru.springfamework.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jt on 9/26/17.
 */
@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories(){
        return ResponseEntity.ok(new CategoryListDTO(categoryService.getAllCategories()));
    }

    @GetMapping("categoryByName/{name}")
    public ResponseEntity<Category> getCategoryByName(@PathVariable String name){
        return ResponseEntity.ok(categoryService.getCategoryByName(name));
    }
}
