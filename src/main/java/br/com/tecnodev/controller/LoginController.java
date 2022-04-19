package br.com.tecnodev.controller;

import br.com.tecnodev.entities.category.CategoryPageDTO;
import br.com.tecnodev.repository.CategoryRepository;
import br.com.tecnodev.repository.SubCategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginController {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public LoginController(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        List<CategoryPageDTO> categoriesDTO = categoryRepository.categoriesLoginInformations()
                .stream().map(CategoryPageDTO::new).toList();

        model.addAttribute("categories", categoriesDTO);
        return "login/login";
    }

}
