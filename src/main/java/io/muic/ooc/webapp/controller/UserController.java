package io.muic.ooc.webapp.controller;

import io.muic.ooc.webapp.api.entity.User;
import io.muic.ooc.webapp.api.service.schedule.ScheduleService;
import io.muic.ooc.webapp.api.service.UserService;
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
@RequestMapping(value = "/"+User.PLURAL)
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public Map getUsers() {
        HashMap<String, Object> frb = new HashMap<>();
        List<User> users = userService.findAll();
        frb.put(User.PLURAL, users);
        return frb;
    }

    @RequestMapping(value = {"/{id}/", "/{id}"}, method = RequestMethod.GET)
    public Map getUser(@PathVariable long id) {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(User.SINGULAR, userService.findOne(id));
        return frb;
    }

    @RequestMapping(value = {"/{id}/get_by_schedule/}", "/{id}/get_by_schedule"}, method = RequestMethod.GET)
    public Map getUserBySchedule(@PathVariable long id) {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(User.SINGULAR, scheduleService.getUser(id));
        return frb;
    }

    /* TODO: GET Users BY Course and trimester */

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public Map createUser(@RequestParam(required = false) String studentId, @RequestParam(required = false) String firstname,
                            @RequestParam(required = false) String lastname, @RequestParam(required = false) Long userGroupId,
                            @RequestParam(required = false) String hashedpassword) {
        HashMap<String, Object> frb = new HashMap<>();
        User user = null;
        if (!StringUtils.isBlank(studentId)) {
            user = userService.create(studentId, firstname, lastname, userGroupId, hashedpassword);
        }
        frb.put(User.SINGULAR, user);
        return frb;
    }

    @RequestMapping(value = {"/{id}/", "/{id}"}, method = RequestMethod.POST)
    public Map updateUser(@PathVariable long id, @RequestParam(required = false) String studentId,
                          @RequestParam(required = false) String firstname, @RequestParam(required = false) String lastname,
                          @RequestParam(required = false) Long userGroupId, @RequestParam(required = false) String hashedpassword) {
        HashMap<String, Object> frb = new HashMap<>();
        User user = null;
        if (!StringUtils.isBlank(studentId)) {
            user = userService.update(id, studentId, firstname, lastname, userGroupId, hashedpassword);
        }
        frb.put(User.SINGULAR, user);
        return frb;
    }

    @RequestMapping(value = {"/{id}/delete/", "/{id}/delete"}, method = RequestMethod.POST)
    public void deleteUser(@PathVariable long id) {
        userService.archive(id);
    }
}