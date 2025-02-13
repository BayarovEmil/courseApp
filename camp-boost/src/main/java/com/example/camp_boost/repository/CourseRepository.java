package com.example.camp_boost.repository;

import com.example.camp_boost.model.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository implements CourseRepo {
    List<Course> courses = new ArrayList<>();
    @Override
    public Course add(Course course) {
        courses.add(course);
        return course;
    }

    @Override
    public List<Course> getAll() {
        return courses;
    }

    @Override
    public Course update(Course course) {
        int courseId = course.getId();
        Course course1 = courses.get(courseId);
        courses.remove(course1);
        course1.setCourseName(course.getCourseName());
        course1.setRating(course.getRating());
        course1.setPrice(course.getPrice());
        course1.setDuration(course.getDuration());
        course1.setDescription(course.getDescription());
        course1.setTeacherName(course.getTeacherName());
        courses.add(course1);
        return course1;
    }

    @Override
    public void delete(int courseId) {
        courses.remove(courseId);
    }
}
