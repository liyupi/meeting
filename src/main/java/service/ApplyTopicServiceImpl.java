package service;

import entity.ApplyTopic;
import entity.ApplyTopicExample;
import entity.ApplyTopicWithBLOBs;
import mapper.ApplyTopicMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ApplyTopicServiceImpl implements ApplyTopicService {
    @Autowired
    ApplyTopicMapper applyTopicMapper;

    @Override
    public void insertApplyTopic(ApplyTopicWithBLOBs applyTopic) {
        applyTopicMapper.insertSelective(applyTopic);
    }

    @Override
    public void deleteApplyTopic(int id) {
        applyTopicMapper.deleteByPrimaryKey(Integer.valueOf(id));
    }

    @Override
    public void updateApplyTopic(ApplyTopicWithBLOBs applyTopic) {
        applyTopicMapper.updateByPrimaryKeySelective(applyTopic);
    }

    @Override
    public long getApplyTopicCount(int applyStatus) {
        ApplyTopicExample applyTopicExample = new ApplyTopicExample();
        applyTopicExample.createCriteria().andApplyStatusEqualTo(applyStatus);
        return applyTopicMapper.countByExample(applyTopicExample);
    }

    @Override
    public long getApplyTopicCountAll() {
        ApplyTopicExample applyTopicExample = new ApplyTopicExample();
        return applyTopicMapper.countByExample(applyTopicExample);
    }

    @Override
    public List<ApplyTopic> getApplyTopicByPage(int page, int limit, int applyStatus) {
        ApplyTopicExample applyTopicExample = new ApplyTopicExample();
        applyTopicExample.setOrderByClause("applyDate asc");
        applyTopicExample.createCriteria().andApplyStatusEqualTo(applyStatus);
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        return applyTopicMapper.selectByExampleWithRowbounds(applyTopicExample, rowBounds);
    }

    @Override
    public List<ApplyTopic> getApplyTopicByPageAll(int page, int limit) {
        ApplyTopicExample applyTopicExample = new ApplyTopicExample();
        applyTopicExample.setOrderByClause("applyDate asc");
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        return applyTopicMapper.selectByExampleWithRowbounds(applyTopicExample, rowBounds);
    }
}