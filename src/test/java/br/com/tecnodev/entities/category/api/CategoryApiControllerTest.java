package br.com.tecnodev.entities.category.api;

import br.com.tecnodev.repository.CategoryRepository;
import br.com.tecnodev.repository.CourseRepository;
import br.com.tecnodev.repository.SubCategoryRepository;
import br.com.tecnodev.repository.util.ProgramDatabaseMotherTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void init() {
        ProgramDatabaseMotherTest programDatabaseMotherTest = new ProgramDatabaseMotherTest(categoryRepository, subCategoryRepository, courseRepository);
        programDatabaseMotherTest.create();
    }

    @AfterEach
    public void delete() {
        courseRepository.deleteAll();
        subCategoryRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
//    @Transactional
    void listAllActiveCategories__Should_Return_Content() throws Exception {

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