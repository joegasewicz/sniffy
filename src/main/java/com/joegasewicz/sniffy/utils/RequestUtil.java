package com.joegasewicz.sniffy.utils;

import com.joegasewicz.sniffy.entities.ApplicationEntity;
import com.joegasewicz.sniffy.entities.RequestEntity;
import com.joegasewicz.sniffy.services.ApplicationService;
import com.joegasewicz.sniffy.services.RequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RequestUtil {

    private WebClient webClient;
    private long appId;
    private String uri;
    private ApplicationService applicationService;
    private RequestService requestService;

    private LogUtil logUtil;
    private int statusCode;
    private String statusPhrase;

    public RequestUtil(ApplicationService applicationService, RequestService requestService, LogUtil logUtil) {
        this.applicationService = applicationService;
        this.requestService = requestService;
        this.logUtil = logUtil;
        webClient = WebClient.builder().baseUrl("").build();
    }

    @Transactional
    public void poll() {
        ApplicationEntity applicationEntity;
        while(true) {
            // check if we still want to poll
            try {
                applicationEntity = applicationService.get(appId);
            } catch (Exception err) {
                err.getStackTrace();
                break;
            }

            if (applicationEntity == null || applicationEntity.getPollRate() == null) {
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
            RequestEntity newRequestEntity = requestService.create(logString, statusCode, statusPhrase, appId);
            applicationService.saveRequestEntities(appId, newRequestEntity);
            // async wait for poleRate minutes
            try {
                Thread.sleep(applicationEntity.getPollRate() * 60 * 100);
            } catch (InterruptedException err) {
                System.out.println(err.getMessage());
                break;
            }
        }
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
