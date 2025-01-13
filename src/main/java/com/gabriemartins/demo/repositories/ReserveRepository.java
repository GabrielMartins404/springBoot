package com.gabriemartins.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gabriemartins.demo.models.ReserveModel;

@Repository
public interface ReserveRepository extends JpaRepository<ReserveModel, Long>{
    @Query(value = "SELECT * FROM reserve", nativeQuery = true)
    List<ReserveModel> findAllReserves();
}
