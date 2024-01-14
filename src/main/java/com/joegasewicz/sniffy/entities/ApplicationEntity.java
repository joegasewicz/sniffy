package com.joegasewicz.sniffy.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ApplicationEntity {
    private @Id @GeneratedValue Long id;
    private String name;
    private String type;
    private String url;
    private String status;
    private int poleRate;
    private String poleStatus;

    public ApplicationEntity(String name, String type, String url, String status, int poleRate, String poleStatus) {
        this.name = name;
        this.type = type;
        this.url = url;
        this.status = status;
        this.poleRate = poleRate;
        this.poleStatus = poleStatus;

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

    public int getPoleRate() {
        return poleRate;
    }

    public void setPoleRate(int poleRate) {
        this.poleRate = poleRate;
    }

    public String getPoleStatus() {
        return poleStatus;
    }

    public void setPoleStatus(String poleStatus) {
        this.poleStatus = poleStatus;
    }

}
