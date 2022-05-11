package br.com.tecnodev.repository;

import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.entities.subCategory.SubCategory;
import br.com.tecnodev.projections.InstructorReportProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(nativeQuery = true, value = """
            SELECT teacher as 'instructorName', COUNT(*) as 'quantityOfCourses'
            FROM Course
            GROUP BY teacher
            ORDER BY quantityOfCourses DESC
            LIMIT 1;
            """)
    InstructorReportProjection getInstructorWithMoreCourses();

    Page<Course> findAllBySubCategory(SubCategory subCategory, Pageable pageable);

    Optional<Course> findByCode(String courseCode);

}