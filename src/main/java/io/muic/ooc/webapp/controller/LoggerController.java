package io.muic.ooc.webapp.controller;

import io.muic.ooc.webapp.api.ActivityLogger;
import io.muic.ooc.webapp.api.entity.Course;
import io.muic.ooc.webapp.api.entity.CourseActivity;
import io.muic.ooc.webapp.api.service.ActivityLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tyeon on 3/11/17.
 */

@RestController
@RequestMapping(value = "/"+ ActivityLogger.PLURAL)
public class LoggerController {
    @Autowired
    private ActivityLoggerService activityLoggerService;

    @RequestMapping(value = {"/courses", ""}, method = RequestMethod.GET)
    public Map getCourses() {
        HashMap<String, Object> frb = new HashMap<>();
        Set<CourseActivity> courses = activityLoggerService.getCourseActivity();
        frb.put(ActivityLogger.PLURAL, courses);
        return frb;
    }

    //TODO: GET ACTIVITY LOGGER FOR ADDCOURSES AND SCHEDULE
}
