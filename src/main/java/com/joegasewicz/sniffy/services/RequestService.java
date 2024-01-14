package com.joegasewicz.sniffy.services;

import com.joegasewicz.sniffy.entities.RequestEntity;
import com.joegasewicz.sniffy.repositories.RequestRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    private RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public RequestEntity create(String log, int statusCode, String statusValue, Long appId) {
        RequestEntity requestEntity = new RequestEntity(log, statusCode, statusValue, appId);
        requestRepository.save(requestEntity);
        return requestEntity;
    }

}
