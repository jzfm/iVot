package com.iVot.Persistence.Topic;

import com.iVot.Domain.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HelperTopicRepository extends CrudRepository<Topic, Integer> {
    @Transactional
    List<Topic> findAllByEventId(int eventId);

    @Transactional
    Topic findByIdAndEventId(int topicId, int eventId);

    @Transactional
    void deleteByIdAndEventId(int topicId, int eventId);

    @Transactional
    void deleteAllByEventId(int eventId);
}
