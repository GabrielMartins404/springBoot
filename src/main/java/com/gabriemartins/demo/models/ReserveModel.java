package com.gabriemartins.demo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Objects;

import org.apache.commons.lang3.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = ReserveModel.NAME_TABLE)
public class ReserveModel {
    public static final String NAME_TABLE = "reserve";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserveId", unique = true)
    private Long reserveId;

    @ManyToOne
    @JoinColumn(name = "reserveUser", nullable = true)
    private UserModel reserveUser;

    @OneToMany(mappedBy = "timeReserve")
    @JsonBackReference
    private List<TimeModel> times = new ArrayList<TimeModel>();


    public ReserveModel() {}

    public ReserveModel(Long reserveId, UserModel reserveUser) {
        this.reserveId = reserveId;
        this.reserveUser = reserveUser;
    }

    public Long getReserveId() {
        return this.reserveId;
    }

    public void setReserveId(Long reserveId) {
        this.reserveId = reserveId;
    }

    public UserModel getReserveUser() {
        return this.reserveUser;
    }

    public void setReserveUser(UserModel reserveUser) {
        this.reserveUser = reserveUser;
    }

    public ReserveModel reserveId(Long reserveId) {
        setReserveId(reserveId);
        return this;
    }

    public ReserveModel reserveUser(UserModel reserveUser) {
        setReserveUser(reserveUser);
        return this;
    }


    @Override
    public boolean equals(Object o) {
      return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reserveId, reserveUser, times);
    }
}
