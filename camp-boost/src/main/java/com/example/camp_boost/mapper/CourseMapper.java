package com.example.camp_boost.mapper;

import com.example.camp_boost.model.dto.request.CourseRequest;
import com.example.camp_boost.model.dto.response.CourseResponse;
import com.example.camp_boost.model.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseMapper {
    public Course convertToCourse(CourseRequest request) {
        return Course.builder()
                .courseName(request.courseName())
                .description(request.description())
                .price(request.price())
                .duration(request.duration())
                .teacherName(request.teacherName())
                .build();
    }

    public CourseResponse convertToResponse(Course course) {
        return CourseResponse.builder()
                .courseName(course.getCourseName())
                .description(course.getDescription())
                .teacherName(course.getTeacherName())
                .rating(course.getRating())
                .duration(course.getDuration())
                .studentCount(course.getStudentCount())
                .price(course.getPrice())
                .build();
    }

    public List<CourseResponse> convertToResponses(Page<Course> courses) {
        List<CourseResponse> courseResponses = courses.stream()
                .map(c->convertToResponse(c))
                .toList();
        return courseResponses;
    }
}
