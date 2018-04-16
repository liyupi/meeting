package service;

import entity.ApplyLearning;

import java.util.List;

public interface ApplyLearningService {
    void insertApplyLearning(ApplyLearning applyLearning);

    void deleteApplyLearning(int id);

    void updateApplyLearning(ApplyLearning applyLearning);

    long getApplyLearningCount(int applyStatus);

    long getApplyLearningCountAll();

    List<ApplyLearning> getApplyLearningByPage(int page, int limit, int applyStatus);

    List<ApplyLearning> getApplyLearningByPageAll(int page, int limit);
}
