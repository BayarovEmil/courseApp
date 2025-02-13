package com.example.camp_boost.repository;

import com.example.camp_boost.model.entity.Course;

import java.util.List;

public interface CourseRepo {
    Course add(Course course);
    List<Course> getAll();
    Course update(Course course);
    void delete(int courseId);
}
