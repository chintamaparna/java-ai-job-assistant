package com.example.aijobassistant.service;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PdfService {

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    public String extractText(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException(
                    "Please upload a PDF file."
            );
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new IllegalArgumentException(
                    "PDF file must be smaller than 5 MB."
            );
        }

        String filename = file.getOriginalFilename();

        if (filename == null ||
                !filename.toLowerCase().endsWith(".pdf")) {

            throw new IllegalArgumentException(
                    "Only PDF files are supported."
            );
        }

        try (PDDocument document =
                     Loader.loadPDF(file.getBytes())) {

            if (document.isEncrypted()) {
                throw new IllegalArgumentException(
                        "Encrypted or password-protected PDFs are not supported."
                );
            }

            String text =
                    new PDFTextStripper().getText(document);

            if (text == null || text.isBlank()) {
                throw new IllegalArgumentException(
                        "No readable text found in the PDF. " +
                                "The PDF may be scanned or image-based."
                );
            }

            return text.trim();
        }
    }
}