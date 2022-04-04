package br.com.tecnodev.repository;

import br.com.tecnodev.entities.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByActiveTrue();

    @Query("""
            SELECT count(course) from Category cat
            INNER JOIN SubCategory sub
            ON cat.id = sub.category.id
            INNER JOIN Course course
            ON course.subCategory.id = sub.id
            WHERE cat.code = :code
            GROUP BY cat.name
            """)
    Long countCoursesFromCategory(String code);
}
