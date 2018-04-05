package service;

import entity.ApplyLearning;
import entity.ApplyLearningExample;
import mapper.ApplyLearningMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Yupi Li
 * @Date: Created in 15:02 2018/4/4
 * @Description:
 * @Modified By:
 */

@Service
public class ApplyLearningServiceImpl implements ApplyLearningService {
    @Autowired
    ApplyLearningMapper applyLearningMapper;

    @Override
    public void insertApplyLearning(ApplyLearning applyLearning) {
        applyLearningMapper.insertSelective(applyLearning);
    }

    @Override
    public void deleteApplyLearning(int id) {
        applyLearningMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateApplyLearning(ApplyLearning applyLearning) {
        applyLearningMapper.updateByPrimaryKeySelective(applyLearning);
    }

    @Override
    public long getApplyLearningCount(int applyStatus) {
        ApplyLearningExample applyLearningExample = new ApplyLearningExample();
        applyLearningExample.createCriteria().andApplyStatusEqualTo(applyStatus);
        return applyLearningMapper.countByExample(applyLearningExample);
    }

    @Override
    public long getApplyLearningCountAll() {
        ApplyLearningExample applyLearningExample = new ApplyLearningExample();
        return applyLearningMapper.countByExample(applyLearningExample);
    }

    @Override
    public List<ApplyLearning> getApplyLearningByPage(int page, int limit, int applyStatus) {
        ApplyLearningExample applyLearningExample = new ApplyLearningExample();
        applyLearningExample.createCriteria().andApplyStatusEqualTo(applyStatus);
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        return applyLearningMapper.selectByExampleWithRowbounds(applyLearningExample, rowBounds);
    }

    @Override
    public List<ApplyLearning> getApplyLearningByPageAll(int page, int limit) {
        ApplyLearningExample applyLearningExample = new ApplyLearningExample();
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        return applyLearningMapper.selectByExampleWithRowbounds(applyLearningExample, rowBounds);
    }
}
