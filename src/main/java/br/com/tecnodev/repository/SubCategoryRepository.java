package br.com.tecnodev.repository;

import br.com.tecnodev.entities.subCategory.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    List<SubCategory> findSubCategoriesByCategory_CodeOrderByOrderInSystem(String code);

}
