package service;

import entity.ApplyLearning;

import java.util.List;

/**
 * @Author: Yupi Li
 * @Date: Created in 15:00 2018/4/4
 * @Description:
 * @Modified By:
 */
public interface ApplyLearningService {
    void insertApplyLearning(ApplyLearning applyLearning);

    void deleteApplyLearning(int id);

    void updateApplyLearning(ApplyLearning applyLearning);

    long getApplyLearningCount(int applyStatus);

    long getApplyLearningCountAll();

    List<ApplyLearning> getApplyLearningByPage(int page, int limit, int applyStatus);

    List<ApplyLearning> getApplyLearningByPageAll(int page, int limit);
}
