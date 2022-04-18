package br.com.tecnodev.repository;

import br.com.tecnodev.entities.category.Category;
import br.com.tecnodev.entities.category.CategoryPageDTO;
import br.com.tecnodev.projections.CategoryReportProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByActiveTrue();

    List<Category> findAllByOrderByOrderInSystem();


    Optional<Category> findByCode(String code);

    @Query(nativeQuery = true, value = """
             SELECT cat.name as 'categoryName', COUNT(course.id) as 'quantityCoursesFromCategory'
             FROM Category cat
                      LEFT JOIN Subcategory sub
                                on cat.id = sub.category_id
                      LEFT JOIN Course course
                                ON sub.id = course.subcategory_id
             GROUP BY cat.name
             ORDER BY COUNT(course.id) DESC;
            """)
    List<CategoryReportProjection> report();

    @Query("""
            SELECT DISTINCT c
            FROM Category c
            INNER JOIN SubCategory s
            ON c.id = s.category.id
            INNER JOIN Course course
            ON course.id = course.subCategory.id
            WHERE c.active = true AND s.active = true AND course.visibility = 'PUBLIC'
            ORDER BY c.orderInSystem
            """)
    List<Category> categoriesLoginInformations();

}
