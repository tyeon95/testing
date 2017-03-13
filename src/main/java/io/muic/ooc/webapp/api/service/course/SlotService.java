package io.muic.ooc.webapp.api.service.course;

import io.muic.ooc.webapp.api.entity.course.Slot;
import io.muic.ooc.webapp.api.repository.course.SlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tyeon on 3/13/17.
 */
@Transactional
@Service
public class SlotService {
    @Autowired
    private SlotRepository slotRepository;

    //TODO: initialise on startup
    //for each day, 7-19

    public long count() {
        return slotRepository.count();
    }

    public List<Slot> findAll() {
        return slotRepository.findAll();
    }

    public Slot findOne(long id) {
        return slotRepository.findOne(id);
    }

    private Slot save(Slot slot) {
        return slotRepository.save(slot);
    }

    public Slot create(String day, int start) {
        Slot slot = new Slot();
        slot.setDay(Slot.Day.valueOf(day.toUpperCase()));
        slot.setStart(start);
        slot = save(slot);
        return slot;
    }
}
