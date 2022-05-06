package br.com.tecnodev.repository;

import br.com.tecnodev.entities.subCategory.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    List<SubCategory> findSubCategoriesByCategory_CodeOrderByOrderInSystem(String code);

    @Query("""
            SELECT s FROM SubCategory s
            INNER JOIN Category c
            ON c.id = s.category.id
            WHERE s.code = :subcategoryCode AND c.code = :categoryCode
            """)
    Optional<SubCategory> findSubcategoryByCategoryAndSubcategoryCode(String subcategoryCode, String categoryCode);

    Optional<SubCategory> findSubcategoryByCode(String code);

    @Query("""
            SELECT DISTINCT s FROM SubCategory s
            JOIN FETCH s.courses c
            WHERE c.visibility = 'PUBLIC'
            AND s.active = true
            AND s.category.code = :categoryCode
            """)
    List<SubCategory> findAllActiveSubcategoryWithCoursesByCategoryCode(String categoryCode);

    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsByCode(String name);

    boolean existsByCodeAndIdNot(String name, Long id);
}
