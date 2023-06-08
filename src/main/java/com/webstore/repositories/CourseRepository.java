package com.webstore.repositories;

import com.webstore.entities.CourseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, Long> {
    Long countCourseEntityByCourseId(long id);
    CourseEntity findCourseEntityByCourseId(long courseId);
}
