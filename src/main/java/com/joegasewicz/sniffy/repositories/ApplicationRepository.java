package com.joegasewicz.sniffy.repositories;

import com.joegasewicz.sniffy.entities.ApplicationEntity;
import com.joegasewicz.sniffy.entities.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {

}
