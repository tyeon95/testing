package io.muic.ooc.webapp.controller;

import io.muic.ooc.webapp.api.entity.Course;
import io.muic.ooc.webapp.api.entity.Schedule;
import io.muic.ooc.webapp.api.service.CourseService;
import io.muic.ooc.webapp.api.service.ScheduleService;
import io.muic.ooc.webapp.api.service.TrimesterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tyeon on 3/10/17.
 */

@RestController
@RequestMapping(value = "/"+Course.PLURAL)
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private TrimesterService trimesterService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public Map getCourses() {
        HashMap<String, Object> frb = new HashMap<>();
        List<Course> courses = courseService.findAll();
        frb.put(Course.PLURAL, courses);
        return frb;
    }

    @RequestMapping(value = {"/{id}/", "/{id}"}, method = RequestMethod.GET)
    public Map getCourse(@PathVariable long id) {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(Course.SINGULAR, courseService.findOne(id));
        return frb;
    }

    @RequestMapping(value = {"/{id}/get_by_schedule/}", "/{id}/get_by_schedule"}, method = RequestMethod.GET)
    public Map getCoursesBySchedule(@PathVariable long id) {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(Course.PLURAL, scheduleService.getCourses(id));
        return frb;
    }

    @RequestMapping(value = {"/{id}/get_by_trimester/}", "/{id}/get_by_trimester"}, method = RequestMethod.GET)
    public Map getCoursesByTrimester(@PathVariable long id) {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(Course.PLURAL, trimesterService.getCourses(id));
        return frb;
    }

    /* TODO: GET COURSE BY TRIMESTER */

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public Map createCourse(@RequestParam(required = false) String code, @RequestParam(required = false) String name,
                             @RequestParam(required = false) String time, @RequestParam(required = false) int capacity,
                             @RequestParam(required = false) Long trimesterId) {
        HashMap<String, Object> frb = new HashMap<>();
        if (!StringUtils.isBlank(code)) {
            Course course = courseService.create(code, name, time, capacity, trimesterId);
            frb.put(Course.SINGULAR, course);
        }
        return frb;
    }

    @RequestMapping(value = {"/{id}/", "/{id}"}, method = RequestMethod.POST)
    public Map updateCourse(@PathVariable long id, @RequestParam(required = false) String code,
                             @RequestParam(required = false) String name, @RequestParam(required = false) String time,
                             @RequestParam(required = false) int capacity, @RequestParam(required = false) Long trimesterId) {
        HashMap<String, Object> frb = new HashMap<>();
        if (!StringUtils.isBlank(code)) {
            Course course = courseService.update(id, code, name, time, capacity, trimesterId);
            frb.put(Course.SINGULAR, course);
        }
        return frb;
    }

    @RequestMapping(value = {"/{id}/delete/", "/{id}/delete"}, method = RequestMethod.POST)
    public void deleteCourse(@PathVariable long id) {
        courseService.archive(id);
    }
}
