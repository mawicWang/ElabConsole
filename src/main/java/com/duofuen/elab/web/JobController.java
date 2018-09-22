package com.duofuen.elab.web;

import com.duofuen.elab.domain.Job;
import com.duofuen.elab.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Date;

@Controller
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }


    @RequestMapping("/listJob")
    public String listCharacter(Model model) {
        model.addAttribute("listJob", jobService.findAll());
        return "listJob";
    }

    @RequestMapping("/detailJob")
    public String detailJob(Integer id, Model model) {
        Job job = jobService.findById(id);
        model.addAttribute("job", job);
        return "detailJob";
    }

    @Transactional
    @PostMapping(path = "/saveJob")
    @ResponseBody
    public String saveJob(@RequestBody Job job) {
        if (job != null) {
            job.setUpdateTime(Date.from(Instant.now()));
        }
        jobService.save(job);
        return "success";
    }

    @Transactional
    @RequestMapping("/deleteJob")
    @ResponseBody
    public String deleteJob(Integer id) {
        boolean deleted = jobService.deleteById(id);
        if (!deleted) {
            return "删除失败！";
        }
        return "删除成功！";
    }
}
