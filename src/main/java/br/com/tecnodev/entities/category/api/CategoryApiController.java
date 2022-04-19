package br.com.tecnodev.entities.category.api;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.repository.CategoryRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/api/categories", produces = {"application/json", "application/xml"}, consumes = MediaType.ALL_VALUE)
    @Cacheable(value = "listAllCategories")
    public ResponseEntity<List<CategoryApiDTO>> listAllActiveCategories() {
        List<Category> allByActiveTrue = categoryRepository.findAllByActiveTrue();
        List<CategoryApiDTO> categoryApiDTOS = allByActiveTrue.stream().map(CategoryApiDTO::new).toList();
        return ResponseEntity.ok().body(categoryApiDTOS);
    }

    @GetMapping("/bGltcGEtby1jYWNoZS1kYS1hcGktYWU")
    @CacheEvict(value = "listAllCategories", allEntries = true)
    public ResponseEntity cleaningApiCache(){
        return ResponseEntity.ok().build();
    }
}
