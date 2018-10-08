package com.iVot.Persistence.Participant;

import com.iVot.Domain.Participant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HelperParticipantRepository extends CrudRepository<Participant, Integer> {
    @Transactional
    List<Participant> findAllByEventId(int eventId);

    @Transactional
    Participant findByIdAndEventId(int participantId, int eventId);

    @Transactional
    List<Participant> findAllByUserId(int userId);

    @Transactional
    void deleteAllByEventId(int eventId);
}
