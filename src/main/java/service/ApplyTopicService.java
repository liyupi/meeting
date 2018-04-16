package service;

import entity.ApplyTopic;
import entity.ApplyTopicWithBLOBs;

import java.util.List;

public interface ApplyTopicService {
    void insertApplyTopic(ApplyTopicWithBLOBs applyTopic);

    void deleteApplyTopic(int id);

    void updateApplyTopic(ApplyTopicWithBLOBs applyTopic);

    long getApplyTopicCount(int applyStatus);

    long getApplyTopicCountAll();

    List<ApplyTopic> getApplyTopicByPage(int page, int limit, int applyStatus);

    List<ApplyTopic> getApplyTopicByPageAll(int page, int limit);
}
