package com.gabriemartins.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.gabriemartins.demo.models.LaboratoryModel;

@Repository
public interface LaboratoryRepository extends JpaRepository<LaboratoryModel, Long>{
    
}
