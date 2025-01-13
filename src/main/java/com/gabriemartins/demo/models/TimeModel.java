package com.gabriemartins.demo.models;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
@Table(name = TimeModel.TABLE_NAME)
public class TimeModel {
    public static final String TABLE_NAME = "time";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeId", unique = true)
    private Long timeId;

    @Column(name = "timeStart", nullable = true)
    @NotNull
    private Time timeStart;

    @Column(name = "timeEnd", nullable = true)
    @NotNull
    private Time timeEnd;

    @Column(name = "timeDate", nullable = true)
    @NotNull
    private Date timeDate;

    @Column(name = "timeStatus", columnDefinition = "TINYINT(1) DEFAULT 1")
    @NotNull
    private boolean timeStatus = true;

    @ManyToOne
    @JoinColumn(name = "timeLab", nullable = false)
    private LaboratoryModel timeLab;

    @ManyToOne
    @JoinColumn(name = "timeReserve", nullable = true)
    private ReserveModel timeReserve;


    public TimeModel() {}

    public TimeModel(Long timeId, Time timeStart, Time timeEnd, Date timeDate, boolean timeStatus, LaboratoryModel timeLab, ReserveModel timeReserve) {
        this.timeId = timeId;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.timeDate = timeDate;
        this.timeStatus = timeStatus;
        this.timeLab = timeLab;
        this.timeReserve = timeReserve;
    }
   

    public Time getTimeStart() {
        return this.timeStart;
    }

    public void setTimeStart(Time timeStart) {
        this.timeStart = timeStart;
    }

    public Time getTimeEnd() {
        return this.timeEnd;
    }

    public void setTimeEnd(Time timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Date getTimeDate() {
        return this.timeDate;
    }

    public void setTimeDate(Date timeDate) {
        this.timeDate = timeDate;
    }

    public ReserveModel getTimeReserve() {
        return this.timeReserve;
    }

    public void setTimeReserve(ReserveModel timeReserve) {
        this.timeReserve = timeReserve;
    }

    public Long getTimeId() {
        return this.timeId;
    }

    public void setTimeId(Long timeId) {
        this.timeId = timeId;
    }

    public boolean isTimeStatus() {
        return this.timeStatus;
    }

    public boolean getTimeStatus() {
        return this.timeStatus;
    }

    public void setTimeStatus(boolean timeStatus) {
        this.timeStatus = timeStatus;
    }

    public LaboratoryModel getTimeLab() {
        return this.timeLab;
    }

    public void setTimeLab(LaboratoryModel timeLab) {
        this.timeLab = timeLab;
    }

    public TimeModel timeId(Long timeId) {
        setTimeId(timeId);
        return this;
    }



    public TimeModel timeStatus(boolean timeStatus) {
        setTimeStatus(timeStatus);
        return this;
    }

    public TimeModel timeLab(LaboratoryModel timeLab) {
        setTimeLab(timeLab);
        return this;
    }

    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeId, timeStart, timeEnd, timeStatus, timeLab);
    }
    
}
