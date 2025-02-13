package com.example.camp_boost.service;

import com.example.camp_boost.model.dto.request.CourseRequest;
import com.example.camp_boost.model.dto.response.CourseResponse;
import com.example.camp_boost.model.entity.Course;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {
    CourseResponse add(CourseRequest course);
    List<CourseResponse> getAll(int page, int size);
    CourseResponse update(CourseRequest course);
    void delete(int courseId);
    List<CourseResponse> getAllCourses(int page, int size, int lowPrice, int highPrice);

}
