package com.example.aijobassistant.service;

import com.example.aijobassistant.model.AnalysisRequest;
import com.example.aijobassistant.model.Job;
import com.example.aijobassistant.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AiAnalysisServiceTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ChatClient.Builder chatClientBuilder;

    @Mock
    private ChatClient chatClient;

    private AiAnalysisService aiAnalysisService;

    private Job job;

    @BeforeEach
    void setUp() {
        when(chatClientBuilder.build())
                .thenReturn(chatClient);

        aiAnalysisService = new AiAnalysisService(
                chatClientBuilder,
                jobRepository
        );

        job = new Job();
        job.setId(1L);
        job.setTitle("Java Developer");
        job.setCompany("BMW");
        job.setDescription(
                "Spring Boot, REST API and PostgreSQL experience"
        );
    }

    @Test
    void shouldThrowExceptionWhenJobDoesNotExist() {

        // Arrange
        AnalysisRequest request = new AnalysisRequest();
        request.setJobId(99L);
        request.setCvText(
                "Java developer with Spring Boot experience"
        );

        when(jobRepository.findById(99L))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception =
                assertThrows(
                        ResponseStatusException.class,
                        () -> aiAnalysisService.analyzeJob(request)
                );

        assertEquals(404, exception.getStatusCode().value());

        verify(jobRepository).findById(99L);
    }
}