//package br.com.tecnodev.repository;
//
//import br.com.tecnodev.entities.category.Category;
//import br.com.tecnodev.entities.course.Course;
//import br.com.tecnodev.entities.subCategory.SubCategory;
//import br.com.tecnodev.repository.util.Builder.CategoryBuilder;
//import br.com.tecnodev.repository.util.Builder.CourseBuilder;
//import br.com.tecnodev.repository.util.Builder.SubcategoryBuilder;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.Optional;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
//public class CategoryRepositoryTest {
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//    @Autowired
//    private TestEntityManager em;
//
//    @Before
//    public void beforeEach() {
//        Category backEnd = CategoryBuilder.categoryBackEnd();
//        Category frontEnd = CategoryBuilder.categoryFrontEnd();
//        Category devops = CategoryBuilder.categoryDevops();
//
//        em.persist(backEnd);
//        em.persist(frontEnd);
//        em.persist(devops);
//
//        SubCategory subcategoryJava = SubcategoryBuilder.subCategoryJava(backEnd);
//        SubCategory subcategoryJavaScript = SubcategoryBuilder.subCategoryJs(frontEnd);
//        SubCategory subCategoryMobile = SubcategoryBuilder.subCategoryMobile(backEnd);
//        SubCategory subCategoryPython = SubcategoryBuilder.subCategoryPython(backEnd);
//
//        em.persist(subcategoryJava);
//        em.persist(subcategoryJavaScript);
//        em.persist(subCategoryMobile);
//        em.persist(subCategoryPython);
//
//        Course courseJava = CourseBuilder.courseJava(subcategoryJava);
//        Course courseJpa = CourseBuilder.courseJpa(subcategoryJava);
//        Course coursePython = CourseBuilder.coursePython(subCategoryPython);
//
//        em.persist(courseJava);
//        em.persist(courseJpa);
//        em.persist(coursePython);
//    }
//
//    @Test
//    public void findAllByActiveTrue() {
//        List<Category> allByActiveTrue = categoryRepository.findAllByActiveTrue();
//
//        Assert.assertEquals(2, allByActiveTrue.size());
//        Assert.assertEquals("backend", allByActiveTrue.get(0).getCode());
//    }
//
//    @Test
//    public void findAllByOrderByOrderInSystem() {
//        List<Category> allByOrderByOrderInSystem = categoryRepository.findAllByOrderByOrderInSystem();
//
//        Assert.assertNotNull(allByOrderByOrderInSystem);
//        Assert.assertEquals("devops", allByOrderByOrderInSystem.get(0).getCode());
//        Assert.assertEquals("frontend", allByOrderByOrderInSystem.get(1).getCode());
//        Assert.assertEquals("backend", allByOrderByOrderInSystem.get(2).getCode());
//    }
//
//    @Test
//    public void findByCode() {
//        String code = "backend";
//        Optional<Category> category = categoryRepository.findByCode(code);
//
//        Assert.assertTrue(category.isPresent());
//        Assert.assertEquals(code, category.get().getCode());
//    }
//
//    @Test
//    public void findByCodeShouldReturnAnOptionalEmptyWhenCodeNotExists() {
//        String code = "nonExistingCode";
//        Optional<Category> category = categoryRepository.findByCode(code);
//
//        Assert.assertTrue(category.isEmpty());
//    }
////
////    @Test
////    public void report() {
////    }
////
////    @Test
////    public void getCategoriesWithSubcategoryActiveAndVisibleCourses() {
////    }
//}