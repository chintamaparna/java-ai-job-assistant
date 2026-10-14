package com.example.aijobassistant.repository;

import com.example.aijobassistant.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
