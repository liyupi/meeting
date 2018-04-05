package controller;

import entity.ApplyLearning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ApplyLearningService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Yupi Li
 * @Date: Created in 14:59 2018/4/4
 * @Description:
 * @Modified By:
 */
@Controller
@CrossOrigin
public class ApplyLearningController {
    @Autowired
    ApplyLearningService applyLearningService;

    @RequestMapping("/addApplyLearning")
    @ResponseBody
    public Map<String, Object> insertApplyLearning(ApplyLearning applyLearning, String timeRange) {
        Map<String, Object> map = new HashMap<>();
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date startTime = dateFormat.parse(timeRange.substring(0, timeRange.indexOf('~') - 1));
            Date endTime = dateFormat.parse(timeRange.substring(timeRange.indexOf('~') + 2));
            applyLearning.setStartTime(startTime);
            applyLearning.setEndTime(endTime);
            applyLearning.setApplyStatus(0);
            applyLearning.setApplyDate(new Date());
            applyLearningService.insertApplyLearning(applyLearning);
            map.put("code", 0);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/getApplyLearningInfoByPage")
    @ResponseBody
    public Map<String, Object> getApplyLearningByPage(int page, int limit, int applyStatus) {
        Map<String, Object> map = new HashMap<>();
        try {
            long count = applyLearningService.getApplyLearningCount(applyStatus);
            if (count > 0) {
                List<ApplyLearning> applyLearningList = applyLearningService.getApplyLearningByPage(page, limit, applyStatus);
                if (applyLearningList.size() > 0) {
                    map.put("code", 0);
                    map.put("data", applyLearningList);
                } else {
                    map.put("code", 4);
                    map.put("msg", "暂无学术报告申请");
                }
            } else {
                map.put("code", 4);
                map.put("msg", "暂无学术报告申请");
            }
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/getApplyLearningInfoByPageAll")
    @ResponseBody
    public Map<String, Object> getApplyLearningByPage(int page, int limit) {
        Map<String, Object> map = new HashMap<>();
        try {
            long count = applyLearningService.getApplyLearningCountAll();
            if (count > 0) {
                List<ApplyLearning> applyLearningList = applyLearningService.getApplyLearningByPageAll(page, limit);
                if (applyLearningList.size() > 0) {
                    map.put("code", 0);
                    map.put("data", applyLearningList);
                } else {
                    map.put("code", 4);
                    map.put("msg", "暂无学术报告申请");
                }
            } else {
                map.put("code", 4);
                map.put("msg", "暂无学术报告申请");
            }
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/deleteApplyLearning")
    @ResponseBody
    public Map<String, Object> deleteApplyLearning(int applyId) {
        Map<String, Object> map = new HashMap<>();
        try {
            applyLearningService.deleteApplyLearning(applyId);
            map.put("code", 0);
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/updateApplyLearning")
    @ResponseBody
    public Map<String, Object> updateApplyLearning(ApplyLearning applyLearning) {
        Map<String, Object> map = new HashMap<>();
        try {
            applyLearningService.updateApplyLearning(applyLearning);
            map.put("code", 0);
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }
}
