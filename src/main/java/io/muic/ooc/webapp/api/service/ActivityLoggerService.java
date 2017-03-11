package io.muic.ooc.webapp.api.service;

import io.muic.ooc.webapp.api.entity.CourseActivity;
import io.muic.ooc.webapp.api.entity.Trimester;
import io.muic.ooc.webapp.api.repository.AddedCourseActivityRepository;
import io.muic.ooc.webapp.api.repository.CourseActivityRepository;
import io.muic.ooc.webapp.api.repository.ScheduleActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by tyeon on 3/11/17.
 */
@Transactional
@Service
public class ActivityLoggerService {
    @Autowired
    private ScheduleActivityRepository scheduleActivityRepository;
    @Autowired
    private CourseActivityRepository courseActivityRepository;
    @Autowired
    private AddedCourseActivityRepository addedCourseActivityRepository;

//    TODO: GET SCHEDULE AND ADDCOURSE ACTIVITY LOG BY USER (friends)


//    TODO: GET COURSE ACTIVITY BY ID DESC
    public Set<CourseActivity> getCourseActivity() {
        return courseActivityRepository.findAllOrderByIdDesc();
    }
}
