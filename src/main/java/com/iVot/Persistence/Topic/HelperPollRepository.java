package com.iVot.Persistence.Topic;

import com.iVot.Domain.Poll;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HelperPollRepository extends CrudRepository<Poll, Integer> {
    @Transactional
    List<Poll> findAllByEventId(int eventId);

    @Transactional
    Poll findByIdAndEventId(int topicId, int eventId);

    @Transactional
    void deleteByIdAndEventId(int topicId, int eventId);

    @Transactional
    void deleteAllByEventId(int eventId);
}
