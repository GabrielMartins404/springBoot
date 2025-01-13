//Repository basicamente será uma interface (Não será instanciada) cujo propósito será armazenar minhas querys

package com.gabriemartins.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gabriemartins.demo.models.CourseModel;

@Repository //Anotação de repository.
public interface CourseRepository extends JpaRepository<CourseModel, Long>{
    @Query(value = "SELECT * FROM course", nativeQuery = true)
    List<CourseModel> findAllCourses();
}
