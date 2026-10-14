
package com.example.aijobassistant.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AnalysisRequest {

    @NotBlank(message = "CV text is required")
    private String cvText;

    @NotNull(message = "Job ID is required")
    @Positive(message = "Job ID must be positive")
    private Long jobId;

    public AnalysisRequest() {
    }

    public String getCvText() {
        return cvText;
    }

    public void setCvText(String cvText) {
        this.cvText = cvText;
    }


    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
}
