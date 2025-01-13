package com.gabriemartins.demo.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriemartins.demo.models.UserModel;
import com.gabriemartins.demo.services.UserServices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController //Anotação de controller
@RequestMapping("/user") //Aqui determino a rota http
@Validated //Faço isso para validar as minhas restrições do meu Model
public class UserController {
    @Autowired
    private UserServices userServices; //Aqui importo os services de usuarios

    @GetMapping("/{idUser}") //Aqui indico que o método é GET e o mesmo receberá um id
    public ResponseEntity<UserModel> findUserById(@PathVariable Long idUser){ //PathVariable indica o id que será usado para busca
        UserModel user = this.userServices.findUserById(idUser); //Aqui uso o service que irá buscar o usuário pelo id e retornar
        return ResponseEntity.ok().body(user); //Aqui indico que me será retornado o user através do método ResponseEntity e no corpo do retorno

    }
    @PostMapping() //Método POST
    public ResponseEntity<Void> createUser(@RequestBody UserModel user){ //Aqui indica que as informações de user serão passados pelo body
        this.userServices.createUser(user);
        //Após criar o usuário, preciso indicar o local em que o mesmo deverá abrir após esse inserção
        //Para isso, uso o URI indicando a url após a inserção

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getUserId()).toUri(); //Aqui pego o id gerado do objeto e insiro
        return ResponseEntity.created(uri).build();
    }
    
    //Método Update
    @PutMapping("/{idUser}")
    public ResponseEntity<Void> updateUser(@RequestBody UserModel user, @PathVariable Long idUser){ //Aqui indico que o campo a ser atualizado e o ID do usuario
        user.setUserId(idUser); //Aqui garanto que o ID do objeto é o mesmo recebido
        this.userServices.updateUser(user);
        return ResponseEntity.noContent().build(); //Como não quero retornar nada, indico aqui
    }

    //Só para titulos de anotação, será feito o delete
    // @DeleteMapping("/{idUser}") //Indico o id do usuário
    // public ResponseEntity<UserModel> deleteUser(@PathVariable Long idUser){
    //     this.userServices.
    // }

}
