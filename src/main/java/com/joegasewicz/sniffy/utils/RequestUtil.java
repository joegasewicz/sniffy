package com.joegasewicz.sniffy.utils;

import com.joegasewicz.sniffy.entities.ApplicationEntity;
import com.joegasewicz.sniffy.services.ApplicationService;
import com.joegasewicz.sniffy.services.RequestService;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

public class RequestUtil {

    private WebClient webClient;
    private long appId;
    private String uri;
    private int poleInterval;
    private ApplicationService applicationService;
    private RequestService requestService;
    private LogUtil logUtil;
    private int statusCode;
    private String statusPhrase;

    public RequestUtil(RequestService requestService, LogUtil logUtil, int poleInterval) {
        this.requestService = requestService;
        this.logUtil = logUtil;
        this.poleInterval = poleInterval;
        webClient = WebClient.builder().baseUrl("").build();
    }

    public void poll() {
        while(true) {
            // check if we still wantr to poll
            ApplicationEntity applicationEntity = applicationService.get(appId);
            if (applicationEntity == null) {
                System.out.println("Error: No Application Entity!"); // TODO throw
                break;
            }
            if (applicationEntity.getPollStatus().equalsIgnoreCase("stop")) {
                var logString = logUtil.stop();
                requestService.create(logString, statusCode, statusPhrase, appId);
                break;
            }
            ClientResponse clientResponse = webClient
                    .get()
                    .uri(uri)
                    .exchange()
                    .block();

            statusPhrase = clientResponse.statusCode().toString();
            statusCode = clientResponse.statusCode().value();
            String logString;
            if (statusCode != 200) {
                logString = logUtil.error(statusCode, statusPhrase, appId, null);
            } else {
                logString = logUtil.ok(statusCode, statusPhrase, appId);
            }
            // save the status to the db with the appId date and time
            requestService.create(logString, statusCode, statusPhrase, appId);
            // async wait for poleRate minutes
            try {
                Thread.sleep(poleInterval);
            } catch (InterruptedException err) {
                System.out.println(err.getMessage());
                break;
            }
        }
       // continue in while loop
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
