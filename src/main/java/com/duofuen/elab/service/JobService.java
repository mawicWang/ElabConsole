package com.duofuen.elab.service;

import com.duofuen.elab.domain.Job;
import com.duofuen.elab.domain.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    public Iterable<Job> findAll() {
        return jobRepository.findAll();
    }

    public Job findById(Integer id) {
        if (id != null) {
            Optional<Job> job = jobRepository.findById(id);
            if (job.isPresent()) {
                return job.get();
            }
        }

        return new Job();
    }

    public void save(Job job) {
        jobRepository.save(job);
    }

    public boolean deleteById(Integer id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
