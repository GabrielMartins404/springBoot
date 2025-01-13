package com.gabriemartins.demo.models;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = LaboratoryModel.TABLE_NAME)
public class LaboratoryModel {
    public static final String TABLE_NAME = "Laboratory";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "labId", unique = true)
    private Long labId;

    @Column(name = "labDescription", length = 100, nullable = true)
    @NotBlank
    private String labDescription;

    @Column(name = "labCapacity")
    private Integer labCapacity;

    @Column(name = "labLocation", length = 100, nullable = true)
    private String labLocation;

    @Column(name = "labDetails", columnDefinition = "TEXT")
    private String labDetails;

    @OneToMany(mappedBy = "timeLab")
    @JsonBackReference
    private List<TimeModel> times = new ArrayList<TimeModel>();
   
    public LaboratoryModel() {}

    public LaboratoryModel(Long labId, String labDescription, Integer labCapacity, String labLocation, String labDetails) {
        this.labId = labId;
        this.labDescription = labDescription;
        this.labCapacity = labCapacity;
        this.labLocation = labLocation;
        this.labDetails = labDetails;
    }

    public Long getLabId() {
        return this.labId;
    }

    public void setLabId(Long labId) {
        this.labId = labId;
    }

    public String getLabDescription() {
        return this.labDescription;
    }

    public void setLabDescription(String labDescription) {
        this.labDescription = labDescription;
    }

    public Integer getLabCapacity() {
        return this.labCapacity;
    }

    public void setLabCapacity(Integer labCapacity) {
        this.labCapacity = labCapacity;
    }

    public String getLabLocation() {
        return this.labLocation;
    }

    public void setLabLocation(String labLocation) {
        this.labLocation = labLocation;
    }

    public String getLabDetails() {
        return this.labDetails;
    }

    public void setLabDetails(String labDetails) {
        this.labDetails = labDetails;
    }

    public LaboratoryModel labId(Long labId) {
        setLabId(labId);
        return this;
    }

    public LaboratoryModel labDescription(String labDescription) {
        setLabDescription(labDescription);
        return this;
    }

    public LaboratoryModel labCapacity(Integer labCapacity) {
        setLabCapacity(labCapacity);
        return this;
    }

    public LaboratoryModel labLocation(String labLocation) {
        setLabLocation(labLocation);
        return this;
    }

    public LaboratoryModel labDetails(String labDetails) {
        setLabDetails(labDetails);
        return this;
    }
    
    public List<TimeModel> getTimes() {
        return this.times;
    }

    public void setTimes(List<TimeModel> times) {
        this.times = times;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labId, labDescription, labCapacity, labLocation, labDetails);
    }
    
}
