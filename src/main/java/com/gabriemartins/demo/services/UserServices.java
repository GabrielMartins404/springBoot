package com.gabriemartins.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriemartins.demo.models.UserModel;
import com.gabriemartins.demo.repositories.CourseRepository;
import com.gabriemartins.demo.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service //Indicação que se trata de uma anotação de service
public class UserServices {
    @Autowired //Anotação para iniciar as interfaces do repository
    private UserRepository userRepository;

    //Métod que busca usuário por id
    public UserModel findUserById(Long id){
        Optional<UserModel> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new RuntimeException("Falha ao buscar usuário pelo ID"));
    }

    //Método de login do usuário
    public UserModel loginSystemUser(String registration, String password){
        //O optional é usado para o caso do sistema não retornar nenhum usuário
        Optional<UserModel> user = this.userRepository.userFindLogin(registration, password);
        //Verificar se existe um usuário com as informações fornecidas
        if(user.isPresent()){
            return user.get();
        }else{
            throw new RuntimeException("Usuário ou senha incorretos!!");
        }
    }

    //Método para cadastrar usuário
    @Transactional // Essa anotação serve como uma segurança no banco, que garante que o processo só será concluído quando tudo foi construido
    public UserModel createUser(UserModel user){
        //Primeiro verificamos se o usuário já está cadastrado no sistema a partir da matricula
        if(this.userRepository.userFindRegistration(user.getUserRegistration()).isPresent()){
            throw new RuntimeException("Usuário já cadastrado no sistema! Favor, realizar login");
        }else{
            // Se não tiver matricula, reseto por segurança o id e já cadastro como novo
            user.setUserId(null);
            user = this.userRepository.save(user);
            return user;   
        }
    }

    //Método para atualizar o usuário já existente
    @Transactional
    public UserModel updateUser(UserModel user){
        //Cópia de segurança
        Optional<UserModel> newUser = this.userRepository.findById(user.getUserId());

        if(newUser.isPresent()){
            newUser.get().setUserName(user.getUserName());
            newUser.get().setUserDateBirth(user.getUserDateBirth());
            //newUser.get().setUserCourse(user.getUserCourse()); Vai poder alterar o curso??
            newUser.get().setUserPassword(user.getUserPassword());
            return this.userRepository.save(newUser.get());
        }else{
            throw new RuntimeException("Falha ao atualizar usuário! Erro de id");
        }
    }

}
