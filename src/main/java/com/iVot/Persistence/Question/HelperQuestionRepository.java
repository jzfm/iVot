package com.iVot.Persistence.Question;

import com.iVot.Domain.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HelperQuestionRepository extends CrudRepository<Question, Integer> {
    @Transactional
    List<Question> findAllByTopic(int topicId);

    Question findByIdAndTopic(int optionId, int topicId);

    @Transactional
    void deleteByIdAndTopic(int optionId, int topicId);

    @Transactional
    void deleteAllByTopic(int topicId);
}
