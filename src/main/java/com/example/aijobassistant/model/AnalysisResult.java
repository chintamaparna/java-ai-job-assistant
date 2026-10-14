
package com.example.aijobassistant.model;

import java.util.List;

public class AnalysisResult {

    private int matchScore;
    private List<String> matchingSkills;
    private List<String> missingSkills;
    private String summary;

    public AnalysisResult() {
    }

    public AnalysisResult(
            int matchScore,
            List<String> matchingSkills,
            List<String> missingSkills,
            String summary) {
        this.matchScore = matchScore;
        this.matchingSkills = matchingSkills;
        this.missingSkills = missingSkills;
        this.summary = summary;
    }

    public int getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(int matchScore) {
        this.matchScore = matchScore;
    }

    public List<String> getMatchingSkills() {
        return matchingSkills;
    }

    public void setMatchingSkills(List<String> matchingSkills) {
        this.matchingSkills = matchingSkills;
    }

    public List<String> getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(List<String> missingSkills) {
        this.missingSkills = missingSkills;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
