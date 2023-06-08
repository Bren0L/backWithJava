package com.webstore.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "course", schema = "lojavirtual")
public class CourseEntity implements Serializable {
    public CourseEntity(long courseId, String courseDescription, String courseName, Double coursePrice, String courseUrl) {
        this.courseId = courseId;
        this.courseDescription = courseDescription;
        this.courseName = courseName;
        this.coursePrice = coursePrice;
        this.courseUrl = courseUrl;
    }
    public CourseEntity(){}

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "course_id", unique = true, nullable = false)
    private long courseId;
    @Basic
    @Column(name = "course_description")
    private String courseDescription;
    @Basic
    @Column(name = "course_name")
    private String courseName;
    @Basic
    @Column(name = "course_price")
    private Double coursePrice;
    @Basic
    @Column(name = "course_url")
    private String courseUrl;
    @ManyToMany(mappedBy = "courses")
    private List<PaymentEntity> payments = new ArrayList<>();

    public List<PaymentEntity> getPayments() {
        return payments;
    }

    public void addPayment(PaymentEntity payments) {
        this.payments.add(payments);
    }

    public long getCourseId() {
        return courseId;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Double getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Double coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getCourseUrl() {
        return courseUrl;
    }

    public void setCourseUrl(String courseUrl) {
        this.courseUrl = courseUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CourseEntity course = (CourseEntity) o;

        if (courseId != course.courseId) return false;
        if (!Objects.equals(courseDescription, course.courseDescription)) return false;
        if (!Objects.equals(courseName, course.courseName)) return false;
        if (!Objects.equals(coursePrice, course.coursePrice)) return false;
        return (Objects.equals(courseUrl, course.courseUrl));

    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "courseId=" + courseId +
                ", courseDescription='" + courseDescription + '\'' +
                ", courseName='" + courseName + '\'' +
                ", coursePrice=" + coursePrice +
                ", courseUrl='" + courseUrl + '\'' +
                '}';
    }
}
