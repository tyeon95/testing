package io.muic.ooc.webapp.api.service;

import io.muic.ooc.webapp.api.entity.Course;
import io.muic.ooc.webapp.api.entity.Trimester;
import io.muic.ooc.webapp.api.repository.CourseRepository;
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

    public long count() {
        return trimesterRepository.count();
    }

    public List<Trimester> findAll() {
        return trimesterRepository.findAll();
    }

    public Trimester findOne(long id) {
        return trimesterRepository.findOne(id);
    }

    private Trimester save(Trimester trimester) {
        return trimesterRepository.save(trimester);
    }

    public Trimester create(int trimester, int year) {
        Trimester tri = new Trimester();
        tri.setTrimester(trimester);
        tri.setYear(year);
        return save(tri);
    }

    public Trimester update(long id, int trimester, int year) {
        Trimester tri = findOne(id);
        if (tri != null) {
            tri.setTrimester(trimester);
            tri.setYear(year);
            tri = save(tri);
        }
        return tri;
    }

    private void archiveRelations(Trimester trimester) {
        for (Course course : trimester.getCourses()) {
            Set<Trimester> trimesters = course.getTrimesters();
            //TODO: ARCHIVE COURSE IF ALL TRIMESTER THAT IS IN THAT COURSE IS INACTIVE
        }
    }

    public void archive(long id) {
        Trimester trimester = findOne(id);
        archiveRelations(trimester);
        trimester.setActive(false);
        save(trimester);
    }
}
