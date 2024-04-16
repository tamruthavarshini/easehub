package com.example.miniproject.model.requests;

import java.time.LocalDateTime;
import java.util.List;

public class RequestObject {
    private List<String> ids;
    private LocalDateTime start;
    private LocalDateTime end;
    private String reason;

    public RequestObject() {

    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
