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
            WHERE s.code = :subCode AND c.code = :catCode
            """)
    Optional<SubCategory> findByCode(String subCode, String catCode);
}
