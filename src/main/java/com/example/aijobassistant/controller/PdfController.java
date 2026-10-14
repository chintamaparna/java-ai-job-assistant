package com.example.aijobassistant.controller;

import com.example.aijobassistant.service.PdfService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/cv")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:3000"
})
public class PdfController {

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public Map<String, String> uploadPdf(
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        String extractedText = pdfService.extractText(file);

        return Map.of("cvText", extractedText);
    }
}
