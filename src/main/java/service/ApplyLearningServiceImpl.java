package service;

import entity.ApplyLearning;
import entity.ApplyLearningExample;

import java.util.List;

import mapper.ApplyLearningMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ApplyLearningServiceImpl implements ApplyLearningService {
    @Autowired
    ApplyLearningMapper applyLearningMapper;

    public void insertApplyLearning(ApplyLearning applyLearning) {
        applyLearningMapper.insertSelective(applyLearning);
    }

    public void deleteApplyLearning(int id) {
        applyLearningMapper.deleteByPrimaryKey(Integer.valueOf(id));
    }

    public void updateApplyLearning(ApplyLearning applyLearning) {
        applyLearningMapper.updateByPrimaryKeySelective(applyLearning);
    }

    public long getApplyLearningCount(int applyStatus) {
        ApplyLearningExample applyLearningExample = new ApplyLearningExample();
        applyLearningExample.createCriteria().andApplyStatusEqualTo(applyStatus);
        return applyLearningMapper.countByExample(applyLearningExample);
    }

    public long getApplyLearningCountAll() {
        ApplyLearningExample applyLearningExample = new ApplyLearningExample();
        return applyLearningMapper.countByExample(applyLearningExample);
    }

    public List<ApplyLearning> getApplyLearningByPage(int page, int limit, int applyStatus) {
        ApplyLearningExample applyLearningExample = new ApplyLearningExample();
        applyLearningExample.setOrderByClause("applyDate asc");
        applyLearningExample.createCriteria().andApplyStatusEqualTo(applyStatus);
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        return applyLearningMapper.selectByExampleWithRowbounds(applyLearningExample, rowBounds);
    }

    public List<ApplyLearning> getApplyLearningByPageAll(int page, int limit) {
        ApplyLearningExample applyLearningExample = new ApplyLearningExample();
        applyLearningExample.setOrderByClause("applyDate asc");
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        return applyLearningMapper.selectByExampleWithRowbounds(applyLearningExample, rowBounds);
    }
}