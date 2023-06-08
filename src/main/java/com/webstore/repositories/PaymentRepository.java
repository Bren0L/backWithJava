package com.webstore.repositories;

import com.webstore.entities.CourseEntity;
import com.webstore.entities.PaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity, Long> {
    PaymentEntity findPaymentEntityByPaymentId(long id);
}
