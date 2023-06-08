package com.webstore.services;

import com.webstore.entities.CourseEntity;
import com.webstore.entities.PaymentEntity;
import com.webstore.repositories.CourseRepository;
import com.webstore.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CourseRepository courseRepository;


    public void cadCourse(CourseEntity course){
        courseRepository.save(course);
    }

    public CourseEntity getCourseByCourseId(long id) throws CourseNotFoundException {
        Optional<CourseEntity> result = courseRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new CourseNotFoundException("Could not find course with id="+id);
    }
    public List<CourseEntity> getAllCoursesById(List<Long> id){
        return (List<CourseEntity>)courseRepository.findAllById(id);
    }

    public void deleteAllCourses(){
        courseRepository.deleteAll();
    }

    public void deleteCourse(long id) throws CourseNotFoundException {
        Long count = courseRepository.countCourseEntityByCourseId(id);
        if(count == null || count == 0){
            throw new CourseNotFoundException("Could not find course with id="+count);
        }
        courseRepository.deleteById(id);
    }

    public List<CourseEntity> getAllCourses(){
        return (List<CourseEntity>) courseRepository.findAll();
    }
}
