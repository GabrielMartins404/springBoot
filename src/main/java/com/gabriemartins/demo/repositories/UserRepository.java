package com.gabriemartins.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gabriemartins.demo.models.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

    @Query(value = "SELECT * FROM user WHERE user_registration = :registration", nativeQuery = true)
    Optional<UserModel> userFindRegistration(@Param("registration") String registrtration);
    
    //Repository para retornar um unico usuário de acordo com o login e senha
    @Query(value = "SELECT * FROM user WHERE user_registration = :registration AND use_password = :password", nativeQuery = true)
    Optional<UserModel> userFindLogin(@Param("registration") String registration, @Param("password") String password);
}
