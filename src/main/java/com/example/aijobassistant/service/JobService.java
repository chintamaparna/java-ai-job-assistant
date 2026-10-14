package com.example.aijobassistant.service;

import com.example.aijobassistant.model.Job;
import com.example.aijobassistant.repository.JobRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }
    public Optional<Job> getJobById(Long id) {
        return jobRepository.findById(id);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
