package service;

import entity.ApplyResearch;
import entity.ApplyResearchExample;
import entity.ApplyResearchWithBLOBs;
import mapper.ApplyResearchMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ApplyResearchServiceImpl implements ApplyResearchService {
    @Autowired
    ApplyResearchMapper applyResearchMapper;

    @Override
    public void insertApplyResearch(ApplyResearchWithBLOBs applyResearch) {
        applyResearchMapper.insertSelective(applyResearch);
    }

    @Override
    public void deleteApplyResearch(int id) {
        applyResearchMapper.deleteByPrimaryKey(Integer.valueOf(id));
    }

    @Override
    public void updateApplyResearch(ApplyResearchWithBLOBs applyResearch) {
        applyResearchMapper.updateByPrimaryKeySelective(applyResearch);
    }

    @Override
    public long getApplyResearchCount(int applyStatus) {
        ApplyResearchExample applyResearchExample = new ApplyResearchExample();
        applyResearchExample.createCriteria().andApplyStatusEqualTo(applyStatus);
        return applyResearchMapper.countByExample(applyResearchExample);
    }

    @Override
    public long getApplyResearchCountAll() {
        ApplyResearchExample applyResearchExample = new ApplyResearchExample();
        return applyResearchMapper.countByExample(applyResearchExample);
    }

    @Override
    public List<ApplyResearch> getApplyResearchByPage(int page, int limit, int applyStatus) {
        ApplyResearchExample applyResearchExample = new ApplyResearchExample();
        applyResearchExample.setOrderByClause("applyDate asc");
        applyResearchExample.createCriteria().andApplyStatusEqualTo(applyStatus);
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        return applyResearchMapper.selectByExampleWithRowbounds(applyResearchExample, rowBounds);
    }

    @Override
    public List<ApplyResearch> getApplyResearchByPageAll(int page, int limit) {
        ApplyResearchExample applyResearchExample = new ApplyResearchExample();
        applyResearchExample.setOrderByClause("applyDate asc");
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        return applyResearchMapper.selectByExampleWithRowbounds(applyResearchExample, rowBounds);
    }
}