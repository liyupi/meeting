package controller;

import entity.ApplyResearch;
import entity.ApplyResearchWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ApplyResearchService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@CrossOrigin
public class ApplyResearchController {
    @Autowired
    ApplyResearchService applyResearchService;

    @RequestMapping("/addApplyResearch")
    @ResponseBody
    public Map<String, Object> insertApplyResearch(ApplyResearchWithBLOBs applyResearch, String dateRange, String timeRange) {
        Map<String, Object> map = new HashMap<>();
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date startDate = dateFormat.parse(dateRange + " " + timeRange.substring(0, timeRange.indexOf('~') - 1));
            Date endDate = dateFormat.parse(dateRange + " " + timeRange.substring(timeRange.indexOf('~') + 2));
            applyResearch.setStartDate(startDate);
            applyResearch.setEndDate(endDate);
            applyResearch.setApplyStatus(0);
            applyResearch.setApplyDate(new Date());
            applyResearchService.insertApplyResearch(applyResearch);
            map.put("code", 0);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/getApplyResearchInfoByPage")
    @ResponseBody
    public Map<String, Object> getApplyResearchByPage(int page, int limit, int applyStatus) {
        Map<String, Object> map = new HashMap<>();
        try {
            long count = applyResearchService.getApplyResearchCount(applyStatus);
            map.put("count", count);
            if (count > 0) {
                List<ApplyResearch> applyResearchList = applyResearchService.getApplyResearchByPage(page, limit, applyStatus);
                if (applyResearchList.size() > 0) {
                    map.put("code", 0);
                    map.put("data", applyResearchList);
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

    @RequestMapping("/getApplyResearchInfoByPageAll")
    @ResponseBody
    public Map<String, Object> getApplyResearchByPage(int page, int limit) {
        Map<String, Object> map = new HashMap<>();
        try {
            long count = applyResearchService.getApplyResearchCountAll();
            map.put("count", count);
            if (count > 0L) {
                List<ApplyResearch> applyResearchList = applyResearchService.getApplyResearchByPageAll(page, limit);
                if (applyResearchList.size() > 0) {
                    map.put("code", 0);
                    map.put("data", applyResearchList);
                } else {
                    map.put("code", 4);
                    map.put("msg", "暂无研究申请");
                }
            } else {
                map.put("code", 4);
                map.put("msg", "暂无研究申请");
            }
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/deleteApplyResearch")
    @ResponseBody
    public Map<String, Object> deleteApplyResearch(int applyId) {
        Map<String, Object> map = new HashMap<>();
        try {
            applyResearchService.deleteApplyResearch(applyId);
            map.put("code", 0);
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/updateApplyResearch")
    @ResponseBody
    public Map<String, Object> updateApplyResearch(ApplyResearchWithBLOBs applyResearch) {
        Map<String, Object> map = new HashMap<>();
        try {
            applyResearchService.updateApplyResearch(applyResearch);
            map.put("code", 0);
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }
}
