package com.joegasewicz.sniffy.components;

import com.joegasewicz.sniffy.services.ApplicationService;
import com.joegasewicz.sniffy.services.RequestService;
import com.joegasewicz.sniffy.utils.LogUtil;
import com.joegasewicz.sniffy.utils.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class Requester {
    private RequestUtil requestUtil;
    private ApplicationService applicationService;
    private RequestService requestService;

    public Requester(ApplicationService applicationService, RequestService requestService) {
        this.applicationService = applicationService;
        this.requestService = requestService;
    }

    @Async
    @Transactional
    public void get(long appId, String uri, int pollRate) {
        try {
            this.requestUtil = new RequestUtil(applicationService, requestService, new LogUtil());
            long threadID = Thread.currentThread().getId(); // TODO
            requestUtil.setAppId(appId);
            requestUtil.setUri(uri);
            requestUtil.poll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Making request in thread: " + Thread.currentThread());
    }
}
