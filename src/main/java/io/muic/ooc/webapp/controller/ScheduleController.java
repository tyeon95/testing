package io.muic.ooc.webapp.controller;

import io.muic.ooc.webapp.api.entity.Course;
import io.muic.ooc.webapp.api.entity.Schedule;
import io.muic.ooc.webapp.api.service.CourseService;
import io.muic.ooc.webapp.api.service.ScheduleService;
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
@RequestMapping(value = "/"+ Schedule.PLURAL)
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public Map getSchedules() {
        HashMap<String, Object> frb = new HashMap<>();
        List<Schedule> schedules = scheduleService.findAll();
        frb.put(Schedule.PLURAL, schedules);
        return frb;
    }

    @RequestMapping(value = {"/{id}/", "/{id}"}, method = RequestMethod.GET)
    public Map getSchedule(@PathVariable long id) {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(Schedule.SINGULAR, scheduleService.findOne(id));
        return frb;
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public Map createSchedule(@RequestParam(required = false) Long userId, @RequestParam(required = false) Long trimesterId) {
        HashMap<String, Object> frb = new HashMap<>();
        Schedule schedule = scheduleService.create(userId, trimesterId);
        if (schedule != null) {
            frb.put(Schedule.SINGULAR, schedule);
        }
        return frb;
    }

    @RequestMapping(value = {"/{id}/add_course/", "/{id}/add_course"}, method = RequestMethod.POST)
    public Map addCourseToSchedule(@PathVariable long id, @RequestParam(required = false) Long courseId) {
        HashMap<String, Object> frb = new HashMap<>();
        Schedule schedule = scheduleService.addCourse(id, courseId);
        if (schedule != null) {
            frb.put(Schedule.SINGULAR, schedule);
        }
        return frb;
    }

    @RequestMapping(value = {"/{id}/remove_course/", "/{id}/remove_course"}, method = RequestMethod.POST)
    public Map removeCourseFromSchedule(@PathVariable long id, @RequestParam(required = false) Long courseId) {
        HashMap<String, Object> frb = new HashMap<>();
        Schedule schedule = scheduleService.removeCourse(id, courseId);
        if (schedule != null) {
            frb.put(Schedule.SINGULAR, schedule);
        }
        return frb;
    }

    @RequestMapping(value = {"/{id}/delete/", "/{id}/delete"}, method = RequestMethod.POST)
    public void deleteSchedule(@PathVariable long id) {
        scheduleService.archive(id);
    }
}
