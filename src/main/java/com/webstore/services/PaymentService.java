package com.webstore.services;

import com.webstore.entities.CourseEntity;
import com.webstore.entities.PaymentEntity;
import com.webstore.repositories.CourseRepository;
import com.webstore.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository repository;
    @Autowired
    private CourseRepository courseRepository;

    public PaymentEntity getPaymentById(long id) throws PaymentNotFoundException {
        Optional<PaymentEntity> result = repository.findById(id);
        if(result.isPresent())
            return result.get();
        throw new PaymentNotFoundException("Could not find payment with id="+id);
    }

    public void deleteCourse(CourseEntity course){
        courseRepository.delete(course);
    }

    public void save(PaymentEntity payment){
        repository.save(payment);
    }

}
