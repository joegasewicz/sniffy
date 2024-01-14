package com.joegasewicz.sniffy.components;

import com.joegasewicz.sniffy.utils.RequestUtil;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Requester {

    private RequestUtil requestService;

    public Requester() {
      //  this.requestService = new RequestUtil();
    }

    @Async
    public void get(long appId, String uri) {
        try {
            long threadID = Thread.currentThread().getId();
            requestService.setAppId(appId);
            requestService.setUri(uri);
            requestService.poll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Making request in thread: " + Thread.currentThread());
    }
}
