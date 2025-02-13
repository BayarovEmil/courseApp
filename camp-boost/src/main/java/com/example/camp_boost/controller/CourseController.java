package com.example.camp_boost.controller;

import com.example.camp_boost.model.dto.request.CourseRequest;
import com.example.camp_boost.model.dto.response.CourseResponse;
import com.example.camp_boost.model.entity.Course;
import com.example.camp_boost.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    //add course, getAll, update, delete
    private final CourseService courseService;

    @PostMapping("/add")
    public ResponseEntity<CourseResponse> add(@RequestBody @Valid CourseRequest course) {
        return new ResponseEntity<>(courseService.add(course), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CourseResponse>> getAll(
            @RequestParam(name = "page",defaultValue = "0",required = false) int page,
            @RequestParam(name = "size",defaultValue = "10",required = false) int size
    ) {
        return ResponseEntity
                .status(200)
                .body(courseService.getAll(page,size));
    }

    @PutMapping("/update")
    public ResponseEntity<CourseResponse> update(CourseRequest course) {
        return ResponseEntity
                .status(201)
                .body(courseService.update(course));
    }

    @DeleteMapping("/delete/{course-id}")
    public void delete(
            @PathVariable("course-id") int courseId
    ) {
        courseService.delete(courseId);
        ResponseEntity
                .status(204)
                .body("Course silindi");
    }

    @GetMapping("/getAllByFilter")
    public ResponseEntity<List<CourseResponse>> getAllCourses(
            @RequestParam(name = "page",defaultValue = "1",required = false) int page,
            @RequestParam(name = "size",defaultValue = "10",required = false) int size,
            @RequestParam(name = "lowPrice",defaultValue = "0",required = false) int lowPrice,
            @RequestParam(name = "highPrice", defaultValue = "100000",required = false) int highPrice
    ) {
        return ResponseEntity.status(200)
                .body(courseService.getAllCourses(page, size, lowPrice, highPrice));
    }
}
