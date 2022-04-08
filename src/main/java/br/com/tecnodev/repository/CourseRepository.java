package br.com.tecnodev.repository;

import br.com.tecnodev.entities.course.Course;
import br.com.tecnodev.projections.InstructorReportProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(nativeQuery = true, value = """
            SELECT teacher as 'instructorName', COUNT(*) as 'quantityOfCourses'
            FROM Course
            GROUP BY teacher
            ORDER BY quantityOfCourses DESC
            LIMIT 1;
            """)
    InstructorReportProjection getInstructorWithMoreCourses();
}
