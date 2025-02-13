package com.example.camp_boost.model.dto.response;

import lombok.*;

@Builder
public record CourseResponse(
        String courseName,
        String teacherName,
        String description,
        int studentCount,
        double price,
        double rating,
        int duration
) {
}
