package br.com.tecnodev.repository;

import br.com.tecnodev.entities.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByActiveTrue();

    List<Category> findAllByOrderByOrderInSystem();

    Optional<Category> findByCode(String code);

}
