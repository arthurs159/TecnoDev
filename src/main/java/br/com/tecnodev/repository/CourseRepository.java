package br.com.tecnodev.repository;

import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.entities.subCategory.SubCategory;
import br.com.tecnodev.projections.InstructorReportProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(nativeQuery = true, value = """
            SELECT teacher as 'instructorName', COUNT(*) as 'quantityOfCourses'
            FROM Course
            GROUP BY teacher
            ORDER BY quantityOfCourses DESC
            LIMIT 1;
            """)
    InstructorReportProjection getInstructorWithMoreCourses();

    @Query("""
            SELECT c FROM Course c
            INNER JOIN SubCategory sub
            ON sub.id = c.subCategory.id
            INNER JOIN Category cat
            ON cat.id = sub.category.id
            WHERE cat.code = :catCode AND sub.code = :SubCode
            ORDER BY c.name
            """)
    Page<Course> findAllByCourseBySubCategoryCodeAndCategoryCode(String SubCode, String catCode, Pageable pageable);


    Page<Course> findAllBySubCategory(SubCategory subCategory, Pageable pageable);

}