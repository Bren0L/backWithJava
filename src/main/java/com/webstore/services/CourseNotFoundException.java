package com.webstore.services;

public class CourseNotFoundException extends Throwable{
    CourseNotFoundException(String message){
        super(message);
    }
}
