package com.joegasewicz.sniffy.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ApplicationEntity {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private String type;
    private String url;
    private String status;
    private int pollRate;
    private String pollStatus;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<RequestEntity> requestEntities = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public ApplicationEntity(String name, String type, String url, String status, int pollRate, String pollStatus) {
        this.name = name;
        this.type = type;
        this.url = url;
        this.status = status;
        this.pollRate = pollRate;
        this.pollStatus = pollStatus;

    }

    public ApplicationEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPollRate() {
        return pollRate;
    }

    public void setPollRate(int pollRate) {
        this.pollRate = pollRate;
    }

    public String getPollStatus() {
        return pollStatus;
    }

    public void setPollStatus(String pollStatus) {
        this.pollStatus = pollStatus;
    }

    public List<RequestEntity> getRequestEntities() {
        return requestEntities;
    }

    public void setRequestEntities(List<RequestEntity> requestEntities) {
        this.requestEntities = requestEntities;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
