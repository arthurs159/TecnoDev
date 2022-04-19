package br.com.tecnodev.repository;

import br.com.tecnodev.entities.subCategory.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    List<SubCategory> findSubCategoriesByCategory_CodeOrderByOrderInSystem(String code);

    default List<SubCategory> getSubcategoryByCategoryCodeOrdered(String code) {
        return findSubCategoriesByCategory_CodeOrderByOrderInSystem(code);
    }

    @Query("""
            SELECT s FROM SubCategory s
            INNER JOIN Category c
            ON c.id = s.category.id
            WHERE s.code = :subcategoryCode AND c.code = :categoryCode
            """)
    Optional<SubCategory> findSubcategoryByCategoryAndSubcategoryCode(String subcategoryCode, String categoryCode);

    SubCategory findSubcategoryByCode(String code);

    @Query("""
            SELECT DISTINCT s FROM SubCategory s
            INNER JOIN Category c
            ON c.id = s.category.id
            INNER JOIN Course course
            ON s.id = course.subCategory.id
            WHERE s.active = true AND c.code = :categoryCode
            """)
    List<SubCategory> findAllActiveSubcategoryWithCourses(String categoryCode);
}
