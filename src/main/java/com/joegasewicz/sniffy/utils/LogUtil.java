package com.joegasewicz.sniffy.utils;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogUtil {

    private String statusCode;
    private String StatusValue;

    public String ok(int statusCode, String statusPhrase, Long appId) {
       return this.baseLog(statusCode, statusPhrase, appId);
    }

    public String error(int statusCode, String statusPhrase, Long appId, Error err) {
        LocalDateTime localDateTime = LocalDateTime.now();
        localDateTime = LocalDateTime.now();
        String bassLog = this.baseLog(statusCode, statusPhrase, appId);
        var forthLine = String.format("[%s] Error: %e\n", localDateTime, err);
        return String.format("%s%s", bassLog, forthLine);
    }

    private String baseLog(int statusCode, String statusPhrase, Long appId) {
        LocalDateTime localDateTime = LocalDateTime.now();
        var firstLine = String.format("[%s] App ID: %d\n", localDateTime, appId);
        localDateTime = LocalDateTime.now();
        var secondLine = String.format("[%s] Status Code: %s\n", localDateTime, statusCode);
        localDateTime = LocalDateTime.now();
        var thirdLine = String.format("[%s] Status Message: %s\n", localDateTime, statusPhrase);
        return String.format("%s%s%s", firstLine, secondLine, thirdLine);
    }

    public String stop() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return String.format("[%s] Stopping polling...\n", localDateTime);
    }
}
