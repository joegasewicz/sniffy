package com.joegasewicz.sniffy.services;

import com.joegasewicz.sniffy.entities.ApplicationEntity;
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

    public void updatePoleStatus(long appId, int poleRate) {
        ApplicationEntity applicationEntity = applicationRepository.getReferenceById(appId);
        applicationEntity.setPollRate(poleRate);
        applicationRepository.save(applicationEntity);
    }
}
