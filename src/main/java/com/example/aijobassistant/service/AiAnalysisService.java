package com.example.aijobassistant.service;

import com.example.aijobassistant.model.AnalysisRequest;
import com.example.aijobassistant.model.AnalysisResult;
import com.example.aijobassistant.model.Job;
import com.example.aijobassistant.repository.JobRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class AiAnalysisService {

    private final ChatClient chatClient;
    private final JobRepository jobRepository;

    public AiAnalysisService(
            ChatClient.Builder chatClientBuilder,
            JobRepository jobRepository) {
        this.chatClient = chatClientBuilder.build();
        this.jobRepository = jobRepository;
    }

    public AnalysisResult analyzeJob(AnalysisRequest request) {

        // Retrieve the selected job from PostgreSQL
        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Job not found: " + request.getJobId()
                ));

        // Get the job description from the database
        String jobDescription = job.getDescription();

        // Compare the CV with the saved job description
        return chatClient.prompt()
                .system("""
                    You are an expert technical recruiter.
                    Compare a candidate's CV with a job description.

                    Return:
                    - matchScore: integer from 0 to 100
                    - matchingSkills: list of matching skills
                    - missingSkills: list of important missing skills
                    - summary: concise explanation of the match

                    Base your assessment only on the supplied information.
                    Do not invent candidate qualifications.
                    """)
                .user(user -> user
                        .text("""
                            Analyze this CV against the selected job.

                            CV:
                            {cvText}

                            Job title:
                            {jobTitle}

                            Company:
                            {company}

                            Job description:
                            {jobDescription}
                            """)
                        .param("cvText", request.getCvText())
                        .param("jobTitle", job.getTitle())
                        .param("company", job.getCompany())
                        .param("jobDescription", jobDescription))
                .call()
                .entity(AnalysisResult.class);
    }
}
