package br.com.tecnodev.entities.category.api;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.repository.CategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/categories")
public class CategoryApiController {

    private final CategoryRepository categoryRepository;

    public CategoryApiController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public ResponseEntity<List<CategoryApiDTO>> listAllActiveCategories(){
        List<Category> allByActiveTrue = categoryRepository.findAllByActiveTrue();
        List<CategoryApiDTO> categoryApiDTOS = allByActiveTrue.stream().map(CategoryApiDTO::new).toList();
        return ResponseEntity.ok().body(categoryApiDTOS);

    }



}
