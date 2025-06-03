package com.callhippo.integration;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CallLogService {

    public CallLogSummary summarizeCallLogs(List<CallLogResponse> callLogs) {
        int incomingCount = 0, outgoingCount = 0;
        int incomingDuration = 0, outgoingDuration = 0;

        for (CallLogResponse log : callLogs) {
            if ("incoming".equalsIgnoreCase(log.getDirection())) {
                incomingCount++;
                incomingDuration += log.getDuration();
            } else if ("outgoing".equalsIgnoreCase(log.getDirection())) {
                outgoingCount++;
                outgoingDuration += log.getDuration();
            }
        }

        return new CallLogSummary(incomingCount, outgoingCount, incomingDuration, outgoingDuration);
    }
}
