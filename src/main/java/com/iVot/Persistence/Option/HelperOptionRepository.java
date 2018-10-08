package com.iVot.Persistence.Option;

import com.iVot.Domain.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HelperOptionRepository extends CrudRepository<Option, Integer> {
    @Transactional
    List<Option> findAllByTopicId(int topicId);

    @Transactional
    Option findByIdAndTopicId(int optionId, int topicId);

    @Transactional
    void removeByIdAndTopicId(int optionId, int TopicId);

    @Transactional
    void deleteAllByTopicId(int topicId);
}
