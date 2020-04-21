package com.zprogress.controller.evaluation;

import java.time.LocalDateTime;

public class EvaluationDTO {

    private boolean success;
    private String generalNotes;
    private String reasonForFailure;
    private String measures;
    private LocalDateTime evaluationTimeStamp;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getGeneralNotes() {
        return generalNotes;
    }

    public void setGeneralNotes(String generalNotes) {
        this.generalNotes = generalNotes;
    }

    public String getReasonForFailure() {
        return reasonForFailure;
    }

    public void setReasonForFailure(String reasonForFailure) {
        this.reasonForFailure = reasonForFailure;
    }

    public String getMeasures() {
        return measures;
    }

    public void setMeasures(String measures) {
        this.measures = measures;
    }

    public LocalDateTime getEvaluationTimeStamp() {
        return evaluationTimeStamp;
    }

    public void setEvaluationTimeStamp(LocalDateTime evaluationTimeStamp) {
        this.evaluationTimeStamp = evaluationTimeStamp;
    }
}
