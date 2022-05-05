package br.com.tecnodev.entities.category.api;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.entities.course.Status;
import br.com.tecnodev.entities.subCategory.SubCategory;
import br.com.tecnodev.repository.CategoryRepository;
import br.com.tecnodev.repository.CourseRepository;
import br.com.tecnodev.repository.SubCategoryRepository;
import br.com.tecnodev.repository.util.Builder.CategoryBuilder;
import br.com.tecnodev.repository.util.Builder.CourseBuilder;
import br.com.tecnodev.repository.util.Builder.SubcategoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CategoryApiControllerTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Transactional
    void listAllActiveCategories__Should_Return_Content() throws Exception {
        Category backEnd = CategoryBuilder.categoryBackEnd("Back-End", "backend", true);
        categoryRepository.save(backEnd);
        SubCategory subcategoryJava = SubcategoryBuilder.subCategoryJava(backEnd, "Java", "java", true);
        subCategoryRepository.save(subcategoryJava);
        Course courseJava = CourseBuilder.courseJava(subcategoryJava, "Java e Sintaxe", "javasintaxe", "Cleb Paulo", Status.PUBLIC);
        Course courseJpa = CourseBuilder.courseJpa(subcategoryJava, "JPA", "jpa", "Cleb Paulo", Status.PUBLIC);

        subcategoryJava.setCourses(List.of(courseJava, courseJpa));
        courseRepository.saveAll(Arrays.asList(courseJava, courseJpa));

        ResultActions result =
                mockMvc.perform(get("/api/categories")
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Back-End"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("backend"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].subCategories[0].name").value("Java"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].subCategories[0].code").value("java"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].subCategories[0].courses[0].name").value("Java e Sintaxe"));
    }

    @Test
    void cleaningApiCache() throws Exception {

        ResultActions result =
                mockMvc.perform(get("/bGltcGEtby1jYWNoZS1kYS1hcGktYWU")
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNoContent());
    }
}