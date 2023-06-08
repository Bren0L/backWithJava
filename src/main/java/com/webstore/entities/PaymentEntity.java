package com.webstore.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "payment", schema = "lojavirtual")
public class PaymentEntity implements Serializable {
    public PaymentEntity(long paymentId, String paymentDate, List<CourseEntity> courses) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.courses = courses;
    }
    public PaymentEntity(){}

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "payment_id", unique = true, nullable = false)
    private long paymentId;
    @Basic
    @Column(name = "payment_date")
    private String paymentDate;
    @OneToOne
    @MapsId
    @JoinColumn(name = "client_id", unique = true)
    private ClientEntity client;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "payment_course", joinColumns = @JoinColumn(name = "payment_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<CourseEntity> courses = new ArrayList<>();

    public long getPaymentId() {
        return paymentId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public List<CourseEntity> getCourses() {
        return courses;
    }


    public void addCourse(CourseEntity course) {
        this.courses.add(course);
    }

    public void deleteCourse(long id){
        for(CourseEntity course : courses){
            if(course.getCourseId() == id) {
                courses.remove(course);
                System.out.println(course+" deleted successfully");
            }
        }
        System.out.println("Could not find course with id="+id);
    }

    @Override
    public String toString() {
        return "PaymentEntity{" +
                "paymentId=" + paymentId +
                ", paymentDate='" + paymentDate + '\'' +
                ", courses=" + courses +
                '}';
    }
}
