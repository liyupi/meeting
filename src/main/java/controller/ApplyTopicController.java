package controller;

import entity.ApplyTopic;
import entity.ApplyTopicWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import service.ApplyTopicService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@CrossOrigin
public class ApplyTopicController {
    @Autowired
    ApplyTopicService applyTopicService;

    @RequestMapping("/addApplyTopic")
    @ResponseBody
    public Map<String, Object> insertApplyTopic(ApplyTopicWithBLOBs applyTopic, String duration) {
        Map<String, Object> map = new HashMap<>();
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date startDate = dateFormat.parse(duration.substring(0, duration.indexOf('~') - 1)+"-01 00:00:00");
            Date endDate = dateFormat.parse(duration.substring(duration.indexOf('~') + 2)+"-01 00:00:00");
            applyTopic.setStartDate(startDate);
            applyTopic.setEndDate(endDate);
            applyTopic.setApplyStatus(0);
            applyTopic.setApplyDate(new Date());
            applyTopicService.insertApplyTopic(applyTopic);
            map.put("code", 0);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/getApplyTopicInfoByPage")
    @ResponseBody
    public Map<String, Object> getApplyTopicByPage(int page, int limit, int applyStatus) {
        Map<String, Object> map = new HashMap<>();
        try {
            long count = applyTopicService.getApplyTopicCount(applyStatus);
            map.put("count", count);
            if (count > 0) {
                List<ApplyTopic> applyTopicList = applyTopicService.getApplyTopicByPage(page, limit, applyStatus);
                if (applyTopicList.size() > 0) {
                    map.put("code", 0);
                    map.put("data", applyTopicList);
                } else {
                    map.put("code", 4);
                    map.put("msg", "暂无课题");
                }
            } else {
                map.put("code", 4);
                map.put("msg", "暂无课题");
            }
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/getApplyTopicInfoByPageAll")
    @ResponseBody
    public Map<String, Object> getApplyTopicByPage(int page, int limit) {
        Map<String, Object> map = new HashMap<>();
        try {
            long count = applyTopicService.getApplyTopicCountAll();
            map.put("count", count);
            if (count > 0L) {
                List<ApplyTopic> applyTopicList = applyTopicService.getApplyTopicByPageAll(page, limit);
                if (applyTopicList.size() > 0) {
                    map.put("code", 0);
                    map.put("data", applyTopicList);
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

    @RequestMapping("/deleteApplyTopic")
    @ResponseBody
    public Map<String, Object> deleteApplyTopic(int applyId) {
        Map<String, Object> map = new HashMap<>();
        try {
            applyTopicService.deleteApplyTopic(applyId);
            map.put("code", 0);
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/updateApplyTopic")
    @ResponseBody
    public Map<String, Object> updateApplyTopic(ApplyTopicWithBLOBs applyTopic) {
        Map<String, Object> map = new HashMap<>();
        try {
            applyTopicService.updateApplyTopic(applyTopic);
            map.put("code", 0);
        } catch (Exception e) {
            map.put("code", 3);
        }
        return map;
    }

    @RequestMapping("/uploadFileApplyTopic")
    @ResponseBody
    public Map<String, Object> uploadFileApplyTopic(HttpServletRequest request, @RequestParam("file") CommonsMultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        String path = new File(System.getProperty("user.dir")).getParent() + "/webapps/meeting_files/" + file.getOriginalFilename();
        File newFile = new File(path);
        try {
            file.transferTo(newFile);
            map.put("code", 0);
            map.put("src", request.getLocalAddr() + ":" + request.getLocalPort() + "/meeting_files/" + file.getOriginalFilename());
        } catch (IOException e) {
            map.put("code", 3);
        }
        return map;
    }
}
