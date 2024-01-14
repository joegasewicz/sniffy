package com.joegasewicz.sniffy.repositories;

import com.joegasewicz.sniffy.entities.RequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<RequestEntity, Long> {
}
