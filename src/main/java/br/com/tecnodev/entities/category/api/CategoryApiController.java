package br.com.tecnodev.entities.category.api;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.category.CategoryDTO;
import br.com.tecnodev.repository.CategoryRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryApiController {

    private final CategoryRepository categoryRepository;

    public CategoryApiController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    @RequestMapping(value="/api/categories", produces = {"application/json"}, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<List<CategoryApiDTO>> listAllActiveCategories(){
        List<Category> allByActiveTrue = categoryRepository.findAllByActiveTrue();
        List<CategoryApiDTO> categoryApiDTOS = allByActiveTrue.stream().map(CategoryApiDTO::new).toList();

        categoryApiDTOS.forEach(c -> c.setTotalCourse(categoryRepository.countCoursesFromCategory(c.getCode())));

        return ResponseEntity.ok().body(categoryApiDTOS);
    }
}
