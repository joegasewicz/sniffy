package com.joegasewicz.sniffy.services;

import com.joegasewicz.sniffy.entities.ApplicationEntity;
import com.joegasewicz.sniffy.entities.RequestEntity;
import com.joegasewicz.sniffy.repositories.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    private ApplicationRepository applicationRepository;

    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public ApplicationEntity get(long appId) {
        return applicationRepository.getReferenceById(appId);
    }

    public List<ApplicationEntity> getAll() {
        return applicationRepository.findAll();
    }

    public void createApplication(ApplicationEntity newApp) {
        ApplicationEntity applicationEntity = new ApplicationEntity(newApp.getName(), newApp.getType(), newApp.getUrl(), "-", 0, "stopped");
        applicationRepository.save(applicationEntity);
    }

    public void updatePollStatus(long appId, int pollRate) {
        ApplicationEntity applicationEntity = applicationRepository.getReferenceById(appId);
        applicationEntity.setPollRate(pollRate);
        applicationRepository.save(applicationEntity);
    }

    public ApplicationEntity saveRequestEntities(long apiId, RequestEntity requestEntity) {
        ApplicationEntity applicationEntity = applicationRepository.getReferenceById(apiId);
        List<RequestEntity> requestEntities = applicationEntity.getRequestEntities();
        requestEntities.add(requestEntity);
        applicationEntity.setRequestEntities(requestEntities);
        applicationRepository.save(applicationEntity);
        return applicationEntity;
    }
}
