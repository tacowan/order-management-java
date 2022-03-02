package com.example.customerapi.models;

import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModelProperty.AccessMode;

import java.util.UUID;

@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @Column(name = "Id")
    @ApiModelProperty(accessMode = AccessMode.READ_ONLY)
    private UUID id;

    @Column(name = "Name")
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
