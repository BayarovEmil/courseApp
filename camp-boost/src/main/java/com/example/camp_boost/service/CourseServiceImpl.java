package com.example.camp_boost.service;

import com.example.camp_boost.mapper.CourseMapper;
import com.example.camp_boost.model.dto.request.CourseRequest;
import com.example.camp_boost.model.dto.response.CourseResponse;
import com.example.camp_boost.model.entity.Course;
import com.example.camp_boost.repository.CourseRepo;
import com.example.camp_boost.repository.MyCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;
    private final MyCourseRepository myCourseRepository;

    @Override
    public CourseResponse add(CourseRequest request) {
        //CourseRequest->Course->CourseResponse
        Course course = courseMapper.convertToCourse(request);
//        courseRepo.add(course);
        myCourseRepository.save(course);
        //course->response
        return courseMapper.convertToResponse(course);
    }

    @Override
    public List<CourseResponse> getAllCourses(int page, int size, int lowPrice, int highPrice) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("id").ascending());
        Page<Course> courses = myCourseRepository.findAllByPriceBetween(pageable,lowPrice,highPrice);
        return courseMapper.convertToResponses(courses);
    }

    @Override
    public List<CourseResponse> getAll(int page,int size) {
        //List<Course> ->CourseResponse
//        List<Course> courses = courseRepo.getAll();
        Pageable pageable = PageRequest.of(page-1,size, Sort.by("id").descending());
        Page<Course> responses = myCourseRepository.findAll(pageable);//List<Course> ->Page<Course>
        return courseMapper.convertToResponses(responses);
    }

    @Override
    public CourseResponse update(CourseRequest request) {
        Course course = courseMapper.convertToCourse(request);
//        courseRepo.update(course);
        myCourseRepository.save(course);
        return courseMapper.convertToResponse(course);
    }

    @Override
    public void delete(int courseId) {
//        courseRepo.delete(courseId);
        myCourseRepository.deleteById(courseId);
    }
}
