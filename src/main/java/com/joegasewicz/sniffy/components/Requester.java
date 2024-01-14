package com.joegasewicz.sniffy.components;

import com.joegasewicz.sniffy.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Requester {

    private RequestService requestService;

    public Requester() {
        this.requestService = new RequestService();
    }

    @Async
    public void get(long appId, String uri) {
        try {
            requestService.setAppId(appId);
            requestService.setUri(uri);
            requestService.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Making request in thread: " + Thread.currentThread());
    }
}
