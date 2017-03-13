package io.muic.ooc.webapp.controller;

import io.muic.ooc.webapp.api.entity.schedule.Schedule;
import io.muic.ooc.webapp.api.service.schedule.ScheduleService;
import io.muic.ooc.webapp.api.service.TrimesterService;
import io.muic.ooc.webapp.api.service.UserService;
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
    @Autowired
    private UserService userService;
    @Autowired
    private TrimesterService trimesterService;

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

    @RequestMapping(value = {"/{id}/get_by_user/", "/{id}/get_by_user"}, method = RequestMethod.GET)
    public Map getScheduleByUser(@PathVariable long id) {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(Schedule.PLURAL, userService.getSchedules(id));
        return frb;
    }

    @RequestMapping(value = {"/{id}/get_by_trimester/", "/{id}/get_by_trimester"}, method = RequestMethod.GET)
    public Map getScheduleByTrimester(@PathVariable long id) {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(Schedule.PLURAL, trimesterService.getSchedules(id));
        return frb;
    }

    @RequestMapping(value = {"/{userId}/get_by_user/{trimesterId}/get_by_trimester/", "/{userId}/get_by_user/{trimesterId}/get_by_trimester"}, method = RequestMethod.GET)
    public Map getScheduleByUserAndTrimester(@PathVariable long userId, @PathVariable long trimesterId) {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(Schedule.SINGULAR, scheduleService.findByUserAndTrimester(userId, trimesterId));
        return frb;
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public Map createSchedule(@RequestParam(required = false) Long userId, @RequestParam(required = false) Long trimesterId) {
        HashMap<String, Object> frb = new HashMap<>();
        Schedule schedule = scheduleService.create(userId, trimesterId);
        frb.put(Schedule.SINGULAR, schedule);
        return frb;
    }

    @RequestMapping(value = {"/{id}/add_course/{courseId}/", "/{id}/add_course/{courseId}"}, method = RequestMethod.POST)
    public Map addCourseToSchedule(@PathVariable long id, @PathVariable long courseId,
                                   @RequestParam(required = false) String type, @RequestParam(required = false) String reason) {
        HashMap<String, Object> frb = new HashMap<>();
        Schedule schedule = scheduleService.addOrUpdateCourse(id, courseId, type.toUpperCase(), reason.toUpperCase());
        frb.put(Schedule.SINGULAR, schedule);
        return frb;
    }

    @RequestMapping(value = {"/{id}/remove_course/{courseId}/", "/{id}/remove_course/{courseId}"}, method = RequestMethod.POST)
    public Map removeCourseFromSchedule(@PathVariable long id, @PathVariable long courseId) {
        HashMap<String, Object> frb = new HashMap<>();
        Schedule schedule = scheduleService.removeCourse(id, courseId);
        frb.put(Schedule.SINGULAR, schedule);
        return frb;
    }

    @RequestMapping(value = {"/{id}/delete/", "/{id}/delete"}, method = RequestMethod.POST)
    public void deleteSchedule(@PathVariable long id) {
        scheduleService.archive(id);
    }
}
