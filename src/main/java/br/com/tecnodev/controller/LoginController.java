package br.com.tecnodev.controller;

import br.com.tecnodev.entities.category.CategoryPageDTO;
import br.com.tecnodev.repository.CategoryRepository;
import br.com.tecnodev.repository.SubCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class LoginController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        List<CategoryPageDTO> categoryPageDTO = categoryRepository.getCategoriesWithActiveSubcategoryAndPublicCourses()
                .stream().map(CategoryPageDTO::new).toList();

        model.addAttribute("categories", categoryPageDTO);
        return "login/login";
    }

}
