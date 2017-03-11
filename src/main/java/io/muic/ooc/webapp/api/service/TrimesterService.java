package io.muic.ooc.webapp.api.service;

import io.muic.ooc.webapp.api.entity.Course;
import io.muic.ooc.webapp.api.entity.Schedule;
import io.muic.ooc.webapp.api.entity.Trimester;
import io.muic.ooc.webapp.api.repository.CourseRepository;
import io.muic.ooc.webapp.api.repository.ScheduleRepository;
import io.muic.ooc.webapp.api.repository.TrimesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by tyeon on 3/7/17.
 */
@Service
@Transactional
public class TrimesterService {
    @Autowired
    private TrimesterRepository trimesterRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

    public long count() {
        return trimesterRepository.count();
    }

    public List<Trimester> findAll() {
        return trimesterRepository.findAll();
    }

    public Trimester findOne(long id) {
        return trimesterRepository.findOne(id);
    }

    public Trimester getLatestTrimester() {
        return trimesterRepository.findTopByOrderByYearDescTrimesterDesc();
    }

    public Set<Course> getCourses(long id) {
        Trimester trimester = findOne(id);
        return courseRepository.findByTrimesters(trimester);
    }

    public Set<Schedule> getSchedules(long id) {
        Trimester trimester = findOne(id);
        return scheduleRepository.findByTrimesterOrderByIdAsc(trimester);
    }

    public Set<Trimester> findByYear(int year) {
        return trimesterRepository.findByYearOrderByIdAsc(year);
    }

    private Trimester save(Trimester trimester) {
        return trimesterRepository.save(trimester);
    }

    private Trimester exists(int trimester, int year) {
        return trimesterRepository.findByTrimesterAndYear(trimester, year);
    }

    public Trimester create(int trimester, int year) {
        if (trimester > 4) return null;
        Trimester tri = exists(trimester, year);
        if (tri == null) {
            tri = new Trimester();
            tri.setTrimester(trimester);
            tri.setYear(year);
        }
        return save(tri);
    }
}
