package com.example.aijobassistant.service;

import com.example.aijobassistant.model.Job;
import com.example.aijobassistant.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobServiceTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobService jobService;

    private Job job;

    @BeforeEach
    void setUp() {
        job = new Job();

        job.setId(1L);
        job.setTitle("Java Developer");
        job.setCompany("BMW");
        job.setDescription(
                "Spring Boot, REST API and PostgreSQL experience"
        );
    }

    @Test
    void shouldGetAllJobs() {
        // Arrange
        when(jobRepository.findAll())
                .thenReturn(List.of(job));

        // Act
        List<Job> result = jobService.getJobs();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Java Developer", result.get(0).getTitle());

        verify(jobRepository).findAll();
    }

    @Test
    void shouldCreateJob() {
        // Arrange
        when(jobRepository.save(job))
                .thenReturn(job);

        // Act
        Job result = jobService.createJob(job);

        // Assert
        assertNotNull(result);
        assertEquals("Java Developer", result.getTitle());

        verify(jobRepository).save(job);
    }

    @Test
    void shouldGetJobById() {
        // Arrange
        when(jobRepository.findById(1L))
                .thenReturn(Optional.of(job));

        // Act
        Optional<Job> result = jobService.getJobById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("BMW", result.get().getCompany());

        verify(jobRepository).findById(1L);
    }

    @Test
    void shouldReturnEmptyWhenJobDoesNotExist() {
        // Arrange
        when(jobRepository.findById(99L))
                .thenReturn(Optional.empty());

        // Act
        Optional<Job> result = jobService.getJobById(99L);

        // Assert
        assertTrue(result.isEmpty());

        verify(jobRepository).findById(99L);
    }

    @Test
    void shouldDeleteJob() {
        // Act
        jobService.deleteJob(1L);

        // Assert
        verify(jobRepository).deleteById(1L);
    }
}