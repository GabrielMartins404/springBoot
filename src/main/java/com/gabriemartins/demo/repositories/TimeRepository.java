package com.gabriemartins.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gabriemartins.demo.models.TimeModel;

@Repository
public interface TimeRepository extends JpaRepository<TimeModel, Long>{
    //Query para buscar horarios por laboratorio
    @Query(value = "SELECT * FROM time WHERE time_lab = :labId", nativeQuery = true)
    List<TimeModel> findTimeByLab(@Param("labId") Long labId);

    @Query(value = "SELECT * FROM time WHERE time_reserve =:reserveId", nativeQuery = true)
    List<TimeModel> findTimeByReserve(@Param("reserveId") Long reserveId);
}
