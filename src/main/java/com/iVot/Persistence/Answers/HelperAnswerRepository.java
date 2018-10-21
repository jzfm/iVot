package com.iVot.Persistence.Answers;

import com.iVot.Domain.Answer;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface HelperAnswerRepository extends CrudRepository<Answer, Integer> {

    @Transactional
    List<Answer> findAllByPollId(int topicId);

    @Transactional
    List<Answer> findAllByQuestionId(int optionId);

    @Transactional
    Answer findByIdAndQuestionId(int answerId, int optionId);

    @Transactional
    Answer findByIdAndParticipantId(int answerId, int participantId);

    @Transactional
    void removeByIdAndQuestionId(int answerId, int optionId);

    @Transactional
    boolean existsByPollIdAndParticipantId(int topicId, int participantId);
}
