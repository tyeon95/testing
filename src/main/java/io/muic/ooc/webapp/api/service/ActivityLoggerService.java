package io.muic.ooc.webapp.api.service;

import io.muic.ooc.webapp.api.entity.course.CourseActivity;
import io.muic.ooc.webapp.api.repository.schedule.AddedCourseActivityRepository;
import io.muic.ooc.webapp.api.repository.course.CourseActivityRepository;
import io.muic.ooc.webapp.api.repository.FollowActivityRepository;
import io.muic.ooc.webapp.api.repository.schedule.ScheduleActivityRepository;
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
    @Autowired
    private FollowActivityRepository followActivityRepository;

//    TODO: GET SCHEDULE AND ADDCOURSE ACTIVITY LOG BY USER (friends)


    public Set<CourseActivity> getCourseActivity() {
        return courseActivityRepository.findTop20ByOrderByIdDesc();
    }
//  TODO: GET FOLLOW ACTIVITY BY USER (friends)
//    public Set<FollowActivity> getFollowActivity() {
//        return followActivityRepository.findBy
//    }
}
