package com.example.camp_boost.repository;

import com.example.camp_boost.model.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MyCourseRepository extends JpaRepository<Course,Integer> {
    Page<Course> findAllByPriceBetween(Pageable pageable,int lowPrice, int highPrice);
    Page<Course> findAllByCourseName(Pageable pageable,String courseName);
    Page<Course> findAllByRatingBetween(Pageable pageable,double low,double high);
}
