package com.webstore.controller;

import com.webstore.entities.ClientEntity;
import com.webstore.entities.CourseEntity;
import com.webstore.entities.PaymentEntity;
import com.webstore.services.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.PreRemove;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class CourseController {
    private ClientEntity clientEntity;
    @Autowired
    private CourseService service;
    @Autowired
    private ClientService clientService;
    @Autowired
    private PaymentService paymentService;

    @GetMapping(value = "/course")
    public String getCourse(@ModelAttribute("getClient") ClientEntity client, Model model) {
    clientEntity = client;
    List<CourseEntity> courses = service.getAllCourses();
    model.addAttribute("courseList", courses);

    return "courses_page";
    }

    /*@GetMapping("/courses/coursesFromClient")
    public String getClientCourses(Model model) throws ClientNotFoundException {
        ClientEntity client = clientService.getClient(clientEntity.getRegistration());
        System.out.println(client);
        List<CourseEntity> courses = client.getPayment().getCourses();
        model.addAttribute("clientCourses", courses);

        return "courses_client";
    }*/

    @PostMapping("/courses/getCourse/{id}")
    public String getCourse(@PathVariable("id") long id, RedirectAttributes ra, Model model){
        try {

            CourseEntity course = service.getCourseByCourseId(id);
            if (clientEntity.getPayment() == null) {
                clientEntity.setPayment(new PaymentEntity());
                clientEntity.getPayment().addCourse(course);
                clientEntity.getPayment().setClient(clientEntity);
                clientEntity.setPayment(clientEntity.getPayment());
                System.out.println(clientEntity);

                clientService.save(clientEntity);
            }else{
                for(CourseEntity c : clientEntity.getPayment().getCourses()) {
                    if (c.equals(course)) {
                        ra.addFlashAttribute("getClient", clientEntity);
                        model.addAttribute("message", "Course already exists");
                        return "redirect:/course";
                    }
                }
                clientEntity.getPayment().addCourse(course);
                clientService.save(clientEntity);
            }

            return "courses_page";
        }catch (CourseNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/courses";
        }
    }

    @PostMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable("id") long id, RedirectAttributes ra) throws CourseNotFoundException, Exception {
        System.out.println("Client: "+ clientEntity);
        clientEntity.getPayment().getCourses().remove(service.getCourseByCourseId(id));
        System.out.println("Client: "+ clientEntity);
        paymentService.save(clientEntity.getPayment());
        return "redirect:/course";
    }
}
