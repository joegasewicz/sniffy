package com.joegasewicz.sniffy.repositories;

import com.joegasewicz.sniffy.entities.ApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {

}
