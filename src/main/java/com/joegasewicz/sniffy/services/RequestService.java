package com.joegasewicz.sniffy.services;

import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

public class RequestService {

    private WebClient webClient;
    private long appId;
    private String uri;

    public RequestService() {
       webClient = WebClient.builder().baseUrl("").build();
    }

    public void get() {
        // While loop
        ClientResponse clientResponse = webClient
                .get()
                .uri(uri)
                .exchange()
                .block();
        var statusCode = clientResponse.statusCode().toString();
        // TODO
       // save the status to the db with the appId date and time
       // in a table called app_responses.

       // async wait for poleRate minutes

       // continue in while loop
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
