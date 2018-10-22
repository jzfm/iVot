package com.iVot.Persistence.Event;

import com.iVot.Domain.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HelperEventRepository extends CrudRepository<Event,Integer> {
    @Transactional
    List<Event> findAllByOrganizationIdOrderByDateDesc(int organizationId);

    @Transactional
    void deleteAllByOrganizationId(int organizationId);

    @Transactional
    void deleteByIdAndOrganizationId(int eventId, int organizationId);

    @Transactional
    Event findByIdAndOrganizationId (int eventId, int organizationId);
}
