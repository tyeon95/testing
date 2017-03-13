package io.muic.ooc.webapp.controller;

import io.muic.ooc.webapp.api.entity.Trimester;
import io.muic.ooc.webapp.api.service.schedule.ScheduleService;
import io.muic.ooc.webapp.api.service.TrimesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tyeon on 3/10/17.
 */
@RestController
@RequestMapping(value = "/"+ Trimester.PLURAL)
public class TrimesterController {
    @Autowired
    private TrimesterService trimesterService;
    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
    public Map getTrimesters() {
        HashMap<String, Object> frb = new HashMap<>();
        List<Trimester> trimesters = trimesterService.findAll();
        frb.put(Trimester.PLURAL, trimesters);
        return frb;
    }

    @RequestMapping(value = {"/{id}/", "/{id}"}, method = RequestMethod.GET)
    public Map getTrimester(@PathVariable long id) {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(Trimester.SINGULAR, trimesterService.findOne(id));
        return frb;
    }

    @RequestMapping(value = {"/get_latest/", "/get_latest"}, method = RequestMethod.GET)
    public Map getLatestTrimester() {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(Trimester.SINGULAR, trimesterService.getLatestTrimester());
        return frb;
    }

    @RequestMapping(value = {"/{id}/get_by_schedule/", "/{id}/get_by_schedule"}, method = RequestMethod.GET)
    public Map getTrimesterBySchedule(@PathVariable long id) {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(Trimester.SINGULAR, scheduleService.getTrimester(id));
        return frb;
    }

    @RequestMapping(value = {"/{year}/get_by_year/", "/{year}/get_by_year"}, method = RequestMethod.GET)
    public Map getTrimesterByYear(@PathVariable int year) {
        HashMap<String, Object> frb = new HashMap<>();
        frb.put(Trimester.PLURAL, trimesterService.findByYear(year));
        return frb;
    }

    @RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
    public Map createTrimester(@RequestParam(required = false) Integer trimester, @RequestParam(required = false) Integer year) {
        HashMap<String, Object> frb = new HashMap<>();
        Trimester tri = null;
        if (trimester != null && year != null) {
            tri = trimesterService.create(trimester, year);
        }
        frb.put(Trimester.SINGULAR, tri);
        return frb;
    }
}
