package com.iVot.Persistence.Question;

import com.iVot.Domain.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface HelperQuestionRepository extends CrudRepository<Question, Integer> {
    @Transactional
    List<Question> findAllByPollId(int topicId);

    Question findByIdAndPollId(int optionId, int topicId);

    @Transactional
    void deleteByIdAndPollId(int optionId, int topicId);

    @Transactional
    void deleteAllByPollId(int topicId);
}
