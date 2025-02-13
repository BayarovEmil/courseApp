package com.example.camp_boost.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record CourseRequest(
        @NotBlank(message = "Course name cant be blank")
        String courseName,
        @NotBlank(message = "Course teacher name cant be blank")
        String teacherName,
        @NotBlank(message = "Course description name cant be blank")
        String description,
        @Positive(message = "Course name cant be minus")
        double price,
        @Positive(message = "Course name cant be minus")
        int duration
) {

}
