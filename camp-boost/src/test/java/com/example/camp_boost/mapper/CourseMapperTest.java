package com.example.camp_boost.mapper;

import com.example.camp_boost.model.dto.request.CourseRequest;
import com.example.camp_boost.model.dto.response.CourseResponse;
import com.example.camp_boost.model.entity.Course;
import com.example.camp_boost.repository.CourseRepository;
import com.example.camp_boost.repository.MyCourseRepository;
import com.example.camp_boost.service.CourseService;
import com.example.camp_boost.service.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CourseMapperTest {
    @InjectMocks
    private CourseMapper courseMapper;
    @Mock
    private Page<Course> mockPage;

    @BeforeEach
    void setUp() {
//        courseMapper = new CourseMapper();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void convertToCourse_success() {
        // Given
        CourseRequest courseRequest = new CourseRequest(
                      "Name","Teacher","Description",12.0,55
        );
        // When
        Course course = courseMapper.convertToCourse(courseRequest);
        // Then
        assertNotNull(course);
        assertEquals(courseRequest.courseName(),course.getCourseName());
        assertEquals(courseRequest.teacherName(),course.getTeacherName());
        assertEquals(courseRequest.description(),course.getDescription());
        assertEquals(courseRequest.price(),course.getPrice());
        assertEquals(courseRequest.duration(),course.getDuration());
    }

    @Test
    void convertToResponse_success() {
        // Given
        Course course = new Course(
                1,"Name","TeacherName","Description",15,12.0,4.5,6
        );
        // When
        CourseResponse courseResponse = courseMapper.convertToResponse(course);
        // Then
        assertNotNull(course);
        assertEquals(course.getCourseName(),courseResponse.courseName());
        assertEquals(course.getTeacherName(),courseResponse.teacherName());
        assertEquals(course.getDescription(),courseResponse.description());
        assertEquals(course.getStudentCount(),courseResponse.studentCount());
        assertEquals(course.getPrice(),courseResponse.price());
        assertEquals(course.getRating(),courseResponse.rating());
        assertEquals(course.getDuration(),courseResponse.duration());
    }

    @Test
    void convertToResponses_success() {
        // GIVEN: Page<Course> mock edirik
        List<Course> courseList = List.of(
                new Course(1,"Name","TeacherName","Description",15,12.0,4.5,6),
                new Course(2,"MyName","TeacherName","Description",15,12.0,4.5,6)
        );
        Page<Course> coursePage = new PageImpl<>(courseList);
        when(mockPage.getContent()).thenReturn(courseList);
//        when(courseMapper.convertToResponse(any(Course.class))).thenAnswer(invocation -> {
//            Course course = invocation.getArgument(0);
//            return new CourseResponse(course.getCourseName(),course.getTeacherName(), course.getDescription(),course.getStudentCount(),
//                    course.getPrice(), course.getRating(),course.getDuration());
//        });

        // When
        List<CourseResponse> responses = courseMapper.convertToResponses(coursePage);

        // Then
        assertNotNull(responses);
        assertEquals(2, responses.size());

        assertEquals(courseList.get(0).getCourseName(), responses.get(0).courseName());
        assertEquals(courseList.get(0).getDescription(), responses.get(0).description());
        assertEquals(courseList.get(0).getPrice(), responses.get(0).price());
        assertEquals(courseList.get(0).getDuration(), responses.get(0).duration());
        assertEquals(courseList.get(0).getTeacherName(), responses.get(0).teacherName());

        assertEquals(courseList.get(1).getCourseName(), responses.get(1).courseName());
        assertEquals(courseList.get(1).getDescription(), responses.get(1).description());
        assertEquals(courseList.get(1).getPrice(), responses.get(1).price());
        assertEquals(courseList.get(1).getDuration(), responses.get(1).duration());
        assertEquals(courseList.get(1).getTeacherName(), responses.get(1).teacherName());

        // `convertToResponse()` metodu hər kurs üçün çağırılmalıdır
//        verify(courseMapper, times(2)).convertToResponse(any(Course.class));
    }
}