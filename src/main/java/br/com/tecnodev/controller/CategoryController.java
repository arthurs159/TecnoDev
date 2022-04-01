package br.com.tecnodev.controller;

import br.com.tecnodev.entities.category.CategoryActiveDto;
import br.com.tecnodev.entities.category.CategoryProjection;
import br.com.tecnodev.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


//    @GetMapping("/list/active")
//    @ResponseBody
//    public List<CategoryActiveDto> listAllActiveCategoriesRepository() {
//        List<CategoryActiveDto> categoryList = categoryRepository.findAllActiveUsers();
//        return categoryList;
//    }
//
//    @GetMapping("/api/categories")
//    @ResponseBody
//    public List<CategoryActiveDto> listAllActiveCategoriesProjection() {
//        List<CategoryProjection> categoryList = categoryRepository.listProjection();
//        return categoryList.stream().map(CategoryActiveDto::new).toList();
//    }
}
