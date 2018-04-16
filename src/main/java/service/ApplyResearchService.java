package service;

import entity.ApplyResearch;
import entity.ApplyResearchWithBLOBs;

import java.util.List;

public interface ApplyResearchService {
    void insertApplyResearch(ApplyResearchWithBLOBs applyResearch);

    void deleteApplyResearch(int id);

    void updateApplyResearch(ApplyResearchWithBLOBs applyResearch);

    long getApplyResearchCount(int applyStatus);

    long getApplyResearchCountAll();

    List<ApplyResearch> getApplyResearchByPage(int page, int limit, int applyStatus);

    List<ApplyResearch> getApplyResearchByPageAll(int page, int limit);
}
