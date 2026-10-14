package com.example.aijobassistant.controller;

import com.example.aijobassistant.model.AnalysisRequest;
import com.example.aijobassistant.model.AnalysisResult;
import com.example.aijobassistant.service.AiAnalysisService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:3000"
})
public class AiAnalysisController {

    private final AiAnalysisService aiAnalysisService;

    public AiAnalysisController(AiAnalysisService aiAnalysisService) {
        this.aiAnalysisService = aiAnalysisService;
    }

    @PostMapping("/analyze")
    public AnalysisResult analyzeJob(
            @Valid @RequestBody AnalysisRequest request) {
        return aiAnalysisService.analyzeJob(request);
    }
}
